package com.avanade.estante.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.avanade.estante.domain.model.LeituraStatus
import com.avanade.estante.domain.model.Livro
import com.avanade.estante.domain.model.Resenha
import com.avanade.estante.ui.theme.DarkBlue
import com.avanade.estante.ui.theme.LightPink
import com.avanade.estante.ui.theme.Pink
import com.avanade.estante.ui.theme.Purple

@Composable
fun LivroDetalheContent(
    livro: Livro,
    resenha: Resenha?,
    onBack: () -> Unit,
    onSalvarResenha: (
        String,
        Int,
        LeituraStatus
    ) -> Unit,
    onDeletarResenha: () -> Unit
) {

    var texto by remember(
        resenha?.id
    ) {
        mutableStateOf(
            resenha?.texto ?: ""
        )
    }

    var avaliacao by remember(
        resenha?.id
    ) {
        mutableIntStateOf(
            resenha?.avaliacao ?: 0
        )
    }

    var status by remember(
        resenha?.id
    ) {
        mutableStateOf(
            resenha?.status ?: LeituraStatus.QUERO_LER
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPink),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 20.dp
        )
    ) {

        item {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = onBack
                ) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Purple
                    )
                }

                Text(
                    text = "Detalhes do livro",
                    style = MaterialTheme.typography.titleLarge,
                    color = Purple
                )
            }
        }

        item {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 20.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = livro.urlImagem,
                    contentDescription = livro.titulo,
                    modifier = Modifier
                        .width(200.dp)
                        .aspectRatio(2f / 3f)
                        .clip(
                            RoundedCornerShape(16.dp)
                        )
                )

                Text(
                    text = livro.titulo,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Purple,
                    modifier = Modifier.padding(top = 20.dp)
                )

                Text(
                    text = livro.autor,
                    style = MaterialTheme.typography.titleMedium,
                    color = DarkBlue,
                    modifier = Modifier.padding(top = 6.dp)
                )

                Text(
                    text = "${livro.genero} • ${livro.anoPublicacao}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = DarkBlue,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }

        item {

            HorizontalDivider(
                modifier = Modifier.padding(
                    vertical = 24.dp
                )
            )
        }

        item {

            Text(
                text = "Minha resenha",
                style = MaterialTheme.typography.headlineSmall,
                color = Purple
            )
        }

        item {

            Text(
                text = "Avaliação",
                style = MaterialTheme.typography.titleMedium,
                color = DarkBlue,
                modifier = Modifier.padding(
                    top = 20.dp,
                    bottom = 8.dp
                )
            )

            Row {

                repeat(5) { index ->

                    IconButton(
                        onClick = {
                            avaliacao = index + 1
                        }
                    ) {

                        Text(
                            text = if (index < avaliacao) "★" else "☆",
                            fontSize = 32.sp,
                            color = Pink
                        )
                    }
                }
            }
        }

        item {

            Text(
                text = "Status da leitura",
                style = MaterialTheme.typography.titleMedium,
                color = DarkBlue,
                modifier = Modifier.padding(
                    top = 12.dp,
                    bottom = 8.dp
                )
            )

            LeituraStatusSelector(
                status = status,
                onStatusChanged = {
                    status = it
                }
            )
        }

        item {

            OutlinedTextField(
                value = texto,
                onValueChange = {
                    texto = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                label = {
                    Text("Sua resenha")
                },
                placeholder = {
                    Text(
                        "Conte o que você achou do livro..."
                    )
                },
                minLines = 6,
                maxLines = 10
            )
        }

        item {

            Button(
                onClick = {
                    onSalvarResenha(
                        texto,
                        avaliacao,
                        status
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple
                )
            ) {

                Text(
                    text = if (resenha == null)
                        "Salvar resenha"
                    else
                        "Atualizar resenha"
                )
            }
        }

        if (resenha != null) {

            item {

                TextButton(
                    onClick = onDeletarResenha,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Excluir resenha",
                        color = Pink
                    )
                }
            }
        }
    }
}
