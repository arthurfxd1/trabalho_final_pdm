package com.example.trabalho_final_pdm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tarefas")
data class tarefa(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val titulo: String,
    val descricao: String,
    val latitude: Double,
    val longitude: Double,
    val raio: Float = 100f,
    val concluida: Boolean = false
)
