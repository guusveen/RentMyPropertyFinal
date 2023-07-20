package com.example.rentmyproperty

import com.example.rentmyproperty.api.OwnerService
import com.example.rentmyproperty.api.PropertyService
import com.example.rentmyproperty.api.ReservationService
import com.example.rentmyproperty.api.TenantService
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import junit.framework.Assert.*


@RunWith(MockitoJUnitRunner::class)
class ReservationServiceUnitTest {
    private val propertyService = PropertyService()
    private val ownerService = OwnerService()
    private val reservationService = ReservationService()
    private val tenantService = TenantService()

    @Test
    fun testFindAll() = runBlocking {
        val reservations = reservationService.findAll()
        assertNotNull(reservations)
        assert(reservations.isNotEmpty())
    }

    @Test
    fun testGet() = runBlocking {
        val reservation = reservationService.get(10016)
        assertNotNull(reservation)
        assertEquals(10016.toLong(), reservation.id)
    }

}