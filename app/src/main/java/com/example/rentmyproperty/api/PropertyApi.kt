package com.example.rentmyproperty.api

import com.example.rentmyproperty.models.PropertyDTO
import retrofit2.http.*

interface PropertyApi {

    @GET("/api/propertys")
    suspend fun getAllProperties(): List<PropertyDTO>

    @GET("/api/propertys/{id}")
    suspend fun getProperty(@Path("id") id: Long): PropertyDTO

    @POST("/api/propertys")
    suspend fun createProperty(@Body propertyDTO: PropertyDTO): Long

    @PUT("/api/propertys/{id}")
    suspend fun updateProperty(@Path("id") id: Long, @Body propertyDTO: PropertyDTO): Unit

    @DELETE("/api/propertys/{id}")
    suspend fun deleteProperty(@Path("id") id: Long): Unit

}