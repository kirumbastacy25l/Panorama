package com.kirumbastacy.panoramahotel.ui.screens.auth
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kirumbastacy.panoramahotel.R
import com.kirumbastacy.panoramahotel.navigation.ROUT_ABOUT
import com.kirumbastacy.panoramahotel.navigation.ROUT_HOME
import com.kirumbastacy.panoramahotel.navigation.ROUT_LOGIN
import com.kirumbastacy.panoramahotel.navigation.ROUT_REGISTER
import com.kirumbastacy.panoramahotel.ui.theme.green
import com.kirumbastacy.panoramahotel.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    authViewModel.loggedInUser = { user ->
        if (user != null) {
            sharedPreferences.edit().putString("logged_in_user_email", user.email).apply() // Save email
            navController.navigate(ROUT_HOME) {
                popUpTo(ROUT_LOGIN) { inclusive = true }
            }
        } else {
            Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


            Text(
                text = "Welcome Back!",
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive
            )


        Spacer(modifier = Modifier.height(24.dp))
        //Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                focusedLabelColor = green,
                unfocusedLabelColor = Color.Black

            ),

            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon", tint = green) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        //End of email
        Spacer(modifier = Modifier.height(12.dp))

        // Password Input with Show/Hide Toggle
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon", tint = green) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                val image = if (passwordVisible) painterResource(R.drawable.visibility) else painterResource(R.drawable.visibilityoff)
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password")
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Gradient Login Button
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = green
            )
        }

        Button(
            onClick = {
                isLoading = true
                authViewModel.loginUser(email, password)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = green)
        ) {
            Text("Login", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register Navigation Button
        TextButton(onClick = { navController.navigate(ROUT_REGISTER) }) {
            Text("Don't have an account? Register")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Social Media Login Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { /* Handle Google Login */ }) {
                Icon(painter = painterResource(R.drawable.img_15), contentDescription = "Google Login")
            }
            IconButton(onClick = { /* Handle Facebook Login */ }) {
                Icon(painter = painterResource(R.drawable.img_16), contentDescription = "Facebook Login")
            }
        }
    }
}