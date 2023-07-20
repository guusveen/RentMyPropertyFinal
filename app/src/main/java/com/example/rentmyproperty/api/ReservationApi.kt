package com.example.rentmyproperty.api

import com.example.rentmyproperty.models.ReservationDTO
import retrofit2.http.*

interface ReservationApi {
    @GET("/api/reservations")
    suspend fun getAllReservations(): List<ReservationDTO>

    @GET("/api/reservations/{id}")
    suspend fun getReservation(@Path("id") id: Long): ReservationDTO

    @POST("/api/reservations")
    @Headers("Content-Type: application/json")
    suspend fun createReservation(@Body reservationDTO: ReservationDTO): Long

    @PUT("/api/reservations/{id}")
    @Headers("Content-Type: application/json")
    suspend fun updateReservation(@Path("id") id: Long, @Body reservationDTO: ReservationDTO)

    @DELETE("/api/reservations/{id}")
    suspend fun deleteReservation(@Path("id") id: Long)
}