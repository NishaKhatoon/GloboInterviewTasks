

package com.coder.globocominterviewapplication.googleSignIn.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Login
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job


@Composable
fun LoginScreen(onSignInClick: () -> Job) {
  Surface(modifier=Modifier.fillMaxSize()){
      Column(modifier=Modifier
          .fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
      ){
          Icon(
              modifier = Modifier
                  .size(80.dp),
              imageVector = Icons.AutoMirrored.Rounded.Login,
              contentDescription = null
          )

          Spacer(modifier = Modifier.size(32.dp))

          Button(
              onClick = { onSignInClick() },
               colors = ButtonDefaults.buttonColors(Color.Red)
          ) {
              Text(
                    text = "Continue with Google",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                  )
          }

      }
  }

}
