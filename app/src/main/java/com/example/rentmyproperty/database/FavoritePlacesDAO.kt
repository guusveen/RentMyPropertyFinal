package com.example.rentmyproperty.database

import androidx.room.*
import com.example.rentmyproperty.domain.FavoritePlaces

@Dao
interface FavoritePlacesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritePlace(favoritePlace: FavoritePlaces)

    @Query("SELECT * FROM favorite_places")
    suspend fun getAllFavoritePlaces(): List<FavoritePlaces>

    @Delete
    suspend fun removeFavoritePlace(favoritePlace: FavoritePlaces)
}