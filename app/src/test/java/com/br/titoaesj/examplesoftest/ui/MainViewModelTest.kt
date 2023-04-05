package com.br.titoaesj.examplesoftest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.br.titoaesj.examplesoftest.common.Resource
import com.br.titoaesj.examplesoftest.data.api.StarWarsApi
import com.br.titoaesj.examplesoftest.data.model.Jedi
import com.br.titoaesj.examplesoftest.data.model.JediResponse
import com.br.titoaesj.examplesoftest.data.repository.JediRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

//    @OptIn(DelicateCoroutinesApi::class)
//    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
//
//    @Before
//    fun setUp() {
//        Dispatchers.setMain(mainThreadSurrogate)
//    }

    /**
     * Test with MockK
     */
    @Test
    fun `when call getAllCharacter in success`() = runTest {

        val jediResponseNext = "http://next" // Dummy
        val jediResponsePrevious = "http://previous" // Dummy
        val jediResponseResult = listOf(Jedi(name = "Teste")) // Dummy
        val jediResponseCount = 1 // Dummy

        //Given // Arrange
        val mockStarWarsApiMock = mockk<StarWarsApi>() //Mock
        val jediRepository = JediRepository(starWarsApi = mockStarWarsApiMock)

        //When // Act
        coEvery { mockStarWarsApiMock.allCharacter() } returns Response.success(
            JediResponse(
                next = jediResponseNext,
                previous = jediResponsePrevious,
                results = jediResponseResult,
                count = jediResponseCount
            )
        ) // Spy or Stub

        val mainViewModel = MainViewModel(jediRepository = jediRepository)
        val result = mainViewModel.res.getOrAwaitValue()

        //Then or Assert
        coVerify(exactly = 1) { jediRepository.getAllCharacter() } //Spy
        coVerify(exactly = 1) { mockStarWarsApiMock.allCharacter() } //Spy
        assertEquals(jediResponseCount, result.data?.count) // Stub
        assertEquals(jediResponseNext, result.data?.next) // Stub
        assertEquals(jediResponsePrevious, result.data?.previous) // Stub
        assertEquals(jediResponseResult, result.data?.results) // Stub

    }

    /**
     * Test with MockK
     */
    @Test
    fun `when call getAllCharacter in loading`() = runTest {

        //Given // Arrange
        val mockStarWarsApiMock = mockk<StarWarsApi>() //Mock
        val jediRepository = JediRepository(starWarsApi = mockStarWarsApiMock)

        val mainViewModel = MainViewModel(jediRepository = jediRepository)
        val result = mainViewModel.res.getOrAwaitValue()

        //Then or Assert
        assertEquals(Resource.loading(null), result)

    }

    /**
     * Test with MockK
     */
    @Test
    fun `when call getAllCharacter in error`() = runTest {

        val responseBody = ResponseBody.create(
            "application/json".toMediaTypeOrNull(),
            "teste"
        ) // Dummy

        //Given // Arrange
        val mockStarWarsApiMock = mockk<StarWarsApi>() //Mock
        val jediRepository = JediRepository(starWarsApi = mockStarWarsApiMock)

        //When // Act
        coEvery { mockStarWarsApiMock.allCharacter() } returns Response.error(
            404,
            responseBody
        ) // Spy or Stub
        val mainViewModel = MainViewModel(jediRepository = jediRepository)
        val result = mainViewModel.res.getOrAwaitValue()

        //Then or Assert
        coVerify(exactly = 1) { jediRepository.getAllCharacter() } //Spy
        coVerify(exactly = 1) { mockStarWarsApiMock.allCharacter() } //Spy
        assertEquals(Resource.error(responseBody.toString(), null), result)

    }

}