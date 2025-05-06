package com.kirumbastacy.panoramahotel.ui.screens.booking

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirumbastacy.panoramahotel.data.BookingDao
import com.kirumbastacy.panoramahotel.model.Booking
import com.kirumbastacy.panoramahotel.repository.BookingRepository
import com.kirumbastacy.panoramahotel.ui.theme.green
import com.kirumbastacy.panoramahotel.viewmodel.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingDetailsScreen(
    navController: NavController,
    bookingViewModel: BookingViewModel
) {
    val bookings by bookingViewModel.bookings.collectAsState()

    LaunchedEffect(Unit) {
        bookingViewModel.fetchBookings()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking Details") },
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
                    .padding(16.dp)
            ) {
                if (bookings.isEmpty()) {
                    Text("No bookings found.")
                } else {
                    bookings.forEach { booking ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Name: ${booking.fullName}")
                                Text("Contact: ${booking.contact}")
                                Text("Room Type: ${booking.roomType}")
                                Text("Check-in: ${booking.checkInDate}")
                                Text("Check-out: ${booking.checkOutDate}")
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = {
                                        bookingViewModel.selectBooking(booking)
                                        navController.navigate("edit_booking")
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = green)
                                ) {
                                    Text("Edit")
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BookingDetailsScreenPreview() {
    // Create a mock BookingDao
    val mockBookingDao = object : BookingDao {
        override suspend fun insertBooking(booking: Booking) {}
        override suspend fun getAllBookings(): List<Booking> = listOf(
            Booking(
                id = 1,
                fullName = "John Doe",
                contact = "123456789",
                roomType = "Standard Room",
                checkInDate = "2025-05-01",
                checkOutDate = "2025-05-05"
            ),
            Booking(
                id = 2,
                fullName = "Jane Smith",
                contact = "987654321",
                roomType = "Deluxe Room",
                checkInDate = "2025-05-10",
                checkOutDate = "2025-05-15"
            )
        )
        override suspend fun getBookingById(id: Int): Booking? = null
        override suspend fun updateBooking(booking: Booking) {}
        override suspend fun deleteBooking(booking: Booking) {}
    }

    // Create a mock BookingRepository
    val mockRepository = BookingRepository(mockBookingDao)

    // Create a mock BookingViewModel
    val mockViewModel = BookingViewModel(mockRepository)

    // Call the BookingDetailsScreen with the mock ViewModel
    BookingDetailsScreen(
        navController = rememberNavController(),
        bookingViewModel = mockViewModel
    )
}
