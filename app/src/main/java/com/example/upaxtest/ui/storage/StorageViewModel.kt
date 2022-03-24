package com.example.upaxtest.ui.storage

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.upaxtest.model.LocationModel
import com.example.upaxtest.repository.Repository
import javax.inject.Inject

class StorageViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    val uploadLiveData : LiveData<Boolean> = repository.uploadLiveData
    val storageListData: LiveData<List<String>> = repository.storageListData

    fun getStorage() {
        repository.getStorage()
    }

    fun uploadImage(uri: Uri, context: Context)
    {
        repository.uploadImage(uri, context)
    }
}