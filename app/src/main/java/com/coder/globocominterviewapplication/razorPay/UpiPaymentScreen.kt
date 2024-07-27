package com.coder.globocominterviewapplication.razorPay

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.coder.globocominterviewapplication.BuildConfig
import com.razorpay.Checkout
import org.json.JSONObject

@Composable
fun RazorPaymentScreen() {
    val applicationContext = LocalContext.current
    val keyId by lazy {
        BuildConfig.KEY_ID
    }
    Checkout.preload(applicationContext)
    val co = Checkout()
    co.setKeyID(keyId)

    var upiId by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var transactionNote by remember { mutableStateOf("This is for demo purpose") }

    var upiIdError by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var amountError by remember { mutableStateOf<String?>(null) }

    fun validate(): Boolean {
        upiIdError = if (upiId.isBlank()) {
            "UPI ID is required"
        } else if (!upiId.matches(Regex("^[\\w.-]+@[\\w.-]+$"))) {
            "Invalid UPI ID format"
        } else {
            null
        }

        nameError = if (name.isBlank()) "Name is required" else null

        amountError = when {
            amount.isBlank() -> "Amount is required"
            amount.toDoubleOrNull() == null -> "Amount must be a valid number"
            amount.toDouble() <= 0 -> "Amount must be greater than 0"
            else -> null
        }

        return upiIdError == null && nameError == null && amountError == null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = upiId,
            onValueChange = { upiId = it },
            label = { Text("UPI ID") },
            isError = upiIdError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (upiIdError != null) {
            Text(
                text = upiIdError ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            isError = nameError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (nameError != null) {
            Text(
                text = nameError ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = amountError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (amountError != null) {
            Text(
                text = amountError ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = transactionNote,
            onValueChange = { transactionNote = it },
            label = { Text("Transaction Note") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (validate()) {
                startPayment(applicationContext, upiId, name, amount, transactionNote)
            }
        }) {
            Text("Pay Now")
        }
    }
}

fun startPayment(context: Context, upiId: String, name: String, amount: String, transactionNote: String) {
    val activity = context as Activity
    val checkout = Checkout()
    try {
        val options = JSONObject()
        options.put("name", name)
        options.put("description", transactionNote)
        options.put("currency", "INR")
        options.put("amount", (amount.toDouble() * 100).toInt().toString())
        options.put("method", "upi")

        val prefill = JSONObject()
        prefill.put("contact", "9876563459")
        prefill.put("email", "nisha123@gmail.com")
        prefill.put("vpa", upiId)

        options.put("prefill", prefill)

        checkout.open(activity, options)
    }catch (e: Exception){
        Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
        e.printStackTrace()
    }



}
