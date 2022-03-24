package com.example.upaxtest.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upaxtest.model.LocationModel
import com.example.upaxtest.repository.Repository
import javax.inject.Inject

class LocationViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    val locationsListData: LiveData<List<LocationModel>> = repository.locationListData

    fun getLocations() {
        repository.getLocations()
    }
}