package com.kirumbastacy.panoramahotel.repository


import com.kirumbastacy.panoramahotel.data.BookingDao
import com.kirumbastacy.panoramahotel.model.Booking

open class BookingRepository(private val bookingDao: BookingDao) {

    // Insert a booking into the database
    suspend fun insertBooking(booking: Booking) {
        bookingDao.insertBooking(booking)
    }

    // Retrieve all bookings from the database
    suspend fun getAllBookings(): List<Booking> {
        return bookingDao.getAllBookings()
    }

    // Retrieve a single booking by ID
    suspend fun getBookingById(id: Int): Booking? {
        return bookingDao.getBookingById(id)
    }

    // Update a booking
    suspend fun updateBooking(booking: Booking) {
        bookingDao.updateBooking(booking)
    }

    // Delete a booking
    suspend fun deleteBooking(booking: Booking) {
        bookingDao.deleteBooking(booking)
    }
}



