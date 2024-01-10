package com.virtusatestapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.virtusatestapp.model.Data
import com.virtusatestapp.model.GallaryResponse
import com.virtusatestapp.network.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {


    suspend fun getPhotoList(query:String): GallaryResponse? {
        val response=apiService.getPhotoList(query)
        val responseBody=response.body()
        Log.e("response",responseBody.toString())
        if(response.isSuccessful && responseBody!=null){
          return responseBody
        }
        return null
    }
}