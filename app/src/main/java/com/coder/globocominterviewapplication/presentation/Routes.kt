package com.coder.globocominterviewapplication.presentation

sealed class Routes(val route: String) {

    data object HomeScreen : Routes("home_screen")
    data object GoogleSignInScreen : Routes("GoogleSignInScreen")
    data object RazorPaymentScreen : Routes("RazorPaymentScreen")
    data object APICallMVVM : Routes("APICallMVVM")
}