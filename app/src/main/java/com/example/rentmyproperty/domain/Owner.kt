package com.example.rentmyproperty.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owners")
data class Owner(
    @PrimaryKey
    var id: Long? = null,

    var email: String? = null,

    var fullname: String? = null,

    var iban: String? = null

)