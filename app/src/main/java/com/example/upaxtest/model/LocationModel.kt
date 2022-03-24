package com.example.upaxtest.model

import com.google.firebase.Timestamp
import java.io.Serializable

data class LocationModel (

    val latitude : Double? = null,
    val longitude : Double? = null,
    val date : Timestamp? = null
): Serializable
