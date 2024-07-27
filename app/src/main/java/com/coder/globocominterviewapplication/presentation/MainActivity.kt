package com.coder.globocominterviewapplication.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.coder.globocominterviewapplication.apiCall.domain.PostViewModel
import com.coder.globocominterviewapplication.ui.theme.GlobocomInterviewApplicationTheme
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() , PaymentResultWithDataListener {

    private val mPostViewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlobocomInterviewApplicationTheme {

                Navigation(mPostViewModel)
            }
        }


    }

    override fun onPaymentSuccess(razorpayPaymentID: String?, p1: PaymentData?) {
        // Handle payment success
        Toast.makeText(this, "Payment Successful: $razorpayPaymentID", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(code: Int, response: String?, p2: PaymentData?) {
        // Handle payment error
        Toast.makeText(this, "Payment Failed: $response", Toast.LENGTH_SHORT).show()
    }



}





