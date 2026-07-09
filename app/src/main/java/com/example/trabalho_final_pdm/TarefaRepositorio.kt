package com.example.trabalho_final_pdm

import androidx.lifecycle.LiveData

class tarefarepositorio(private val dao: tarefadao) {

    val todas: LiveData<List<tarefa>> = dao.buscar_todas()

    suspend fun inserir(t: tarefa) = dao.inserir(t)

    suspend fun atualizar(t: tarefa) = dao.atualizar(t)

    suspend fun deletar(t: tarefa) = dao.deletar(t)

    suspend fun buscar(id: Long) = dao.buscar_por_id(id)
}
