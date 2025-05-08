package com.kirumbastacy.panoramahotel.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kirumbastacy.panoramahotel.model.Booking
import com.kirumbastacy.panoramahotel.repository.BookingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class BookingViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val bookingRepository = BookingRepository(app)  // Use the repository instead of DAO

    // LiveData to observe bookings
    val allBookings: LiveData<List<Booking>> = bookingRepository.getAllBookings()

    // Add a new booking
    fun addBooking(booking: Booking) {
        viewModelScope.launch(Dispatchers.IO) {
            // Save the image and get the internal storage path
            val savedImagePath = saveImageToInternalStorage(Uri.parse(booking.imagePath))
            val newBooking = booking.copy(imagePath = savedImagePath) // Replace the original path with the saved one
            bookingRepository.insertBooking(newBooking)  // Use repository to insert booking
        }
    }

    // Update an existing booking
    fun updateBooking(updatedBooking: Booking) {
        viewModelScope.launch(Dispatchers.IO) {
            // Update booking in the database
            bookingRepository.updateBooking(updatedBooking)
        }
    }

    // Delete a booking
    fun deleteBooking(booking: Booking) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete the image from internal storage
            deleteImageFromInternalStorage(booking.imagePath)
            // Delete the booking from the database
            bookingRepository.deleteBooking(booking)
        }
    }

    // Save image to internal storage
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }

    // Delete the image from internal storage
    private fun deleteImageFromInternalStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
