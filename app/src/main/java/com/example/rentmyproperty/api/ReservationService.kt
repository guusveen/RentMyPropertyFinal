package com.example.rentmyproperty.api

import android.util.Log
import com.example.rentmyproperty.models.ReservationDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReservationService {
    private val retrofit = RetrofitSingleton.retrofit

    private val reservationApi = retrofit.create(ReservationApi::class.java)

    suspend fun findAll(): List<ReservationDTO> = withContext(Dispatchers.IO) {
        reservationApi.getAllReservations()
    }

    suspend fun get(id: Long): ReservationDTO = withContext(Dispatchers.IO) {
        reservationApi.getReservation(id)
    }

    suspend fun create(reservationDTO: ReservationDTO): Long = withContext(Dispatchers.IO) {
        Log.d("ReservationService", "Creating reservation: ${reservationDTO.id.toString()}")
        Log.d("ReservationService", "Creating reservation: ${reservationDTO.fromDate.toString()}")
        Log.d("ReservationService", "Creating reservation: ${reservationDTO.toDate.toString()}")
        Log.d("ReservationService", "Creating reservation: ${reservationDTO.property.toString()}")
        Log.d("ReservationService", "Creating reservation: ${reservationDTO.tenant}")

        reservationApi.createReservation(reservationDTO)
    }

    suspend fun update(id: Long, reservationDTO: ReservationDTO): Unit = withContext(Dispatchers.IO) {
        reservationApi.updateReservation(id, reservationDTO)
    }

    suspend fun delete(id: Long): Unit = withContext(Dispatchers.IO) {
        reservationApi.deleteReservation(id)
    }
}