package com.example.rentmyproperty

import com.example.rentmyproperty.api.TenantService
import com.example.rentmyproperty.api.RetrofitSingleton
import com.example.rentmyproperty.models.TenantDTO
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*



@RunWith(MockitoJUnitRunner::class)
class TenantServiceUnitTest {
    private val tenantService = TenantService()

    @Test
    fun testFindAll() = runBlocking {
        val tenants = tenantService.findAll()
        assertNotNull(tenants)
        assertTrue(tenants.isNotEmpty())
    }

    @Test
    fun testGet() = runBlocking {
        val tenant = tenantService.get(10015)
        assertNotNull(tenant)
        assertEquals(10015.toLong(), tenant.id)
    }

    @Test
    fun testCreate() = runBlocking {
        val tenant = TenantDTO()
        var randomEmail1 = addRandomPrefix("createtest@example.com")
        var randomEmail = addRandomPrefix(randomEmail1);
        tenant.email = randomEmail
        tenant.fullName = "Kotlin leuk"
        val id = tenantService.create(tenant)
        assertNotNull(id)
        assertTrue(id > 0)
    }

    // updatable false in de api
    @Test
    fun testUpdate() = runBlocking {
        val tenant = tenantService.get(10015)
        println("id: ${tenant.id}")
        assertNotNull(tenant)
        tenant.fullName = "John Smith"
        tenantService.update(10015, tenant)
        val updatedTenant = tenantService.get(10015)
        assertNotNull(updatedTenant)
        assert("John Smith" == updatedTenant.fullName)
    }

    fun addRandomPrefix(str: String): String {
        val prefixChar = randomChar()
        return prefixChar + str
    }
    fun randomChar(): Char {
        val randoms = (0..25).random()
        val chars = "abcdefghijklmnopqrstuvwxyz"
        return chars.get(randoms)
    }
}
