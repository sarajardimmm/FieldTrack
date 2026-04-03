package com.example.fieldtrack.feature.logentryform

import app.cash.turbine.test
import com.example.fieldtrack.data.repository.LogEntryRepository
import com.example.fieldtrack.feature.logentry.form.LogEntryEffect
import com.example.fieldtrack.feature.logentry.form.LogEntryEvent
import com.example.fieldtrack.feature.logentry.form.LogEntryFormViewModel
import com.example.fieldtrack.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class LogEntryFormViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var logEntryRepository: LogEntryRepository
    private lateinit var viewModel: LogEntryFormViewModel

    @Before
    fun setUp() {
        logEntryRepository = mockk(relaxed = true)
        viewModel = LogEntryFormViewModel(logEntryRepository)
    }

    @Test
    fun `ZoneChanged updates zoneName and clears zoneNameError`() {
        viewModel.onEvent(LogEntryEvent.ZoneChanged("North Field"))

        val state = viewModel.uiState.value
        assertEquals("North Field", state.zoneName)
        assertNull(state.zoneNameErrorRes)
    }

    @Test
    fun `ProductChanged updates productName and clears productNameError`() {
        viewModel.onEvent(LogEntryEvent.ProductChanged("Fungicide X"))

        val state = viewModel.uiState.value
        assertEquals("Fungicide X", state.productName)
        assertNull(state.productNameErrorRes)
    }

    @Test
    fun `SaveClicked with blank zone and product does not insert`() = runTest {
        viewModel.onEvent(LogEntryEvent.ZoneChanged(""))
        viewModel.onEvent(LogEntryEvent.ProductChanged(""))

        viewModel.onEvent(LogEntryEvent.SaveClicked)
        advanceUntilIdle()

        coVerify(exactly = 0) { logEntryRepository.createLogEntryFromNames(any(), any(), any(), any(), any(), any()) }
    }

    @Test
    fun `SaveClicked with valid data inserts log entry`() = runTest {
        val appliedAt = LocalDate.of(2026, 3, 26)

        coEvery { logEntryRepository.createLogEntryFromNames(any(), any(), any(), any(), any(), any()) } returns Unit

        viewModel.onEvent(LogEntryEvent.ZoneChanged("North Field"))
        viewModel.onEvent(LogEntryEvent.ProductChanged("Fungicide X"))
        viewModel.onEvent(LogEntryEvent.AppliedAtChanged(appliedAt))
        viewModel.onEvent(LogEntryEvent.ReapplyDaysChanged("14"))
        viewModel.onEvent(LogEntryEvent.QuantityChanged("20 ml"))
        viewModel.onEvent(LogEntryEvent.NotesChanged("Applied in the morning"))

        viewModel.onEvent(LogEntryEvent.SaveClicked)
        advanceUntilIdle()

        coVerify(exactly = 1) {
            logEntryRepository.createLogEntryFromNames(
                    zoneName = "North Field",
                    productName = "Fungicide X",
                    appliedAt = appliedAt,
                    reapplyDays = 14,
                    quantity = "20 ml",
                    notes = "Applied in the morning"
            )
        }
    }

    @Test
    fun `SaveClicked with valid data emits NavigateBack`() = runTest {
        val appliedAt = LocalDate.of(2026, 3, 26)

        coEvery { logEntryRepository.createLogEntryFromNames(any(), any(), any(), any(), any(), any()) } returns Unit

        viewModel.effect.test {
            viewModel.onEvent(LogEntryEvent.ZoneChanged("North Field"))
            viewModel.onEvent(LogEntryEvent.ProductChanged("Fungicide X"))
            viewModel.onEvent(LogEntryEvent.AppliedAtChanged(appliedAt))

            viewModel.onEvent(LogEntryEvent.SaveClicked)
            advanceUntilIdle()

            assertEquals(LogEntryEffect.NavigateBack, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}