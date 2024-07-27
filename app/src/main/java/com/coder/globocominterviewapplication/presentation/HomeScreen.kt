package com.coder.globocominterviewapplication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.coder.globocominterviewapplication.ui.theme.GlobocomInterviewApplicationTheme


@Composable
fun HomeScreen(
    navController: NavController
){
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier
        .fillMaxSize(),
         color = MaterialTheme.colorScheme.background){

        Column(modifier=Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            /*Google Sign IN*/
            Button(
                onClick = { navController.navigate(Routes.GoogleSignInScreen.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text(
                    text = "1. Google Sign In",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            /*Razor Pay SDK*/
            Button(
                onClick = { navController.navigate(Routes.RazorPaymentScreen.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(Color.Cyan)
            ) {
                Text(
                    text = "2. Razor Pay UPI",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            /*Call API with mvvm and retrofit*/

            Button(
                onClick = { navController.navigate(Routes.APICallMVVM.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(Color.Magenta)
            ) {
                Text(
                    text = "3. Call API",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GlobocomInterviewApplicationTheme {
        //HomeScreen()
    }
}