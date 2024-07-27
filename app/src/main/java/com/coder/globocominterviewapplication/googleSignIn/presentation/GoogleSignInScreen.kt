package com.coder.globocominterviewapplication.googleSignIn.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coder.globocominterviewapplication.BuildConfig
import com.coder.globocominterviewapplication.googleSignIn.utils.Screen
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GoogleSignInScreen(){

    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background) {
         val auth : FirebaseAuth by lazy {
            Firebase.auth
        }

        val webClientId by lazy {
            BuildConfig.WEB_CLIENT_ID
        }
        val navController = rememberNavController()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val credentialManager = CredentialManager.create(context)

        val startDestination = if(auth.currentUser==null) Screen.Login.name else Screen.Profile.name

        NavHost(
            navController = navController,
            startDestination = startDestination
        ){
            composable(Screen.Login.name){
                LoginScreen(onSignInClick = {
                    val googleOption = GetGoogleIdOption.Builder()
                        .setFilterByAuthorizedAccounts(false)
                        .setServerClientId(webClientId)
                        .build()

                    val request = GetCredentialRequest.Builder()
                        .addCredentialOption(googleOption)
                        .build()

                    scope.launch {
                        try {
                            val result = credentialManager.getCredential(
                                context = context,
                                request = request)
                            val credential = result.credential
                            val googleIdTokenCredential = GoogleIdTokenCredential
                                .createFrom(credential.data)
                            val googleIdToken =googleIdTokenCredential.idToken
                            val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken,null)

                            auth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener{task->
                                    if(task.isSuccessful){
                                        navController.popBackStack()
                                        navController.navigate(Screen.Profile.name)
                                    }
                                }

                        }catch(e:Exception){

                            Toast.makeText(
                                context,
                                "Error : ${e.message}",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                })
            }
            composable(Screen.Profile.name){
                ProfileScreen(
                    currentUser = auth.currentUser,
                    onSignOutClick = {
                        auth.signOut()
                        scope.launch {
                            credentialManager.clearCredentialState(
                                ClearCredentialStateRequest()
                            )
                        }
                        navController.popBackStack()
                        navController.navigate(Screen.Login.name)

                    }
                )
            }
        }
    }
}
