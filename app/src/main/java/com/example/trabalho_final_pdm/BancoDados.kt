package com.example.trabalho_final_pdm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [tarefa::class], version = 1, exportSchema = false)
abstract class bancodados : RoomDatabase() {

    abstract fun tdao(): tarefadao

    companion object {
        @Volatile
        private var instancia: bancodados? = null

        fun obter_instancia(contexto: Context): bancodados {
            return instancia ?: synchronized(this) {
                val nova = Room.databaseBuilder(
                    contexto.applicationContext,
                    bancodados::class.java,
                    "banco"
                ).build()
                instancia = nova
                nova
            }
        }
    }
}
