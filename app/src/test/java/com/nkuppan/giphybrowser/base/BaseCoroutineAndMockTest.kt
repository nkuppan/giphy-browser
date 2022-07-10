package com.nkuppan.giphybrowser.base

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

open class BaseCoroutineAndMockTest {

    private lateinit var autoCloseable: AutoCloseable

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Before
    open fun setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this)
    }

    @After
    open fun tearDown() {
        autoCloseable.close()
    }
}