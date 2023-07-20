package com.example.rentmyproperty.database

import androidx.room.*
import com.example.rentmyproperty.domain.Review

@Dao
interface ReviewDao {
    @Insert
    suspend fun insertReview(review: Review)

    @Query("SELECT * FROM reviews")
    suspend fun getAllReviews(): List<Review>
}
