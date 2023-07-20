package com.example.rentmyproperty.api

import com.example.rentmyproperty.models.TenantDTO
import retrofit2.http.*

interface TenantApi {
    @GET("/api/tenants")
    suspend fun getAllTenants(): List<TenantDTO>

    @GET("/api/tenants/{id}")
    suspend fun getTenant(@Path("id") id: Long): TenantDTO

    @POST("/api/tenants")
    suspend fun createTenant(@Body tenantDTO: TenantDTO): Long

    @PUT("/api/tenants/{id}")
    suspend fun updateTenant(@Path("id") id: Long, @Body tenantDTO: TenantDTO)

    @DELETE("/api/tenants/{id}")
    suspend fun deleteTenant(@Path("id") id: Long)
}