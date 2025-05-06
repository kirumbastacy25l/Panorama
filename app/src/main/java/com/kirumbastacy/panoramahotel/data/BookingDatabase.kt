package com.kirumbastacy.panoramahotel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kirumbastacy.panoramahotel.model.Booking

@Database(entities = [Booking::class], version = 1, exportSchema = false)
abstract class BookingDatabase : RoomDatabase() {
    abstract fun bookingDao(): BookingDao
}

object DatabaseProvider {
    @Volatile
    private var INSTANCE: BookingDatabase? = null

    fun getDatabase(context: Context): BookingDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BookingDatabase::class.java,
                "booking_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
