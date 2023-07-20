package com.example.rentmyproperty.api

import com.example.rentmyproperty.models.OwnerDTO

class OwnerService {
    private val retrofit = RetrofitSingleton.retrofit

    private val ownerApi = retrofit.create(OwnerApi::class.java)

    suspend fun getAllOwners(): List<OwnerDTO> {
        val response = ownerApi.getAllOwners()
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw Exception("Error getting owners: ${response.code()}")
        }
    }

    suspend fun getOwner(id: Long): OwnerDTO {
        val response = ownerApi.getOwner(id)
        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Owner not found")
        } else {
            throw Exception("Error getting owner: ${response.code()}")
        }
    }

    suspend fun createOwner(ownerDTO: OwnerDTO): Long {
        val response = ownerApi.createOwner(ownerDTO)
        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Error creating owner: ${response.code()}")
        } else {
            throw Exception("Error creating owner: ${response.code()}")
        }
    }

    suspend fun updateOwner(id: Long, ownerDTO: OwnerDTO) {
        val response = ownerApi.updateOwner(id, ownerDTO)
        if (!response.isSuccessful) {
            throw Exception("Error updating owner: ${response.code()}")
        }
    }

    suspend fun deleteOwner(id: Long) {
        val response = ownerApi.deleteOwner(id)
        if (!response.isSuccessful) {
            throw Exception("Error deleting owner: ${response.code()}")
        }
    }
}
