package com.example.rentmyproperty.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "reviews")
data class Review (
    @PrimaryKey
    val id: Long?,

    val Rating: Float? = null,

    val Remark: String? = null

)
