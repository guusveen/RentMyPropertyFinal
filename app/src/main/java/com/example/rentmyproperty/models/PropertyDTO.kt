package com.example.rentmyproperty.models

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class PropertyDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var title: String? = null

    @Size(max = 1000)
    var description: String? = null

    @NotNull
    var latitude: Double? = null

    @NotNull
    var longitude: Double? = null

    @NotNull
    var price: Int? = null

    @NotNull
    var propertyType: PropertyType? = null

    @NotNull
    var owner: Long? = null

}
