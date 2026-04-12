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
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: Repository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login success returns keypass`() = runTest {
        // Given
        val expectedKeypass = "art"
        whenever(repository.login("s8092899", "Abdul Moeez"))
            .thenReturn(LoginResponse(keypass = expectedKeypass))

        // When
        viewModel.login("s8092899", "Abdul Moeez")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val result = viewModel.loginResult.value
        assertNotNull(result)
        assertEquals(expectedKeypass, result?.getOrNull())
    }

    @Test
    fun `login failure returns error`() = runTest {
        // Given
        whenever(repository.login("wrong", "wrong"))
            .thenThrow(RuntimeException("Invalid credentials"))

        // When
        viewModel.login("wrong", "wrong")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val result = viewModel.loginResult.value
        assertNotNull(result)
        assertEquals(true, result?.isFailure)
    }

    @Test
    fun `login sets loading state`() = runTest {
        // Given
        whenever(repository.login("s8092899", "Abdul Moeez"))
            .thenReturn(LoginResponse(keypass = "art"))

        // When
        viewModel.login("s8092899", "Abdul Moeez")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then - loading should be false after completion
        assertEquals(false, viewModel.isLoading.value)
    }
}