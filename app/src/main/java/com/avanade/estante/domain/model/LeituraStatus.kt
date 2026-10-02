package com.avanade.estante.domain.model

import com.avanade.estante.data.local.entity.LeituraStatusEntity

enum class LeituraStatus {
    QUERO_LER,
    LENDO,
    PAREI_DE_LER,
    CONCLUIDO;

    fun toEntity(): LeituraStatusEntity {
        return when (this) {
            QUERO_LER -> LeituraStatusEntity.QUERO_LER
            LENDO -> LeituraStatusEntity.LENDO
            PAREI_DE_LER -> LeituraStatusEntity.PAREI_DE_LER
            CONCLUIDO -> LeituraStatusEntity.CONCLUIDO
        }
    }
}