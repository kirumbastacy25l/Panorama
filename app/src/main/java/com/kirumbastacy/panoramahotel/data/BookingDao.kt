package com.kirumbastacy.panoramahotel.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kirumbastacy.panoramahotel.model.Booking
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {
    @Query("SELECT * FROM bookings")
    fun getAllBookings(): LiveData<List<Booking>>

    @Insert
    suspend fun insertBooking(booking: Booking)

    @Update
    suspend fun updateBooking(booking: Booking)

    @Delete
    suspend fun deleteBooking(booking: Booking)
}