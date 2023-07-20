package com.example.rentmyproperty.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tenants")
data class Tenant (
    @PrimaryKey
    var id: Long? = null,

    var email: String? = null,

    var fullName: String? = null,

    var phone: String? = null,

)