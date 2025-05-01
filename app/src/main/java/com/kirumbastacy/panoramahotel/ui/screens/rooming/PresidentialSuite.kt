package com.kirumbastacy.panoramahotel.ui.screens.rooming


import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
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
import com.kirumbastacy.panoramahotel.navigation.ROUT_HOME
import com.kirumbastacy.panoramahotel.ui.theme.VeryWhite
import com.kirumbastacy.panoramahotel.ui.theme.White
import com.kirumbastacy.panoramahotel.ui.theme.green


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresidentialSuiteScreen(navController: NavController){
    //Scaffold

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        //TopBar
        topBar = {
            TopAppBar(
                title = { Text("Presidential Suite") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back/nav */ }) {
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

        //BottomBar
        bottomBar = {
            NavigationBar(
                containerColor = green
            ) {
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
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        // navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        //  navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Info") },
                    label = { Text("Info") },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        // navController.navigate(ROUT_HOME)
                    }
                )


            }
        },

        //FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = green
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        //Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                //Card1
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)

                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(R.drawable.img_1),
                            contentDescription = "home",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
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
                            text = "Presidential Suite",
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                        )
                        Text(
                            text = "Ksh.50000 per night",
                            modifier = Modifier
                                .padding(start = 8.dp, top = 4.dp, bottom = 8.dp)
                        )
                    }
                }
                //End of card 1
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(VeryWhite)
                ) {
                    Text(
                        text = "Description",
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                    Row {
                        Text(
                            text = "Pros :",
                            Modifier.padding(start = 10.dp, end = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Great View",)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Very Spacious",)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Privacy",)

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text(
                            text = "Cons :",
                            Modifier.padding(start = 10.dp, end = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Expensive",)
                        Spacer(modifier = Modifier.width(10.dp))


                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(green),
                        shape = RoundedCornerShape(size = 10.dp),
                        modifier = Modifier.width(400.dp).padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = "Book Now",
                            color = White,
                            fontSize = 10.sp
                        )
                    }
                    //End of button


                }


            }
        }
    )

    //End of scaffold



























}

@Preview(showBackground = true)
@Composable
fun PresidentialSuiteScreenPreview(){
    PresidentialSuiteScreen(rememberNavController())


}
