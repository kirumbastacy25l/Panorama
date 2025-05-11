package com.kirumbastacy.panoramahotel.ui.screens.contact

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.kirumbastacy.panoramahotel.navigation.ROUT_HOME
import com.kirumbastacy.panoramahotel.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        // TopBar
        topBar = {
            TopAppBar(
                title = { Text("Contact Us") },
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
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Bookings", tint = Color.White) },
                    label = { Text("Bookings", color = Color.White) },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(ROUT_BOOKING_LIST)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Phone, contentDescription = "Profile", tint = Color.White) },
                    label = { Text("Contact",color = Color.White) },
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        navController.navigate(ROUT_CONTACT)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "About", tint = Color.White) },
                    label = { Text("About",color = Color.White) },
                    selected = selectedIndex == 3,
                    onClick = {
                        selectedIndex = 3
                        navController.navigate(ROUT_ABOUT)
                    }
                )
            }
        },

        // Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Title
                Text(
                    text = "Contact Us",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Address Section
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.img_9),
                        contentDescription = "Location Icon",
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Address",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "123 Beach Road, Panorama City, California",
                            fontSize = 16.sp,
                            modifier = Modifier.clickable {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("geo:0,0?q=123 Beach Road, Panorama City, California")
                                )
                                navController.context.startActivity(intent)
                            },
                            color = Color.Blue
                        )
                    }
                }

                // Phone Section
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.img_7),
                        contentDescription = "Phone Icon",
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Contact",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "+254 0114 386 232",
                            fontSize = 16.sp,
                            color = Color.Blue,
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:+254114386232")
                                }
                                navController.context.startActivity(intent)
                            }
                        )
                    }
                }

                // Email Section
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.img_10),
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Email",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "panoramahotel@gmail.com",
                            fontSize = 16.sp,
                            color = Color.Blue,
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:panoramahotel@gmail.com")
                                }
                                navController.context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ContactScreenPreview() {
    ContactScreen(navController = rememberNavController())
}