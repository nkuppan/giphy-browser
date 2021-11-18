package com.nkuppan.giphybrowser.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.NetworkResult
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository
import com.nkuppan.giphybrowser.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SearchViewModelTest {

    // Run tasks synchronously
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    // Sets the main coroutines dispatcher to a TestCoroutineScope for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Use a Fake DataSource so we have all necessary control over it
    private val fakeDataSource = FakeGiphyRepository()

    private lateinit var viewModel: SearchViewModel


    @Before
    fun setUp() {
        viewModel = SearchViewModel()
    }

    @Test
    fun testNull() {

    }

    @Test
    fun testApiFetchDataSuccess() {

    }
}

@ExperimentalCoroutinesApi
class FakeGiphyRepository : GiphyRepository {
    override suspend fun getGifResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): NetworkResult<List<GiphyImage>> {
        TODO("Not yet implemented")
    }

    override suspend fun getStickersResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): NetworkResult<List<GiphyImage>> {
        TODO("Not yet implemented")
    }

}