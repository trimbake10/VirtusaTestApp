package com.virtusatestapp.network

import com.virtusatestapp.model.GallaryResponse
import com.virtusatestapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers(
        "Cache-Control: max-age=60",
        "Authorization: Client-ID ${Constants.CLIENT_ID}"
    )
    @GET("search/top")
   suspend fun getPhotoList(
       @Query("q") query: String
    ): Response<GallaryResponse>
}