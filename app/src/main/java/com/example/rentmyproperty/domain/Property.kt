package com.example.rentmyproperty.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rentmyproperty.models.PropertyType

@Entity(tableName = "properties")
class Property (
    @PrimaryKey
    var id: Long? = null,

    var title: String? = null,

    var description: String? = null,

    var latitude: Double? = null,

    var longitude: Double? = null,

    var price: Int? = null,

    var propertyType: PropertyType? = null,

    var owner: Long? = null
)