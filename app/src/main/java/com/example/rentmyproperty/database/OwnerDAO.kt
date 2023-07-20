package com.example.rentmyproperty.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rentmyproperty.domain.Owner
import kotlinx.coroutines.flow.Flow

@Dao
interface OwnerDAO {
    @Query("SELECT * from owners")
    fun getAll() : Flow<List<Owner>>
    @Query("SELECT * from owners WHERE id = :id")
    fun get(id: Long): LiveData<Owner>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(owner: Owner)
    @Update
    suspend fun update(owner: Owner)
    @Delete
    suspend fun delete(owner: Owner)
}