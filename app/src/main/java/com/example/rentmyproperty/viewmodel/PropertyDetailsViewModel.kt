package com.example.rentmyproperty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentmyproperty.api.PropertyService
import com.example.rentmyproperty.api.ReservationService
import com.example.rentmyproperty.models.PropertyDTO
import com.example.rentmyproperty.models.ReservationDTO
import kotlinx.coroutines.launch
import java.time.LocalDate

class PropertyDetailsViewModel : ViewModel() {

    private val reservationService = ReservationService()
    private val propertyService = PropertyService()

    val propertyDTO = MutableLiveData<PropertyDTO>()

    fun reserveProperty(fromDate: String, toDate: String) {
        val reservation = ReservationDTO().apply {
            this.id = 0
            this.fromDate = LocalDate.parse(fromDate, ReservationDTO.formatter)
            this.toDate = LocalDate.parse(toDate, ReservationDTO.formatter)
            this.tenant = 10015
            this.property = propertyDTO.value?.id
        }

        viewModelScope.launch {
            reserve(reservation)
        }
    }

    private fun reserve(reservation: ReservationDTO) {
        viewModelScope.launch {
            reservationService.create(reservation)
        }
    }
    fun getPropertyById(id: Long): LiveData<PropertyDTO> {
        viewModelScope.launch {
            val propertyDTO = propertyService.getProperty(id)
            this@PropertyDetailsViewModel.propertyDTO.value = propertyDTO
        }
        return propertyDTO
    }
}
