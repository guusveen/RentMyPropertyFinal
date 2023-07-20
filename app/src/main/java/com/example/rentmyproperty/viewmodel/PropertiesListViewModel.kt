package com.example.rentmyproperty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmyproperty.api.PropertyService
import com.example.rentmyproperty.models.PropertyDTO
import com.example.rentmyproperty.models.PropertyFilter
import kotlinx.coroutines.launch

class PropertiesListViewModel : ViewModel() {

    private val propertyService = PropertyService()

    val properties = MutableLiveData<List<PropertyDTO>>()

    init {
        getAllProperties()
    }

    private fun getAllProperties() {
        viewModelScope.launch {
            val propertyList = propertyService.getAllProperties()
            properties.value = propertyList
        }
    }

    fun applyFilter(filter: PropertyFilter) {
        viewModelScope.launch {
            val propertyList = propertyService.getAllProperties()

            val filteredList = propertyList.filter { property ->
                val typeMatch = filter.propertyType == null || property.propertyType == filter.propertyType
                val priceMatch =
                    (filter.minPrice == null || property.price!! >= filter.minPrice) &&
                            (filter.maxPrice == null || property.price!! <= filter.maxPrice)
                val searchMatch = filter.searchString.isNullOrEmpty() ||
                        property.title!!.contains(filter.searchString, ignoreCase = true) ||
                        property.description!!.contains(filter.searchString, ignoreCase = true)

                typeMatch && priceMatch && searchMatch
            }

            properties.value = filteredList
        }
    }
}
