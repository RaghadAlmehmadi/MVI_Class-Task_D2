package com.daniyal.m2w3d2

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.daniyal.m2w3d2.ui.theme.whiteColor
import kotlinx.coroutines.flow.collect

@Composable
fun SignUpView(
    signUpViewModel: SignUpViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    CallBackSignUpViewAction(signUpViewModel)
    val viewState by signUpViewModel.viewStateFlow.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Screen Title
        Text(
            "Sign Up",
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
        )
        Spacer(Modifier.height(32.dp))

        //the name text field
        TextField(
            value = viewState.fullName.value,
            onValueChange = {
                signUpViewModel.handleIntent(
                    SignUpIntent.UpdateField(
                        signUpFieldsType = SignUpFieldsType.FULL_NAME,
                        value = it
                    )
                )
            },
            label = { Text("Enter Your Name:") },
            modifier = Modifier,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = viewState.fullName.isError
        )
        Spacer(Modifier.height(16.dp))

        //the email text field
        TextField(
            value = viewState.email.value,
            onValueChange = {
                signUpViewModel.handleIntent(
                    SignUpIntent.UpdateField(
                        signUpFieldsType = SignUpFieldsType.EMAIL,
                        value = it
                    )
                )
            },
            label = {
                Text("Enter Your Email:")
            },
            modifier = Modifier,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = viewState.email.isError
        )
        Spacer(Modifier.height(16.dp))

        //the password text field
        TextField(
            value = viewState.password.value,
            onValueChange = {
                signUpViewModel.handleIntent(
                    SignUpIntent.UpdateField(
                        signUpFieldsType = SignUpFieldsType.PASSWORD,
                        value = it
                    )
                )
            },
            label = { Text("Enter Your Password:") },
            modifier = Modifier,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = viewState.password.isError
        )
        Spacer(Modifier.height(16.dp))

        //the confirm password text field
        TextField(
            value = viewState.confirmPassword.value,
            onValueChange = {
                signUpViewModel.handleIntent(
                    SignUpIntent.UpdateField(
                        signUpFieldsType = SignUpFieldsType.CONFORM_PASSWORD,
                        value = it
                    )
                )
            },
            label = { Text("Confirm Your Password:") },
            modifier = Modifier,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = viewState.confirmPassword.isError
        )
        Spacer(Modifier.height(32.dp))

        val context = LocalContext.current
        //sign up button
        Button(
            onClick = {
                signUpViewModel.handleIntent(SignUpIntent.SignUpButtonClicked)
            },
        ) {
            Text("Sign Up")
        }
    }


    if (viewState.isLoading) {
        Loader()
    }
}

@Composable
private fun CallBackSignUpViewAction(signUpViewModel: SignUpViewModel) {
    val context = LocalContext.current
    LaunchedEffect(signUpViewModel) {
        signUpViewModel.viewActionFlow.collect { signUpViewAction ->
            when (signUpViewAction){
                is SignUpViewAction.ShowToast -> {
                    Toast.makeText(context, signUpViewAction.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

@Composable
fun Loader(onDismissRequest: () -> Unit = {}) {
    Dialog(
        onDismissRequest = onDismissRequest,
        content = {
            Row(
                modifier = Modifier
                    .background(color = whiteColor, shape = MaterialTheme.shapes.extraSmall)
                    .padding(20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        })
}