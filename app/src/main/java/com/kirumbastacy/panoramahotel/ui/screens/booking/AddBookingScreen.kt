package com.kirumbastacy.panoramahotel.ui.screens.booking

import android.app.DatePickerDialog
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kirumbastacy.panoramahotel.R
import com.kirumbastacy.panoramahotel.model.Booking
import com.kirumbastacy.panoramahotel.viewmodel.BookingViewModel
import com.kirumbastacy.panoramahotel.navigation.ROUT_ADD_BOOKING
import com.kirumbastacy.panoramahotel.navigation.ROUT_BOOKING_LIST
import com.kirumbastacy.panoramahotel.navigation.ROUT_CONFIRM
import com.kirumbastacy.panoramahotel.navigation.ROUT_HOME
import com.kirumbastacy.panoramahotel.ui.theme.green
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookingScreen(navController: NavController, viewModel: BookingViewModel) {
    val roomTypes = listOf("Standard Room", "Deluxe Room", "Suite", "Presidential Suite")
    var selectedRoomType by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var roomType by remember { mutableStateOf("") }
    var checkInDate by remember { mutableStateOf("") }
    var checkOutDate by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showMenu by remember { mutableStateOf(false) }
    var isFormValid by remember { mutableStateOf(true) }
    val bookingViewModel: BookingViewModel = viewModel()

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
                title = { Text("Make Booking", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(green),
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
                    leadingIcon = {
                        Icon(Icons.Filled.Person, contentDescription = "Full Name Icon", tint = green)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = green,
                        unfocusedLabelColor = Color.Black
                    )
                )


                Spacer(modifier = Modifier.height(10.dp))

                // Contact
                OutlinedTextField(
                    value = contact,
                    onValueChange = { contact = it },
                    label = { Text("Contact") },
                    leadingIcon = {
                        Icon(Icons.Filled.Phone, contentDescription = "Contact Icon", tint = green)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = green,
                        unfocusedLabelColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
                Spacer(modifier = Modifier.height(10.dp))
                // Room Type Dropdown
                var expanded by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = roomType,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Room Type") },
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Dropdown Icon")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = green,
                        unfocusedLabelColor = Color.Black
                    )
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    roomTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                roomType = type
                                expanded = false
                            }
                        )
                    }
                }



                Spacer(modifier = Modifier.height(10.dp))

                // Check-in Date
                DatePickerField(
                    label = "Check-in Date",
                    selectedDate = checkInDate,
                    onDateSelected = { checkInDate = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = green,
                        unfocusedLabelColor = Color.Black
                    )
                )



                Spacer(modifier = Modifier.height(10.dp))

                // Check-out Date
                DatePickerField(
                    label = "Check-out Date",
                    selectedDate = checkOutDate,
                    onDateSelected = { checkOutDate = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        focusedLabelColor = green,
                        unfocusedLabelColor = Color.Black
                    )
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
                            Icon(painter = painterResource(R.drawable.image), contentDescription = "Guest Image")
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
                            viewModel.addBooking(newBooking) // Add booking to the database
                            Toast.makeText(context, "Booking Successfully Made!", Toast.LENGTH_SHORT).show()

                            // Navigate to the booking list screen after successful booking
                            navController.navigate(ROUT_CONFIRM) {
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            }
                        } else {
                            // Show a toast message if the form is invalid
                            Toast.makeText(context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(green)
                ) {
                    Text("Book Now", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}

@Composable
fun DatePickerField(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier,
    colors: TextFieldColors
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        trailingIcon = {
            IconButton(onClick = {
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        calendar.set(year, month, dayOfMonth)
                        onDateSelected("${dayOfMonth}/${month + 1}/${year}")
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Icon(Icons.Default.DateRange, contentDescription = "Select Date")
            }
        },
        modifier = modifier,
        colors = colors
    )
}


// Bottom Navigation Bar Component
@Composable
fun BottomNavigationBar1(navController: NavController) {
    NavigationBar(
        containerColor = green,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_HOME) },
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
