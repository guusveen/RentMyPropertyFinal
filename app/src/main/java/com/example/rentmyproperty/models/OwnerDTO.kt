package com.example.rentmyproperty.models

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class OwnerDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var email: String? = null

    @NotNull
    @Size(max = 255)
    var fullname: String? = null

    @Size(max = 255)
    var iban: String? = null

}
