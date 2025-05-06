package com.kirumbastacy.panoramahotel.data

import androidx.room.*
import com.kirumbastacy.panoramahotel.model.Booking

@Dao
interface BookingDao {
    @Insert
    suspend fun insertBooking(booking: Booking)

    @Query("SELECT * FROM bookings")
    suspend fun getAllBookings(): List<Booking>

    @Query("SELECT * FROM bookings WHERE id = :id")
    suspend fun getBookingById(id: Int): Booking?

    @Update
    suspend fun updateBooking(booking: Booking)

    @Delete
    suspend fun deleteBooking(booking: Booking)
}