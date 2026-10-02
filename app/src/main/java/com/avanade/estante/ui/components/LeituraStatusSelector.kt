package com.avanade.estante.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import com.avanade.estante.domain.model.LeituraStatus

private fun statusLabel(
    status: LeituraStatus
): String {

    return when (status) {

        LeituraStatus.QUERO_LER ->
            "Quero ler"

        LeituraStatus.LENDO ->
            "Lendo"

        LeituraStatus.PAREI_DE_LER ->
            "Parei de ler"

        LeituraStatus.CONCLUIDO ->
            "Concluído"
    }
}


@Composable
fun LeituraStatusSelector(
    status: LeituraStatus,
    onStatusChanged: (LeituraStatus) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box {

        OutlinedButton(
            onClick = {
                expanded = true
            }
        ) {

            Text(
                text = statusLabel(status)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            LeituraStatus.entries.forEach { item ->

                DropdownMenuItem(
                    text = {
                        Text(
                            text = statusLabel(item)
                        )
                    },
                    onClick = {

                        onStatusChanged(item)

                        expanded = false
                    }
                )
            }
        }
    }
}
