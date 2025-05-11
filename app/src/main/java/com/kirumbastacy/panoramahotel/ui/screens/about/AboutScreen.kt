package com.kirumbastacy.panoramahotel.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.kirumbastacy.panoramahotel.navigation.ROUT_BOOKING_LIST
import com.kirumbastacy.panoramahotel.navigation.ROUT_CONTACT
import com.kirumbastacy.panoramahotel.navigation.ROUT_DELUXE
import com.kirumbastacy.panoramahotel.navigation.ROUT_HOME
import com.kirumbastacy.panoramahotel.ui.theme.VeryWhite
import com.kirumbastacy.panoramahotel.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        // TopBar
        topBar = {
            TopAppBar(
                title = { Text("About Us") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        androidx.compose.material3.Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
                    icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home",) },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.DateRange, contentDescription = "Favorites",tint = Color.White) },
                    label = { Text("Bookings", color = VeryWhite) },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        navController.navigate(ROUT_BOOKING_LIST)}
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Phone, contentDescription = "Profile",tint = Color.White) },
                    label = { Text("Contact", color = VeryWhite) },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        navController.navigate(ROUT_CONTACT)}
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Info, contentDescription = "Info",tint = Color.White) },
                    label = { Text("About", color = VeryWhite) },
                    selected = selectedIndex == 3,
                    onClick = { selectedIndex = 3
                        navController.navigate(ROUT_ABOUT)}
                )
            }
        },

        // Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Hotel Name
                Text(
                    text = "Welcome to Panorama Hotel",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = green,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Hotel Description
                Text(
                    text = "Panorama Hotel is a luxurious and serene getaway located in the heart of Kenya. " +
                            "We pride ourselves on offering world-class hospitality, breathtaking views, and " +
                            "unparalleled comfort to our guests. Whether you're here for business or leisure, " +
                            "Panorama Hotel is the perfect destination for a memorable stay.",
                    fontSize = 16.sp,
                     fontWeight = FontWeight.Bold,

                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Location
                Text(
                    text = "Location",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = green,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Panorama Hotel is located in Nairobi, Kenya, surrounded by stunning landscapes " +
                            "and close to major attractions such as the Nairobi National Park and the Giraffe Centre.",
                    fontSize = 16.sp,
                    color = Color.Gray, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Amenities
                Text(
                    text = "Amenities",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = green,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Column {
                    Text("• Luxurious rooms and suites", fontSize = 16.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Text("• Fine dining restaurants", fontSize = 16.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Text("• State-of-the-art conference facilities", fontSize = 16.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Text("• Spa and wellness center", fontSize = 16.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Text("• Swimming pool with panoramic views", fontSize = 16.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(20.dp))

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = RoundedCornerShape(12.dp)) {
                    Image(
                        painter = painterResource(R.drawable.img_18),
                        contentDescription = "home",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .clickable {
                                navController.navigate(ROUT_DELUXE)
                            },
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Luxurious Dining",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = RoundedCornerShape(12.dp)) {
                    Image(
                        painter = painterResource(R.drawable.img_19),
                        contentDescription = "home",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .clickable {
                                navController.navigate(ROUT_DELUXE)
                            },
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Spa and Wellness",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = RoundedCornerShape(12.dp)) {
                    Image(
                        painter = painterResource(R.drawable.img_20),
                        contentDescription = "home",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .clickable {
                                navController.navigate(ROUT_DELUXE)
                            },
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Swimming Pool and Recreation",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = RoundedCornerShape(12.dp)) {
                    Image(
                        painter = painterResource(R.drawable.img_21),
                        contentDescription = "home",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .clickable {
                                navController.navigate(ROUT_DELUXE)
                            },
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Conference Centre",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))




            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(navController = rememberNavController())
}