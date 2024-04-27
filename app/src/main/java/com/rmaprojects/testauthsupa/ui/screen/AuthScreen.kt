package com.rmaprojects.testauthsupa.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val authState = viewModel.authState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Sign Up Slur")
            Spacer(modifier = Modifier.height(32.dp))
            TextField(value = email, onValueChange = { email = it })
            Spacer(modifier = Modifier.height(24.dp))
            TextField(value = password, onValueChange = { password = it })
            Spacer(modifier = Modifier.height(26.dp))
            Button(
                onClick = { viewModel.performRegister(email, password) },
                enabled = !authState.value.isLoading()
            ) {
                Text(text = "Register")
            }
            authState.value.DisplayResult(
                onLoading = {
                    CircularProgressIndicator()
                },
                onSuccess = {

                },
                onError = { message ->
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    Text(text = message)
                },
                onIdle = {

                }
            )
        }

    }


}