package com.example.upaxtest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoviesRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prev: Int?,
    val next: Int?
)