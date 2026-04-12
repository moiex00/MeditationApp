package com.abdulmoeezsalej.meditationapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: DashboardViewModel
    private lateinit var repository: Repository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = DashboardViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchDashboard success returns entities`() = runTest {
        // Given
        val mockEntities = listOf(
            Entity(
                artworkTitle = "Mona Lisa",
                artist = "Leonardo da Vinci",
                medium = "Oil paint",
                year = 1503,
                description = "A famous painting"
            )
        )
        whenever(repository.getDashboard("art"))
            .thenReturn(DashboardResponse(entities = mockEntities, entityTotal = 1))

        // When
        viewModel.fetchDashboard("art")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val entities = viewModel.entities.value
        assertNotNull(entities)
        assertEquals(1, entities?.size)
        assertEquals("Mona Lisa", entities?.get(0)?.artworkTitle)
    }

    @Test
    fun `fetchDashboard failure sets error message`() = runTest {
        // Given
        whenever(repository.getDashboard("wrong"))
            .thenThrow(RuntimeException("Network error"))

        // When
        viewModel.fetchDashboard("wrong")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val error = viewModel.error.value
        assertNotNull(error)
        assertEquals("Network error", error)
    }

    @Test
    fun `fetchDashboard sets loading to false after completion`() = runTest {
        // Given
        whenever(repository.getDashboard("art"))
            .thenReturn(DashboardResponse(entities = emptyList(), entityTotal = 0))

        // When
        viewModel.fetchDashboard("art")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(false, viewModel.isLoading.value)
    }
}