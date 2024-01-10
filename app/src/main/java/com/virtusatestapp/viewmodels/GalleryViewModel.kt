package com.virtusatestapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtusatestapp.model.Data
import com.virtusatestapp.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel(){

    var galleryObserver =MutableLiveData<List<Data>>()

    fun getGalleryData(){
        viewModelScope.launch {
            galleryObserver.postValue(repository.getPhotoList("cats")?.data)
        }
    }
}