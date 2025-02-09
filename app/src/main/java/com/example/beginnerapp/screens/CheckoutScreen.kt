package com.example.beginnerapp.screens

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.beginnerapp.network.Constants
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    onBackClick:()->Unit,
    onCheckoutComplete:()->Unit,
) {
    var paymentIntentClientSecret by remember { mutableStateOf<String?>(null) }

    var error by remember { mutableStateOf<String?>(null) }

    val activity= LocalContext.current as Activity
    val context= LocalContext.current

    val paymentSheet = rememberPaymentSheet { paymentResult ->
        when (paymentResult) {
            is PaymentSheetResult.Completed -> showToast("Payment complete!", activity, context)
            is PaymentSheetResult.Canceled -> showToast("Payment canceled!", activity, context)
            is PaymentSheetResult.Failed -> {
                error = paymentResult.error.localizedMessage ?: paymentResult.error.message
            }
        }
    }

    error?.let { errorMessage ->
        ErrorAlert(
            errorMessage = errorMessage,
            onDismiss = {
                error = null
            }
        )
    }

    LaunchedEffect(Unit) {
        fetchPaymentIntent().onSuccess { clientSecret ->
            paymentIntentClientSecret = clientSecret
        }.onFailure { paymentIntentError ->
            error = paymentIntentError.localizedMessage ?: paymentIntentError.message
        }
    }

    Scaffold(
        topBar={
            TopAppBar(
                title = {Text("Checkout")},
                navigationIcon = {
                    IconButton( onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack,"Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
        ) {
        PayButton(
            enabled = paymentIntentClientSecret != null,
            onClick = {
                paymentIntentClientSecret?.let {
                    onPayClicked(
                        paymentSheet = paymentSheet,
                        paymentIntentClientSecret = it,
                    )
                }
            }

        )
        }
    }
}

@Composable
private fun PayButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        onClick = onClick
    ) {
        Text("Pay now")
    }
}

@Composable
private fun ErrorAlert(
    errorMessage: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = "Error occurred during checkout")
        },
        text = {
            Text(text = errorMessage)
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onDismiss) {
                Text(text = "Ok")
            }
        }
    )
}

private suspend fun fetchPaymentIntent(): Result<String> = suspendCoroutine { continuation ->
    val url = "${Constants.BASE_URL}api/create-payment-intent"

    val shoppingCartContent = """
            {
                "items": [
                    {"id":"xl-tshirt",
                    "amount":"2000"}
                ]
            }
        """

    val mediaType = "application/json; charset=utf-8".toMediaType()

    val body = shoppingCartContent.toRequestBody(mediaType)
    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()

    OkHttpClient()
        .newCall(request)
        .enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                continuation.resume(Result.failure(e))
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    continuation.resume(Result.failure(Exception(response.message)))
                } else {
                    val clientSecret = extractClientSecretFromResponse(response)

                    clientSecret?.let { secret ->
                        continuation.resume(Result.success(secret))
                    } ?: run {
                        val error = Exception("Could not find payment intent client secret in response!")

                        continuation.resume(Result.failure(error))
                    }
                }
            }
        })
}

private fun extractClientSecretFromResponse(response: Response): String? {
    return try {
        val responseData = response.body?.string()
        val responseJson = responseData?.let { JSONObject(it) } ?: JSONObject()

        responseJson.getString("clientSecret")
    } catch (exception: JSONException) {
        null
    }
}

private fun showToast(message: String, activity:Activity, context: Context) {
    activity.runOnUiThread {
        Toast.makeText(context,  message, Toast.LENGTH_LONG).show()
    }
}

private fun onPayClicked(
    paymentSheet: PaymentSheet,
    paymentIntentClientSecret: String,
) {
    val configuration = PaymentSheet.Configuration.Builder(merchantDisplayName = "Example, Inc.")
        .build()

    // Present Payment Sheet
    paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret, configuration)
}