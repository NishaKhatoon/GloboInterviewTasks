package com.coder.globocominterviewapplication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coder.globocominterviewapplication.apiCall.domain.PostViewModel
import com.coder.globocominterviewapplication.apiCall.presentation.GETData
import com.coder.globocominterviewapplication.googleSignIn.presentation.GoogleSignInScreen
import com.coder.globocominterviewapplication.razorPay.RazorPaymentScreen

@Composable
fun Navigation(mPostViewModel: PostViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Routes.GoogleSignInScreen.route) {
            GoogleSignInScreen()
        }
        composable(Routes.RazorPaymentScreen.route) {
            RazorPaymentScreen()
        }
        composable(Routes.APICallMVVM.route) {
            GETData(mPostViewModel)
        }

    }
}