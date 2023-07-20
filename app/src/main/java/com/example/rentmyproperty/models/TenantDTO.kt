package com.example.rentmyproperty.models

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class TenantDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var email: String? = null

    @NotNull
    @Size(max = 255)
    var fullName: String? = null

    @Size(max = 255)
    var phone: String? = null

}