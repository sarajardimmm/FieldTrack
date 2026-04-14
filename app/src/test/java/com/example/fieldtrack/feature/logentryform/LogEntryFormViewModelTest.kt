package com.example.fieldtrack.feature.logentryform

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.example.fieldtrack.data.repository.LogEntryRepository
import com.example.fieldtrack.data.repository.ProductRepository
import com.example.fieldtrack.data.repository.ZoneRepository
import com.example.fieldtrack.data.db.model.LogEntry
import com.example.fieldtrack.feature.logentry.form.LogEntryEffect
import com.example.fieldtrack.feature.logentry.form.LogEntryEvent
import com.example.fieldtrack.feature.logentry.form.LogEntryFormViewModel
import com.example.fieldtrack.navigation.Routes
import com.example.fieldtrack.util.MainDispatcherRule
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class LogEntryFormViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    private lateinit var logEntryRepository: LogEntryRepository
    private lateinit var zoneRepository: ZoneRepository
    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        logEntryRepository = mockk(relaxed = true)
        zoneRepository = mockk(relaxed = true)
        productRepository = mockk(relaxed = true)

        mockkStatic("androidx.navigation.SavedStateHandleKt")


        every { zoneRepository.getAllZoneNames() } returns flowOf(listOf("North Field", "South Garden"))
        every { productRepository.getAllProductNames() } returns flowOf(listOf("Fungicide X", "Fertilizer Y"))
    }

    @After
    fun tearDown() {
        unmockkStatic("androidx.navigation.SavedStateHandleKt")
    }

    private fun createViewModel(logEntryId: Long? = null): LogEntryFormViewModel {
        val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
        
        every { savedStateHandle.toRoute<Routes.LogEntryForm>() } returns Routes.LogEntryForm(logEntryId)

        return LogEntryFormViewModel(
            logEntryRepository = logEntryRepository,
            zoneRepository = zoneRepository,
            productRepository = productRepository,
            savedStateHandle = savedStateHandle
        )
    }

    @Test
    fun `Initial state for new entry is empty and not editing`() = runTest {
        val viewModel = createViewModel(logEntryId = null)
        
        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals("", state.zoneName)
            assertEquals("", state.productName)
            assertFalse(state.isEditing)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Initial state for existing entry loads data and sets isEditing`() = runTest {
        val id = 123L
        val mockEntry = LogEntry(
            id = id,
            zoneName = "West Field",
            productName = "Pesticide Z",
            appliedAt = LocalDate.of(2024, 1, 1),
            reapplyDays = 7,
            quantity = "10L",
            notes = "Test notes"
        )
        every { logEntryRepository.getLogEntryForDisplay(id) } returns flowOf(mockEntry)

        val viewModel = createViewModel(logEntryId = id)

        viewModel.uiState.test {
            val state = expectMostRecentItem()
            assertEquals("West Field", state.zoneName)
            assertEquals("Pesticide Z", state.productName)
            assertTrue(state.isEditing)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `ZoneChanged updates state and clears error`() = runTest {
        val viewModel = createViewModel()
        
        viewModel.uiState.test {
            skipItems(1) // skip initial state
            
            viewModel.onEvent(LogEntryEvent.ZoneChanged("New Zone"))
            
            val state = awaitItem()
            assertEquals("New Zone", state.zoneName)
            assertNull(state.zoneNameErrorRes)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `SaveClicked with invalid data shows errors and prevents repository call`() = runTest {
        val viewModel = createViewModel()
        
        viewModel.uiState.test {
            skipItems(1)
            
            viewModel.onEvent(LogEntryEvent.ZoneChanged(""))
            viewModel.onEvent(LogEntryEvent.ProductChanged(""))
            viewModel.onEvent(LogEntryEvent.SaveClicked)
            
            val state = expectMostRecentItem()
            assertTrue("Zone error should be present", state.zoneNameErrorRes != null)
            assertTrue("Product error should be present", state.productNameErrorRes != null)
            
            coVerify(exactly = 0) { 
                logEntryRepository.saveLogEntry(any(), any(), any(), any(), any(), any(), any()) 
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `SaveClicked with valid data for new entry calls repository with null ID`() = runTest {
        val viewModel = createViewModel(logEntryId = null)
        val date = LocalDate.now()
        
        viewModel.uiState.test {
            viewModel.onEvent(LogEntryEvent.ZoneChanged("North Field"))
            viewModel.onEvent(LogEntryEvent.ProductChanged("Fungicide X"))
            viewModel.onEvent(LogEntryEvent.AppliedAtChanged(date))
            viewModel.onEvent(LogEntryEvent.ReapplyDaysChanged("14"))

            viewModel.effect.test {
                viewModel.onEvent(LogEntryEvent.SaveClicked)
                
                coVerify(exactly = 1) {
                    logEntryRepository.saveLogEntry(
                        id = null,
                        zoneName = "North Field",
                        productName = "Fungicide X",
                        appliedAt = date,
                        reapplyDays = 14,
                        quantity = any(),
                        notes = any()
                    )
                }
                assertEquals(LogEntryEffect.NavigateBack, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `SaveClicked with valid data for existing entry passes correct ID`() = runTest {
        val id = 55L
        val viewModel = createViewModel(logEntryId = id)
        
        viewModel.uiState.test {
            viewModel.onEvent(LogEntryEvent.ZoneChanged("Zone"))
            viewModel.onEvent(LogEntryEvent.ProductChanged("Product"))
            viewModel.onEvent(LogEntryEvent.SaveClicked)

            coVerify { 
                logEntryRepository.saveLogEntry(
                    id = 55L, 
                    zoneName = "Zone", 
                    productName = "Product", 
                    appliedAt = any(),
                    reapplyDays = any(),
                    quantity = any(),
                    notes = any()
                ) 
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Zone suggestions filter and hide when exact match is found`() = runTest {
        val viewModel = createViewModel()
        
        viewModel.uiState.test {
            awaitItem() // initial state
            
            // Partial match should show suggestion
            viewModel.onEvent(LogEntryEvent.ZoneChanged("Nor"))
            assertEquals(listOf("North Field"), awaitItem().zoneSuggestions)

            // Exact match should hide suggestions
            viewModel.onEvent(LogEntryEvent.ZoneChanged("North Field"))
            assertTrue("Suggestions should be empty on exact match", awaitItem().zoneSuggestions.isEmpty())
            
            cancelAndIgnoreRemainingEvents()
        }
    }
}