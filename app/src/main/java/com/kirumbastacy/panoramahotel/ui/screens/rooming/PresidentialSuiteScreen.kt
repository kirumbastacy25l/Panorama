package com.kirumbastacy.panoramahotel.ui.screens.rooming

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirumbastacy.panoramahotel.R
import com.kirumbastacy.panoramahotel.navigation.ROUT_ABOUT
import com.kirumbastacy.panoramahotel.navigation.ROUT_ADD_BOOKING
import com.kirumbastacy.panoramahotel.navigation.ROUT_BOOKING_LIST
import com.kirumbastacy.panoramahotel.navigation.ROUT_CONTACT
import com.kirumbastacy.panoramahotel.navigation.ROUT_HOME
import com.kirumbastacy.panoramahotel.ui.theme.VeryWhite
import com.kirumbastacy.panoramahotel.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresidentialSuiteScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        // TopBar
        topBar = {
            TopAppBar(
                title = { Text("Presidential Suite") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = green,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        // BottomBar
        bottomBar = {
            NavigationBar(containerColor = green) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Favorites") },
                    label = { Text("Bookings") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        navController.navigate(ROUT_BOOKING_LIST)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Phone, contentDescription = "Profile") },
                    label = { Text("Contact") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        navController.navigate(ROUT_CONTACT)}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Info") },
                    label = { Text("About") },
                    selected = selectedIndex == 3,
                    onClick = { selectedIndex = 3
                        navController.navigate(ROUT_ABOUT)}
                )
            }
        },

        // FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = green
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },

        // Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Room Image and Details
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(R.drawable.img_1),
                            contentDescription = "Presidential Suite",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentScale = ContentScale.Crop
                        )
                        Row(
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(5) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Star",
                                    tint = green
                                )
                            }
                        }
                        Text(
                            text = "Presidential Suite",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                        )
                        Text(
                            text = "Ksh. 50,000 per night",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 8.dp)
                        )
                    }
                }

                // Room Description
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Description",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "The Presidential Suite offers unparalleled luxury, with expansive living spaces, world-class amenities, and breathtaking views. Perfect for VIPs and those seeking the ultimate indulgence.",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                // Pros and Cons
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Pros",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Column {
                            Text("• Expansive and luxurious living spaces", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                            Text("• Private butler service", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                            Text("• Panoramic views of the city", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                            Text("• Exclusive access to VIP amenities", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Cons",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Column {
                            Text("• Extremely high price point", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                            Text("• Limited availability", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Book Now Button
                Button(
                    onClick = {
                        navController.navigate(ROUT_ADD_BOOKING)
                    },
                    colors = ButtonDefaults.buttonColors(green),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Book Now",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PresidentialSuiteScreenPreview() {
    PresidentialSuiteScreen(rememberNavController())
}
