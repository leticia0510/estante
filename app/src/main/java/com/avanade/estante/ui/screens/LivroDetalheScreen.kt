package com.avanade.estante.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import com.avanade.estante.ui.intent.LivroDetalheIntent
import com.avanade.estante.ui.theme.Pink
import com.avanade.estante.ui.theme.Purple
import com.avanade.estante.ui.viewmodel.LivroDetalheViewModel
import org.koin.androidx.compose.koinViewModel
import com.avanade.estante.ui.components.LivroDetalheContent


@Composable
fun LivroDetalheScreen(
    livroId: Int,
    navController: NavController
) {

    val viewModel: LivroDetalheViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(livroId) {
        viewModel.onIntent(
            LivroDetalheIntent.CarregarLivro(livroId)
        )
    }

    when {

        uiState.isLoading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(
                    color = Pink
                )
            }
        }

        uiState.erro != null && uiState.livro == null -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = uiState.erro ?: "",
                    color = Purple
                )
            }
        }

        uiState.livro != null -> {

            LivroDetalheContent(
                livro = uiState.livro!!,
                resenha = uiState.resenha,
                onBack = {
                    navController.popBackStack()
                },
                onSalvarResenha = { texto, avaliacao, status ->

                    viewModel.onIntent(
                        LivroDetalheIntent.SalvarResenha(
                            texto = texto,
                            avaliacao = avaliacao,
                            status = status
                        )
                    )
                },
                onDeletarResenha = {

                    viewModel.onIntent(
                        LivroDetalheIntent.DeletarResenha
                    )
                }
            )
        }
    }
}


