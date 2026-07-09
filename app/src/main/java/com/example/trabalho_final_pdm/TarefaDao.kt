package com.example.trabalho_final_pdm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface tarefadao {

    @Query("SELECT * FROM tarefas ORDER BY id DESC")
    fun buscar_todas(): LiveData<List<tarefa>>

    @Query("SELECT * FROM tarefas")
    suspend fun buscar_todas_lista(): List<tarefa>

    @Query("SELECT * FROM tarefas WHERE id = :id")
    suspend fun buscar_por_id(id: Long): tarefa?

    @Insert
    suspend fun inserir(t: tarefa): Long

    @Update
    suspend fun atualizar(t: tarefa)

    @Delete
    suspend fun deletar(t: tarefa)

    @Query("DELETE FROM tarefas")
    suspend fun limpar_tudo()
}
