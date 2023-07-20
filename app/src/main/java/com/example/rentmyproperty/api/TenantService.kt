package com.example.rentmyproperty.api

import com.example.rentmyproperty.models.TenantDTO

class TenantService {
    private val retrofit = RetrofitSingleton.retrofit

    private val tenantApi = retrofit.create(TenantApi::class.java)

    suspend fun findAll(): List<TenantDTO> = tenantApi.getAllTenants()

    suspend fun get(id: Long): TenantDTO = tenantApi.getTenant(id)

    suspend fun create(tenantDTO: TenantDTO): Long = tenantApi.createTenant(tenantDTO)

    suspend fun update(id: Long, tenantDTO: TenantDTO) {
        tenantApi.updateTenant(id, tenantDTO)
    }

    suspend fun delete(id: Long) {
        tenantApi.deleteTenant(id)
    }
}