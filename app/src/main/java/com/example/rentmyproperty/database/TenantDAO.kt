package com.example.rentmyproperty.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rentmyproperty.domain.Tenant
import kotlinx.coroutines.flow.Flow

@Dao
interface TenantDAO {
    @Query("SELECT * from tenants")
    fun getAll() : Flow<List<Tenant>>
    @Query("SELECT * from tenants WHERE id = :id")
    fun get(id: Long): LiveData<Tenant>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tenant: Tenant)
    @Update
    suspend fun update(tenant: Tenant)
    @Delete
    suspend fun delete(tenant: Tenant)
}