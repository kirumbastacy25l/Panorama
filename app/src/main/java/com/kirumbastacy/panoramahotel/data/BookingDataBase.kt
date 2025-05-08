package com.kirumbastacy.panoramahotel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kirumbastacy.panoramahotel.model.Booking
import com.kirumbastacy.panoramahotel.model.User

@Database(entities = [Booking::class, User::class], version = 3, exportSchema = false)
abstract class BookingDataBase : RoomDatabase() {
    abstract fun bookingDao(): BookingDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE:BookingDataBase? = null

        fun getDatabase(context: Context): BookingDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookingDataBase::class.java,
                    "main_database"
                )
                    .fallbackToDestructiveMigration() // 💥 This clears DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}