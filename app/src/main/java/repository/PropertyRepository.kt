package repository

import com.example.rentmyproperty.api.PropertyService
import com.example.rentmyproperty.models.PropertyDTO

class PropertyRepository {
    suspend fun getProperties(): List<PropertyDTO> {
        val propertyService = PropertyService()
        return propertyService.getAllProperties()
    }
}