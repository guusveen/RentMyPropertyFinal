package com.example.rentmyproperty.api

import com.example.rentmyproperty.models.OwnerDTO
import retrofit2.Response
import retrofit2.http.*

interface OwnerApi {
    @GET("/api/owners")
    suspend fun getAllOwners(): Response<List<OwnerDTO>>

    @GET("/api/owners/{id}")
    suspend fun getOwner(@Path("id") id: Long): Response<OwnerDTO>

    @POST("/api/owners")
    suspend fun createOwner(@Body ownerDTO: OwnerDTO): Response<Long>

    @PUT("/api/owners/{id}")
    suspend fun updateOwner(@Path("id") id: Long, @Body ownerDTO: OwnerDTO): Response<Void>

    @DELETE("/api/owners/{id}")
    suspend fun deleteOwner(@Path("id") id: Long): Response<Void>
}
