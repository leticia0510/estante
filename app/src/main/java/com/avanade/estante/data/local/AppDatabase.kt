package com.avanade.estante.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avanade.estante.data.local.converter.LeituraStatusConverter
import com.avanade.estante.data.local.dao.LivroDao
import com.avanade.estante.data.local.dao.ResenhaDao
import com.avanade.estante.data.local.dao.UsuarioDao
import com.avanade.estante.data.local.entity.LivroEntity
import com.avanade.estante.data.local.entity.ResenhaEntity
import com.avanade.estante.data.local.entity.UsuarioEntity

@Database(
    entities = [
        UsuarioEntity::class,
        LivroEntity::class,
        ResenhaEntity::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(LeituraStatusConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    abstract fun livroDao(): LivroDao

    abstract fun resenhaDao(): ResenhaDao

    companion object {
        const val DATABASE_NAME = "estante.db"
    }
}
