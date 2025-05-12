package com.kirumbastacy.panoramahotel.ui.screens.profile

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirumbastacy.panoramahotel.R
import com.kirumbastacy.panoramahotel.data.UserDao
import com.kirumbastacy.panoramahotel.model.User
import com.kirumbastacy.panoramahotel.navigation.ROUT_HOME
import com.kirumbastacy.panoramahotel.repository.UserRepository
import com.kirumbastacy.panoramahotel.ui.theme.green
import com.kirumbastacy.panoramahotel.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    val loggedInUserEmail = sharedPreferences.getString("logged_in_user_email", null)

    // Fetch user data
    LaunchedEffect(loggedInUserEmail) {
        if (loggedInUserEmail != null) {
            viewModel.fetchUser(loggedInUserEmail)
        }
    }

    val user by viewModel.user.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
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
        bottomBar = {
            NavigationBar(
                containerColor = green
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home", color = Color.White) },
                    selected = false,
                    onClick = { navController.navigate(ROUT_HOME) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White) },
                    label = { Text("Profile", color = Color.White) },
                    selected = true,
                    onClick = { /* Already on Profile */ }
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Picture
                Image(
                    painter = painterResource(id = R.drawable.img_22), // Replace with your placeholder image
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color.Gray)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // User Information
                Text(text = "Name: ${user?.username ?: "N/A"}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "Email: ${user?.email ?: "N/A"}", fontSize = 16.sp, color = Color.Gray)
                Text(text = "Role: ${user?.role ?: "N/A"}", fontSize = 16.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(24.dp))

                // Logout Button
                Button(
                    onClick = {
                        // Clear session and navigate to login
                        sharedPreferences.edit().clear().apply()
                        navController.navigate("login") {
                            popUpTo("profile") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = green)
                ) {
                    Text(text = "Logout", color = Color.White)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    // Mock ProfileViewModel for preview
    val mockRepository = UserRepository(object : UserDao {
        override suspend fun registerUser(user: User) {}
        override suspend fun loginUser(email: String, password: String): User? = null
        override suspend fun getUserByEmail(email: String): User? = User(
            id = 1, // Use an Int for the id
            username = "John Doe",
            email = "johndoe@example.com",
            password = "password",
            role = "Admin"
        )
    })

    val profileViewModel = ProfileViewModel(mockRepository)

    ProfileScreen(navController = rememberNavController(), viewModel = profileViewModel)
}