package com.kirumbastacy.panoramahotel.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kirumbastacy.panoramahotel.data.UserDatabase
import com.kirumbastacy.panoramahotel.repository.UserRepository
import com.kirumbastacy.panoramahotel.ui.screens.about.AboutScreen
import com.kirumbastacy.panoramahotel.ui.screens.auth.LoginScreen
import com.kirumbastacy.panoramahotel.ui.screens.auth.RegisterScreen
import com.kirumbastacy.panoramahotel.ui.screens.contact.ContactScreen
import com.kirumbastacy.panoramahotel.ui.screens.home.HomeScreen
import com.kirumbastacy.panoramahotel.ui.screens.pay.PaymentScreen
import com.kirumbastacy.panoramahotel.ui.screens.rooming.DeluxeRoomScreen
import com.kirumbastacy.panoramahotel.ui.screens.rooming.HoScreen
import com.kirumbastacy.panoramahotel.ui.screens.rooming.PresidentialSuiteScreen
import com.kirumbastacy.panoramahotel.ui.screens.rooming.StandardRoomScreen
import com.kirumbastacy.panoramahotel.ui.screens.rooming.SuiteRoomScreen
import com.kirumbastacy.panoramahotel.ui.screens.rooms.RoomScreen
import com.kirumbastacy.panoramahotel.ui.screens.splash.SplashScreen
import com.kirumbastacy.panoramahotel.viewmodel.AuthViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_PAYMENT
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,

        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_CONTACT) {
            ContactScreen(navController)
        }
        composable(ROUT_ROOMS) {
            RoomScreen(navController)
        }
        composable(ROUT_STANDARD) {
            StandardRoomScreen(navController)
        }
        composable(ROUT_DELUXE) {
            DeluxeRoomScreen(navController)
        }
        composable(ROUT_SUITE) {
            SuiteRoomScreen(navController)
        }
        composable(ROUT_PRESIDENTIAL) {
            PresidentialSuiteScreen(navController)
        }
        composable(ROUT_HO) {
            HoScreen(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_PAYMENT) {
            PaymentScreen(navController)
        }












        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }




    }
}