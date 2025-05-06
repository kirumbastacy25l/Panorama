package com.kirumbastacy.panoramahotel.ui.screens.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.collectAsState
import com.kirumbastacy.panoramahotel.data.BookingDao
import com.kirumbastacy.panoramahotel.model.Booking
import com.kirumbastacy.panoramahotel.repository.BookingRepository
import com.kirumbastacy.panoramahotel.ui.theme.green
import com.kirumbastacy.panoramahotel.viewmodel.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBookingScreen(
    navController: NavController,
    bookingViewModel: BookingViewModel
) {
    val selectedBooking by bookingViewModel.selectedBooking.collectAsState()

    var fullName by remember { mutableStateOf(selectedBooking?.fullName ?: "") }
    var contact by remember { mutableStateOf(selectedBooking?.contact ?: "") }
    var roomType by remember { mutableStateOf(selectedBooking?.roomType ?: "") }
    var checkInDate by remember { mutableStateOf(selectedBooking?.checkInDate ?: "") }
    var checkOutDate by remember { mutableStateOf(selectedBooking?.checkOutDate ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Booking") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = contact,
                    onValueChange = { contact = it },
                    label = { Text("Contact") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = roomType,
                    onValueChange = { roomType = it },
                    label = { Text("Room Type") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = checkInDate,
                    onValueChange = { checkInDate = it },
                    label = { Text("Check-in Date") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = checkOutDate,
                    onValueChange = { checkOutDate = it },
                    label = { Text("Check-out Date") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        val updatedBooking = selectedBooking?.copy(
                            fullName = fullName,
                            contact = contact,
                            roomType = roomType,
                            checkInDate = checkInDate,
                            checkOutDate = checkOutDate
                        )
                        if (updatedBooking != null) {
                            bookingViewModel.updateBooking(updatedBooking)
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = green)
                ) {
                    Text("Save Changes")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EditBookingScreenPreview() {
    // Create a dummy BookingDao
    val dummyBookingDao = object : BookingDao {
        override suspend fun insertBooking(booking: Booking) {}
        override suspend fun getAllBookings(): List<Booking> = listOf(
            Booking(
                id = 1,
                fullName = "John Doe",
                contact = "123456789",
                roomType = "Single Room",
                checkInDate = "2025-05-01",
                checkOutDate = "2025-05-05"
            )
        )
        override suspend fun getBookingById(id: Int): Booking = Booking(
            id = 1,
            fullName = "John Doe",
            contact = "123456789",
            roomType = "Single Room",
            checkInDate = "2025-05-01",
            checkOutDate = "2025-05-05"
        )
        override suspend fun updateBooking(booking: Booking) {}
        override suspend fun deleteBooking(booking: Booking) {}
    }

    // Create a dummy BookingRepository
    val dummyRepository = BookingRepository(dummyBookingDao)

    // Create a dummy BookingViewModel
    val dummyViewModel = BookingViewModel(dummyRepository)

    // Call the EditBookingScreen with the dummy ViewModel
    EditBookingScreen(
        navController = rememberNavController(),
        bookingViewModel = dummyViewModel
    )
}
