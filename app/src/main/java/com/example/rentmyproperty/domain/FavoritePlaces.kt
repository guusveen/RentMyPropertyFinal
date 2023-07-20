package com.example.rentmyproperty.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_places")
data class FavoritePlaces(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val location: String,
)