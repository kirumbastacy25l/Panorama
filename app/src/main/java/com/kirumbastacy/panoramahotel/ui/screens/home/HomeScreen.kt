package com.kirumbastacy.panoramahotel.ui.screens.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.text.style.TextAlign
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
import com.kirumbastacy.panoramahotel.navigation.ROUT_ROOMS
import com.kirumbastacy.panoramahotel.ui.theme.LightBrown
import com.kirumbastacy.panoramahotel.ui.theme.VeryWhite
import com.kirumbastacy.panoramahotel.ui.theme.black
import com.kirumbastacy.panoramahotel.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    //Scaffold

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        //TopBar
        topBar = {
            TopAppBar(
                title = { Text("PANORAMA HOTEL HOME PAGE", fontWeight = FontWeight.SemiBold) },

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
                    icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home", color = VeryWhite) },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                        navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.DateRange, contentDescription = "Bookings",tint = Color.White) },
                    label = { Text("Bookings", color = VeryWhite) },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                         navController.navigate(ROUT_BOOKING_LIST)
                    }
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Call, contentDescription = "Profile",tint = Color.White) },
                    label = { Text("Contact", color = VeryWhite) },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                          navController.navigate(ROUT_CONTACT)
                    }
                )
                NavigationBarItem(
                    icon = { androidx.compose.material3.Icon(Icons.Default.Info, contentDescription = "Info",tint = Color.White) },
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
            ) {
                Box (modifier = Modifier.fillMaxSize()){

                        Image(
                        painter = painterResource(R.drawable.mod),
                        contentDescription = "home",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()

                        )
                    Column(modifier = Modifier.fillMaxSize()
                        .padding(24.dp)
                        ,
                        verticalArrangement = Arrangement.Top

                        ) {
                        Text(
                            text = "Welcome,\nJohn",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(50.dp))



                        Spacer(modifier = Modifier.height(32.dp))


                        Column(modifier = Modifier.padding(top = 100.dp)) {
                            Card ( modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clickable{navController.navigate(ROUT_ROOMS)},
                                elevation = CardDefaults.cardElevation(10.dp)
                            )
                            {
                                Row( modifier = Modifier.padding(start = 16.dp, end = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = R.drawable.img_5),
                                        contentDescription = "Bed Icon",
                                        modifier = Modifier
                                            .size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "Book A Room",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = LightBrown,
                                        textAlign = TextAlign.Center
                                    )


                                }







                            }
                            //End of card 1
                            Spacer(modifier = Modifier.height(15.dp))
                            Card ( modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clickable{navController.navigate(ROUT_BOOKING_LIST)},
                                elevation = CardDefaults.cardElevation(10.dp)
                            )
                            {
                                Row (  verticalAlignment = Alignment.CenterVertically){
                                    Image(
                                        painter = painterResource(id = R.drawable.img_6),
                                        contentDescription = "Book Icon",
                                        modifier = Modifier
                                            .size(50.dp)
                                            .padding(start = 8.dp, )
                                            ,
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "My Bookings",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = LightBrown,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                                    )


                                }







                            }
                            //End of card 2
                            Spacer(modifier = Modifier.height(15.dp))
                            Card ( modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clickable{navController.navigate(ROUT_ROOMS)},
                                elevation = CardDefaults.cardElevation(10.dp)
                            )
                            {
                                Row ( modifier = Modifier.padding(start = 16.dp, end = 16.dp),verticalAlignment = Alignment.CenterVertically){
                                    Image(
                                        painter = painterResource(id = R.drawable.img_14),
                                        contentDescription = "Bed Icon",
                                        modifier = Modifier
                                            .size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(
                                        text = "About ",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = LightBrown,
                                        textAlign = TextAlign.Center
                                    )


                                }







                            }
                            //End of card 3
                            Spacer(modifier = Modifier.height(15.dp))
                            Card ( modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clickable{navController.navigate(ROUT_CONTACT)},
                                elevation = CardDefaults.cardElevation(10.dp)
                            )
                            {
                                Row (modifier = Modifier.padding(start = 16.dp, end = 16.dp),  verticalAlignment = Alignment.CenterVertically){
                                    Image(
                                        painter = painterResource(id = R.drawable.img_7),
                                        contentDescription = "Bed Icon",
                                        modifier = Modifier
                                            .size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "Contact Reception",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = LightBrown,
                                        textAlign = TextAlign.Center
                                    )


                                }







                            }
                            //End of card 4
                            Spacer(modifier = Modifier.height(15.dp))

                        }




                    }







                }
















            }
        }
    )

    //End of scaffold





}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController= rememberNavController())
}