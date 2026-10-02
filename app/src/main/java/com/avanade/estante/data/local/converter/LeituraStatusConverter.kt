package com.avanade.estante.data.local.converter

import androidx.room.TypeConverter
import com.avanade.estante.data.local.entity.LeituraStatusEntity

class LeituraStatusConverter {

    @TypeConverter
    fun fromLeituraStatus(status: LeituraStatusEntity): String {
        return status.name
    }

    @TypeConverter
    fun toLeituraStatus(status: String): LeituraStatusEntity {
        return LeituraStatusEntity.valueOf(status)
    }
}
