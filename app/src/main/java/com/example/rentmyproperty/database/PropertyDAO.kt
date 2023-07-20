package com.example.rentmyproperty.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rentmyproperty.domain.Property
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDAO {
    @Query("SELECT * from properties")
    fun getAll() : Flow<List<Property>>
    @Query("SELECT * from properties WHERE id = :id")
    fun get(id: Long): LiveData<Property>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(property: Property)
    @Update
    suspend fun update(property: Property)
    @Delete
    suspend fun delete(property: Property)
}