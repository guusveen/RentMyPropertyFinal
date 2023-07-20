package com.example.rentmyproperty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rentmyproperty.domain.*

@Database(entities = [
    Reservation::class,
    Tenant::class,
    Property::class,
    Owner::class,
    Review::class,
    FavoritePlaces::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class RentMyPropertyDatabase : RoomDatabase() {
    abstract fun reservationDAO(): ReservationDAO
    abstract fun propertyDAO(): PropertyDAO
    abstract fun ownerDAO(): OwnerDAO
    abstract fun tenantDAO(): TenantDAO
    abstract fun reviewDAO(): ReviewDao
    abstract fun favoritePlacesDAO(): FavoritePlacesDAO

    // We only want one instance of the database
    // so we create a singleton using a companion object
    companion object {
        @Volatile
        private var INSTANCE: RentMyPropertyDatabase? = null

        fun getDatabase(context: Context): RentMyPropertyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RentMyPropertyDatabase::class.java,
                    "rentmyproperty_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}