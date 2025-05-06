package com.kirumbastacy.panoramahotel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.kirumbastacy.panoramahotel.model.Booking
import com.kirumbastacy.panoramahotel.repository.BookingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookingViewModel(private val repository: BookingRepository) : ViewModel() {

    private val _bookings = MutableStateFlow<List<Booking>>(emptyList())
    val bookings: StateFlow<List<Booking>> = _bookings

    private val _selectedBooking = MutableStateFlow<Booking?>(null)
    val selectedBooking: StateFlow<Booking?> = _selectedBooking

    // Fetch all bookings
    fun fetchBookings() {
        viewModelScope.launch {
            _bookings.value = repository.getAllBookings()
        }
    }

    // Save a booking
    fun saveBooking(booking: Booking) {
        viewModelScope.launch {
            repository.insertBooking(booking)
            fetchBookings() // Refresh the list
        }
    }

    // Select a booking for editing
    fun selectBooking(booking: Booking) {
        _selectedBooking.value = booking
    }

    // Update a booking
    fun updateBooking(booking: Booking) {
        viewModelScope.launch {
            repository.updateBooking(booking)
            fetchBookings() // Refresh the list
        }
    }

    // Delete a booking
    fun deleteBooking(booking: Booking) {
        viewModelScope.launch {
            repository.deleteBooking(booking)
            fetchBookings() // Refresh the list
        }
    }
}
