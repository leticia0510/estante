package com.avanade.estante.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.avanade.estante.ui.Screen
import com.avanade.estante.ui.intent.LoginIntent
import com.avanade.estante.ui.theme.DarkBlue
import com.avanade.estante.ui.theme.LightBlue
import com.avanade.estante.ui.theme.LightPink
import com.avanade.estante.ui.theme.Pink
import com.avanade.estante.ui.theme.Purple
import com.avanade.estante.ui.theme.White
import com.avanade.estante.ui.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController
) {

    val loginViewModel: LoginViewModel = koinViewModel()

    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.loginSucesso) {

        if (uiState.loginSucesso) {

            navController.navigate(Screen.LivroCadastro) {

                popUpTo(Screen.Login) {
                    inclusive = true
                }

                launchSingleTop = true
            }

            loginViewModel.onIntent(
                LoginIntent.LoginSuccessHandled
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPink),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Seja Bem-Vindo",
                style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
                color = Purple
            )

            Text(
                text = "Entre na sua conta",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = DarkBlue
            )

            LoginInput(
                nomeInput = "Email",
                texto = uiState.email,
                onInputChange = {
                    loginViewModel.onIntent(
                        LoginIntent.EmailChanged(it)
                    )
                },
                tipo = VisualTransformation.None
            )

            LoginInput(
                nomeInput = "Senha",
                texto = uiState.senha,
                onInputChange = {
                    loginViewModel.onIntent(
                        LoginIntent.SenhaChanged(it)
                    )
                },
                tipo = PasswordVisualTransformation()
            )

            ButtonLogin(
                onClick = {
                    loginViewModel.onIntent(
                        LoginIntent.LoginClicked
                    )
                },
                title = if (uiState.isLoading) {
                    "Entrando..."
                } else {
                    "Entrar"
                }
            )
        }
    }
}


@Composable
private fun LoginInput(
    nomeInput: String,
    texto: String,
    onInputChange: (String) -> Unit,
    tipo: VisualTransformation
) {

    OutlinedTextField(
        value = texto,
        onValueChange = onInputChange,
        label = {
            Text(
                text = nomeInput
            )
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = tipo,

        colors = OutlinedTextFieldDefaults.colors(

            // Campo
            focusedContainerColor = LightBlue.copy(alpha = 0.35f),
            unfocusedContainerColor = LightBlue.copy(alpha = 0.20f),

            // Borda
            focusedBorderColor = Pink,
            unfocusedBorderColor = DarkBlue.copy(alpha = 0.5f),

            // Texto
            focusedTextColor = Purple,
            unfocusedTextColor = Purple,

            // Label
            focusedLabelColor = Pink,
            unfocusedLabelColor = DarkBlue,

            // Cursor
            cursorColor = Pink
        )
    )
}

@Composable
private fun ButtonLogin(
    onClick: () -> Unit,
    title: String
) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Pink,
            contentColor = White
        )
    ) {

        Text(
            text = title,
            style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
            fontSize = 16.sp
        )
    }
}