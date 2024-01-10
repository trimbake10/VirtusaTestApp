package com.virtusatestapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.virtusatestapp.network.ApiService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivityTest {


    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: ApiService
    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun getTestGalleryResponse()=runTest{
        val mockResponse=MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        TestScope().launch {
            val response = apiService.getPhotoList("cats")
            mockWebServer.takeRequest()
            assertEquals(true, response.body()?.data!!.isEmpty())
        }
    }

    @Test
    fun getTestGalleryWithResponse()=runTest{
        val mockResponse=MockResponse()
        val content="[{\"id\":101},{\"id\":102}]"
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        TestScope().launch {
            val response = apiService.getPhotoList("cats")
            mockWebServer.takeRequest()
            assertEquals(true, response.body()?.data!!.size==2)
        }
    }

    @Test
    fun getTestGalleryWithResponseFail()=runTest{
        val mockResponse=MockResponse()
        val content="[{\"id\":101},{\"id\":102}]"
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        TestScope().launch {
            val response = apiService.getPhotoList("cats")
            mockWebServer.takeRequest()
            assertEquals(false, response.body()?.data!!.size==0)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


}