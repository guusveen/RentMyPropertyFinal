package com.example.rentmyproperty.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
@Entity(tableName = "reservations")
data class Reservation
(
    @PrimaryKey
    val id: Long? = null,

    val fromDate: LocalDate? = null,

    val toDate: LocalDate? = null,

    val tenant: Long? = null,

    val `property`: Long? = null
)