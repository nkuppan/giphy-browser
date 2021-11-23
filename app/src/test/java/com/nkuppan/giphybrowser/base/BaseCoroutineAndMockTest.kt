package com.nkuppan.giphybrowser.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
open class BaseCoroutineAndMockTest {

    private lateinit var autoCloseable: AutoCloseable

    @ExperimentalCoroutinesApi
    protected val testCoroutineDispatcher = TestCoroutineDispatcher()

    @After
    open fun tearDown() {
        // reset main dispatcher to the original Main dispatcher
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        autoCloseable.close()
    }

    @Before
    open fun setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testCoroutineDispatcher)
    }
}