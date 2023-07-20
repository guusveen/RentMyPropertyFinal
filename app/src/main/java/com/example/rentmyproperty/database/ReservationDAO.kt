package com.example.rentmyproperty.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rentmyproperty.domain.Reservation
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDAO {
    @Query("SELECT * from reservations")
    fun getAll() : Flow<List<Reservation>>
    @Query("SELECT * from reservations WHERE id = :id")
    fun get(id: Long): LiveData<Reservation>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reservation: Reservation)
    @Update
    suspend fun update(reservation: Reservation)
    @Delete
    suspend fun delete(reservation: Reservation)
}