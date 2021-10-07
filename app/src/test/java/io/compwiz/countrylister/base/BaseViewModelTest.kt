package io.compwiz.countrylister.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.compwiz.countrylister.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class BaseViewModelTest: BaseKoinTest() {
    /**
     * Swaps background executor to execute task synchronously
     */
    @get:Rule
    open val instantExecutorRule = InstantTaskExecutorRule()

    /**
     * Rule to allow testing coroutines that uses main dispatcher
     */
    @ExperimentalCoroutinesApi
    @get:Rule
    open val coroutineTestRule = CoroutineTestRule()
}