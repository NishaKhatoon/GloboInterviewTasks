package com.coder.globocominterviewapplication.googleSignIn.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.coder.globocominterviewapplication.presentation.Routes
import com.coder.globocominterviewapplication.ui.theme.profileTypography
import com.google.firebase.auth.FirebaseUser


@Composable
fun ProfileScreen(
    currentUser:  FirebaseUser?,
    onSignOutClick: () -> Unit
){

    Surface(modifier=Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            //Checking current user is null or not
           currentUser?.let { user->

               user.photoUrl?.let {
                   AsyncImage(
                       modifier = Modifier
                           .size(140.dp)
                           .clip(RoundedCornerShape(4.dp)),
                       model = ImageRequest.Builder(LocalContext.current)
                           .data(it)
                           .crossfade(true)
                           .build(),
                       contentDescription = "Profile Picture",
                       contentScale = ContentScale.Crop
                   )
                   Spacer(modifier = Modifier.size(16.dp))
               }

               user.displayName?.let { name->
                   Text(
                       text = name,
                       style = profileTypography,
                       maxLines = 1,
                       overflow = TextOverflow.Ellipsis
                   )
                   Spacer(modifier = Modifier.size(16.dp))
               }

               user.email?.let { mailId->
                   Text(
                       text = "Email Id $mailId",
                       style = profileTypography,
                       maxLines = 1,
                       overflow = TextOverflow.Ellipsis
                   )
                   Spacer(modifier = Modifier.size(16.dp))
               }

               Text(
                   text = "UID:  ${user.uid}",
                   style = profileTypography,
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
               )

               Spacer(modifier = Modifier.size(16.dp))

               Button(onClick = { onSignOutClick()}) {
                   Text(
                       text = "Sign Out",
                       style = profileTypography.copy(
                           fontWeight = FontWeight.SemiBold
                       ),
                       maxLines = 1,
                       overflow = TextOverflow.Ellipsis
                   )
               }
           }
        }
    }
}