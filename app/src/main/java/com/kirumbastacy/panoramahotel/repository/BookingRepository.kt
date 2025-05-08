package com.kirumbastacy.panoramahotel.repository
import android.content.Context
import com.kirumbastacy.panoramahotel.data.BookingDataBase
import com.kirumbastacy.panoramahotel.model.Booking

class BookingRepository(context: Context) {
    private val bookingDao = BookingDataBase.getDatabase(context).bookingDao()

    suspend fun insertBooking(booking: Booking) {
        bookingDao.insertBooking(booking)
    }
    // Update a booking
    suspend fun updateBooking(booking: Booking) {
        bookingDao.updateBooking(booking)
    }


    fun getAllBookings() = bookingDao.getAllBookings()

    suspend fun deleteBooking(booking: Booking) = bookingDao.deleteBooking(booking)
}