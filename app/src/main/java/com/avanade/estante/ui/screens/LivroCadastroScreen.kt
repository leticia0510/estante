package com.avanade.estante.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.avanade.estante.ui.intent.LivroCadastroIntent
import com.avanade.estante.ui.theme.DarkBlue
import com.avanade.estante.ui.theme.LightBlue
import com.avanade.estante.ui.theme.LightPink
import com.avanade.estante.ui.theme.Pink
import com.avanade.estante.ui.theme.Purple
import com.avanade.estante.ui.theme.White
import com.avanade.estante.ui.viewmodel.LivroCadastroViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LivroCadastroScreen(
    navController: NavController
) {

    val viewModel: LivroCadastroViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPink)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Cadastrar Livro",
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            color = Purple
        )

        Text(
            text = "Adicione um novo livro à sua estante",
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            color = DarkBlue,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

        LivroInput(
            nomeInput = "Título",
            texto = uiState.titulo,
            onInputChange = {
                viewModel.onIntent(
                    LivroCadastroIntent.TituloChanged(it)
                )
            }
        )

        LivroInput(
            nomeInput = "Autor",
            texto = uiState.autor,
            onInputChange = {
                viewModel.onIntent(
                    LivroCadastroIntent.AutorChanged(it)
                )
            }
        )

        LivroInput(
            nomeInput = "Imagem (URL)",
            texto = uiState.urlImagem,
            onInputChange = {
                viewModel.onIntent(
                    LivroCadastroIntent.ImagemChanged(it)
                )
            }
        )

        LivroInput(
            nomeInput = "Ano de publicação",
            texto = uiState.anoPublicacao,
            onInputChange = {
                viewModel.onIntent(
                    LivroCadastroIntent.AnoPublicacaoChanged(it)
                )
            },
            keyboardType = KeyboardType.Number
        )

        LivroInput(
            nomeInput = "Gênero",
            texto = uiState.genero,
            onInputChange = {
                viewModel.onIntent(
                    LivroCadastroIntent.GeneroChanged(it)
                )
            }
        )

        Button(
            onClick = {
                viewModel.onIntent(
                    LivroCadastroIntent.CadastrarClicked
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Pink,
                contentColor = White
            )
        ) {

            Text(
                text = if (uiState.isLoading) {
                    "Cadastrando..."
                } else {
                    "Cadastrar Livro"
                },
                style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun LivroInput(
    nomeInput: String,
    texto: String,
    onInputChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    OutlinedTextField(
        value = texto,
        onValueChange = onInputChange,
        label = {
            Text(
                text = nomeInput
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
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
