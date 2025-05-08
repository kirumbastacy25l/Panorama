package com.kirumbastacy.panoramahotel.ui.screens.booking

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kirumbastacy.panoramahotel.R
import com.kirumbastacy.panoramahotel.model.Booking
import com.kirumbastacy.panoramahotel.viewmodel.BookingViewModel
import com.kirumbastacy.panoramahotel.navigation.ROUT_ADD_BOOKING
import com.kirumbastacy.panoramahotel.navigation.ROUT_BOOKING_LIST

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookingScreen(navController: NavController, viewModel: BookingViewModel) {
    var fullName by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var roomType by remember { mutableStateOf("") }
    var checkInDate by remember { mutableStateOf("") }
    var checkOutDate by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showMenu by remember { mutableStateOf(false) }
    var isFormValid by remember { mutableStateOf(true) }

    val context = LocalContext.current

    // Image Picker Launcher
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            Log.d("ImagePicker", "Selected image URI: $it")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Booking", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Booking List") },
                            onClick = {
                                navController.navigate(ROUT_BOOKING_LIST)
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Add Booking") },
                            onClick = {
                                navController.navigate(ROUT_ADD_BOOKING)
                                showMenu = false
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar1(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Full Name
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Contact
                OutlinedTextField(
                    value = contact,
                    onValueChange = { contact = it },
                    label = { Text("Contact") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Contact") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Room Type
                OutlinedTextField(
                    value = roomType,
                    onValueChange = { roomType = it },
                    label = { Text("Room Type") },
                    leadingIcon = { Icon(Icons.Default.PlayArrow, contentDescription = "Room Type") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Check-in Date
                OutlinedTextField(
                    value = checkInDate,
                    onValueChange = { checkInDate = it },
                    label = { Text("Check-in Date") },
                    leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Check-in Date") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Check-out Date
                OutlinedTextField(
                    value = checkOutDate,
                    onValueChange = { checkOutDate = it },
                    label = { Text("Check-out Date") },
                    leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Check-out Date") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Image Picker Box
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                        .clickable { imagePicker.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUri),
                            contentDescription = "Selected Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painter = painterResource(R.drawable.image), contentDescription = "Pick Image")
                            Text("Tap to pick image", color = Color.DarkGray)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Add Booking Button
                Button(
                    onClick = {
                        // Validate form
                        isFormValid = fullName.isNotEmpty() && contact.isNotEmpty() && roomType.isNotEmpty() &&
                                checkInDate.isNotEmpty() && checkOutDate.isNotEmpty()

                        if (isFormValid) {
                            val newBooking = Booking(
                                fullName = fullName,
                                contact = contact,
                                roomType = roomType,
                                checkInDate = checkInDate,
                                checkOutDate = checkOutDate,
                                imagePath = imageUri?.toString() ?: ""
                            )
                            viewModel.addBooking(newBooking)
                            Toast.makeText(context, "Booking Added!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray)
                ) {
                    Text("Add Booking", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                // Show error message if form is invalid
                if (!isFormValid) {
                    Text(
                        text = "Please fill out all fields.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    )
}

// Bottom Navigation Bar Component
@Composable
fun BottomNavigationBar1(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF6F6A72),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_BOOKING_LIST) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Booking List") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_BOOKING) },
            icon = { Icon(Icons.Default.AddCircle, contentDescription = "Add Booking") },
            label = { Text("Add") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_ADD_BOOKING) },
            icon = { Icon(painter = painterResource(R.drawable.register), contentDescription = "") },
            label = { Text("Profile") }
        )
    }
}
