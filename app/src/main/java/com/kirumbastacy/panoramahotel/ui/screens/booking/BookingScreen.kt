package com.kirumbastacy.panoramahotel.ui.screens.booking
import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirumbastacy.panoramahotel.navigation.ROUT_CONFIRM
import com.kirumbastacy.panoramahotel.navigation.ROUT_DETAILS
import com.kirumbastacy.panoramahotel.ui.theme.black
import com.kirumbastacy.panoramahotel.ui.theme.green
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController) {
    val context = LocalContext.current

    // State variables
    var fullName by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var numberOfGuests by remember { mutableStateOf(1) }
    var roomType by remember { mutableStateOf("Single Room") }
    var checkInDate by remember { mutableStateOf("") }
    var checkOutDate by remember { mutableStateOf("") }

    // Room types
    val roomTypes = listOf("Standard Room", "Deluxe Room", "Suite", "Presidential Suite")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book a Room", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = green)
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = green,
                content = {
                    Text(
                        text = "Enjoy your stay!",
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5)) // Light background color
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Full Name
                TextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Username Icon",
                            tint = green
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = black,
                        focusedLabelColor = green,
                        unfocusedLabelColor = Color.Black
                    )
                )

                // Contact
                TextField(
                    value = contact,
                    onValueChange = { contact = it },
                    label = { Text("Contact") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Phone,
                            contentDescription = "Contact Icon",
                            tint = green
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = black,
                        focusedLabelColor = green,
                        unfocusedLabelColor = Color.Black
                    )
                )

                // Room Type Dropdown
                var expanded by remember { mutableStateOf(false) }
                TextField(
                    value = roomType,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Room Type") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.KeyboardArrowDown,
                            contentDescription = "Room Type Icon",
                            tint = green
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Dropdown Icon")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = black,
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

                // Check-in Date
                DatePickerField(
                    label = "Check-in Date",
                    selectedDate = checkInDate,
                    onDateSelected = { checkInDate = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = black,
                        focusedLabelColor = green,
                        unfocusedLabelColor = Color.Black
                    )
                )

                // Check-out Date
                DatePickerField(
                    label = "Check-out Date",
                    selectedDate = checkOutDate,
                    onDateSelected = { checkOutDate = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = black,
                        focusedLabelColor = green,
                        unfocusedLabelColor = Color.Black
                    )
                )

                // Submit Button
                Button(
                    onClick = {
                        navController.navigate(ROUT_DETAILS)
                        if (fullName.isNotEmpty() && contact.isNotEmpty() && checkInDate.isNotEmpty() && checkOutDate.isNotEmpty()) {
                            Toast.makeText(context, "Booking Successful!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = green)
                ) {
                    Text("Book Now", color = Color.White)
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

    TextField(
        value = selectedDate,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        leadingIcon = {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Date Icon",
                tint = green
            )
        },
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

@Preview(showBackground = true)
@Composable
fun BookingScreenPreview() {
    BookingScreen(navController = rememberNavController())
}