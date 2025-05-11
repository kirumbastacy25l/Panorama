package com.kirumbastacy.panoramahotel.ui.screens.rooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import com.kirumbastacy.panoramahotel.navigation.ROUT_PRESIDENTIAL
import com.kirumbastacy.panoramahotel.navigation.ROUT_STANDARD
import com.kirumbastacy.panoramahotel.navigation.ROUT_SUITE
import com.kirumbastacy.panoramahotel.ui.theme.VeryWhite
import com.kirumbastacy.panoramahotel.ui.theme.black
import com.kirumbastacy.panoramahotel.ui.theme.green


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(navController: NavController){
    //Scaffold

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        //TopBar
        topBar = {
            TopAppBar(
                title = { Text("Rooms") },
                navigationIcon = {
                    IconButton(onClick = { { navController.popBackStack() } }) {
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

        //BottomBar
        bottomBar = {
            NavigationBar(
                containerColor = green
            ){
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home", ) },
                    label = { Text("Home", color = Color.White) },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                        navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.DateRange, contentDescription = "Favorites", tint = Color.White) },
                    label = { Text("Bookings", color = VeryWhite) },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                         navController.navigate(ROUT_BOOKING_LIST)
                    }
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Phone, contentDescription = "Profile", tint = Color.White) },
                    label = { Text("Contact", color = VeryWhite) },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                          navController.navigate(ROUT_CONTACT)
                    }
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Info, contentDescription = "Info", tint = Color.White) },
                    label = { Text("About", color = VeryWhite) },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                         navController.navigate(ROUT_ABOUT)
                    }
                )




            }
        },

       
        //Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())

            ) {
                //Card1
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
                            painter = painterResource(R.drawable.img),
                            contentDescription = "home",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clickable {
                                    navController.navigate(ROUT_STANDARD)
                                },
                            contentScale = ContentScale.Crop

                        )
                        Row {
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)

                        }
                        Text(
                            text = "Standard Room",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                        )
                        Text(
                            text = "Ksh.7000 per night",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 4.dp, bottom = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))


                    }
                }
                //End of card 1

                Spacer(modifier = Modifier.height(20.dp))

                //Card2
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(R.drawable.img_2),
                            contentDescription = "home",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clickable {
                                    navController.navigate(ROUT_DELUXE)
                                },
                            contentScale = ContentScale.Crop
                        )
                        Row {
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)

                        }

                        Text(
                            text = "Deluxe Room",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                        )
                        Text(
                            text = "Ksh.15000 per night",
                            fontWeight = FontWeight.Bold,

                            modifier = Modifier
                                .padding(start = 8.dp, top = 4.dp, bottom = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                    }
                }
                //End of card 2
                Spacer(modifier = Modifier.height(20.dp))

                //Card3
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(R.drawable.lux),
                            contentDescription = "home",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clickable {
                                    navController.navigate(ROUT_SUITE)
                                },
                            contentScale = ContentScale.Crop
                        )
                        Row {
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = VeryWhite)
                        }
                        Text(
                            text = "Suite Room",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                        )
                        Text(
                            text = "Ksh.30000 per night",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 4.dp, bottom = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                    }
                }
                //End of card 3
                Spacer(modifier = Modifier.height(20.dp))
                //Card4
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(R.drawable.img_1),
                            contentDescription = "home",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clickable {
                                    navController.navigate(ROUT_PRESIDENTIAL)
                                },
                            contentScale = ContentScale.Crop
                        )
                        Row {
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green )
                            Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = green)
                        }
                        Text(
                            text = "Presidential Suite",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                        )
                        Text(
                            text = "Ksh.50000 per night",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 4.dp, bottom = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))


                    }
                }
                //End of card 4


















            }
            //End of main column
        }
    )

    //End of scaffold









}

@Preview(showBackground = true)
@Composable
fun RoomScreenPreview(){
    RoomScreen(navController = rememberNavController())
}