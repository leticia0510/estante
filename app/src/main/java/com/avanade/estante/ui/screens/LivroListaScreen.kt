package com.avanade.estante.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.avanade.estante.domain.model.Livro
import com.avanade.estante.ui.intent.LivroListaIntent
import com.avanade.estante.ui.theme.DarkBlue
import com.avanade.estante.ui.theme.LightBlue
import com.avanade.estante.ui.theme.LightPink
import com.avanade.estante.ui.theme.Pink
import com.avanade.estante.ui.theme.Purple
import com.avanade.estante.ui.theme.White
import com.avanade.estante.ui.viewmodel.LivroListaViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LivroListaScreen(
    navController: NavController
) {

    val viewModel: LivroListaViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(
            LivroListaIntent.CarregarLivros
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPink)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 24.dp
                )
        ) {

            androidx.compose.foundation.layout.Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = "Minha Estante",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Purple
                    )

                    Text(
                        text = "${uiState.livros.size} livros cadastrados",
                        style = MaterialTheme.typography.bodyLarge,
                        color = DarkBlue,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                IconButton(
                    onClick = {
                        viewModel.onIntent(
                            LivroListaIntent.RecarregarLivros
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Atualizar livros",
                        tint = Pink
                    )
                }
            }
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

            uiState.erro != null -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = uiState.erro ?: "",
                        color = Purple,
                        fontSize = 16.sp
                    )
                }
            }

            uiState.livros.isEmpty() -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = Purple
                        )

                        Text(
                            text = "Nenhum livro cadastrado",
                            color = Purple,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 12.dp)
                        )

                        Text(
                            text = "Cadastre um livro para começar sua estante.",
                            color = DarkBlue,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            else -> {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = 24.dp,
                        vertical = 8.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        items = uiState.livros,
                        key = { livro -> livro.id }
                    ) { livro ->

                        LivroCard(
                            livro = livro,
                            onClick = {
                                viewModel.onIntent(
                                    LivroListaIntent.LivroClicked(
                                        livro.id
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LivroCard(
    livro: Livro,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        AsyncImage(
            model = livro.urlImagem,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .background(LightBlue)
                .padding(20.dp)
        )
        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = livro.titulo,
                style = MaterialTheme.typography.titleLarge,
                color = Purple
            )

            Text(
                text = livro.autor,
                style = MaterialTheme.typography.bodyLarge,
                color = DarkBlue,
                modifier = Modifier.padding(top = 6.dp)
            )

            Text(
                text = "Gênero: ${livro.genero}",
                style = MaterialTheme.typography.bodyMedium,
                color = DarkBlue,
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = "Ano de publicação: ${livro.anoPublicacao}",
                style = MaterialTheme.typography.bodyMedium,
                color = DarkBlue,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
