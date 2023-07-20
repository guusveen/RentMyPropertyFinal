package com.example.rentmyproperty.models

data class PropertyFilter(
    val searchString: String?,
    val propertyType: PropertyType?,
    val minPrice: Int?,
    val maxPrice: Int?
)
