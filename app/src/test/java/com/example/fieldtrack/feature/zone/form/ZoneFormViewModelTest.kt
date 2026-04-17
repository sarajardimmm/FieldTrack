package com.example.fieldtrack.feature.zone.form

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.entity.ZoneEntity
import com.example.fieldtrack.data.repository.ZoneRepository
import com.example.fieldtrack.data.db.model.Zone
import com.example.fieldtrack.navigation.Routes
import com.example.fieldtrack.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@OptIn(ExperimentalCoroutinesApi::class)
class ZoneFormViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    private lateinit var zoneRepository: ZoneRepository

    @Before
    fun setUp() {
        zoneRepository = mockk(relaxed = true)
        mockkStatic("androidx.navigation.SavedStateHandleKt")
    }

    @After
    fun tearDown() {
        unmockkStatic("androidx.navigation.SavedStateHandleKt")
    }

    private fun createViewModel(zoneId: Long? = null): ZoneFormViewModel {
        val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
        every { savedStateHandle.toRoute<Routes.ZoneForm>() } returns Routes.ZoneForm(zoneId)

        return ZoneFormViewModel(
            zoneRepository = zoneRepository,
            savedStateHandle = savedStateHandle
        )
    }

    @Test
    fun `Initial state for new zone is empty`() = runTest {
        val viewModel = createViewModel(zoneId = null)
        
        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals("", state.name)
            assertFalse(state.isEditing)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Initial state for existing zone loads data`() = runTest {
        val id = 1L
        val mockZone = Zone(id, "North Field", "Notes")
        coEvery { zoneRepository.getZoneById(id) } returns mockZone

        val viewModel = createViewModel(zoneId = id)

        viewModel.uiState.test {
            // Use expectMostRecentItem as the state might already be updated due to UnconfinedTestDispatcher
            val state = expectMostRecentItem()
            assertEquals("North Field", state.name)
            assertTrue(state.isEditing)
        }
    }

    @Test
    fun `SaveClicked with empty name sets error`() = runTest {
        val viewModel = createViewModel()
        
        viewModel.onEvent(ZoneEvent.NameChanged(""))
        viewModel.onEvent(ZoneEvent.SaveClicked)

        assertEquals(R.string.error_zone_required, viewModel.uiState.value.nameErrorRes)
        coVerify(exactly = 0) { zoneRepository.insertZone(any()) }
    }

    @Test
    fun `SaveClicked with existing name sets error`() = runTest {
        val viewModel = createViewModel()
        val name = "Existing Zone"
        coEvery { zoneRepository.isZoneNameTaken(name) } returns true

        viewModel.onEvent(ZoneEvent.NameChanged(name))
        viewModel.onEvent(ZoneEvent.SaveClicked)

        assertEquals(R.string.error_zone_already_exists, viewModel.uiState.value.nameErrorRes)
    }

    @Test
    fun `SaveClicked with valid new zone inserts and navigates back`() = runTest {
        val viewModel = createViewModel()
        coEvery { zoneRepository.isZoneNameTaken(any()) } returns false

        viewModel.onEvent(ZoneEvent.NameChanged("New Zone"))
        
        viewModel.effect.test {
            viewModel.onEvent(ZoneEvent.SaveClicked)
            
            coVerify { 
                zoneRepository.insertZone(match { 
                    it.name == "New Zone" && it.id == 0L 
                }) 
            }
            assertEquals(ZoneEffect.NavigateBack, awaitItem())
        }
    }

    @Test
    fun `SaveClicked with valid existing zone updates and navigates back`() = runTest {
        val id = 10L
        val viewModel = createViewModel(zoneId = id)

        viewModel.onEvent(ZoneEvent.NameChanged("Updated Zone"))
        
        viewModel.effect.test {
            viewModel.onEvent(ZoneEvent.SaveClicked)
            
            coVerify { 
                zoneRepository.updateZone(match { 
                    it.name == "Updated Zone" && it.id == 10L 
                }) 
            }
            assertEquals(ZoneEffect.NavigateBack, awaitItem())
        }
    }
}