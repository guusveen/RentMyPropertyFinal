package com.example.rentmyproperty.api

import com.example.rentmyproperty.models.PropertyDTO
import retrofit2.Call

class PropertyService {
    private val retrofit = RetrofitSingleton.retrofit

    private val propertyApi = retrofit.create(PropertyApi::class.java)

    suspend fun getAllProperties(): List<PropertyDTO> {
        return propertyApi.getAllProperties()
    }

    suspend fun getProperty(id: Long): PropertyDTO {
        return propertyApi.getProperty(id)
    }

    suspend fun createProperty(propertyDTO: PropertyDTO): Long {
        return propertyApi.createProperty(propertyDTO)
    }

    suspend fun updateProperty(id: Long, propertyDTO: PropertyDTO) {
        propertyApi.updateProperty(id, propertyDTO)
    }

    suspend fun deleteProperty(id: Long) {
        propertyApi.deleteProperty(id)
    }
}

