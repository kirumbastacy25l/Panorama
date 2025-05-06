package com.kirumbastacy.panoramahotel.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.kirumbastacy.panoramahotel.navigation.ROUT_HOME
import com.kirumbastacy.panoramahotel.ui.theme.GoldenColor
import com.kirumbastacy.panoramahotel.ui.theme.VeryWhite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController){
    //Navigation
    val coroutine = rememberCoroutineScope()
    coroutine.launch {

        delay(2000)
        navController.navigate(ROUT_HOME)

    }

    //End of navigation




    Box(
        modifier = Modifier.fillMaxSize()


    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()){



            
        }

        Image(
            painter = painterResource(id = R.drawable.lounge),
            contentDescription = "Room Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()

        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 460.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(id = R.drawable.log),
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(70.dp)
            )

            Text(
                text = "PANORAMA \nHOTEL",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = VeryWhite,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Luxury & Comfort at\nyour fingertips",
                fontSize = 30.sp,

                color = VeryWhite,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            CircularProgressIndicator(color = GoldenColor,
                    modifier = Modifier.size(40.dp),
                strokeWidth = 4.dp)

        }
    }




    }









@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(navController = rememberNavController())
}