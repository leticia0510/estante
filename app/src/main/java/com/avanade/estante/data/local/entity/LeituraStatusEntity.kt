package com.avanade.estante.data.local.entity

import com.avanade.estante.domain.model.LeituraStatus

enum class LeituraStatusEntity {
    QUERO_LER,
    LENDO,
    PAREI_DE_LER,
    CONCLUIDO;

    fun toDomainModel(): LeituraStatus {
        return when (this) {
            LeituraStatusEntity.QUERO_LER -> LeituraStatus.QUERO_LER
            LeituraStatusEntity.LENDO -> LeituraStatus.LENDO
            LeituraStatusEntity.PAREI_DE_LER -> LeituraStatus.PAREI_DE_LER
            LeituraStatusEntity.CONCLUIDO -> LeituraStatus.CONCLUIDO
        }
    }
}