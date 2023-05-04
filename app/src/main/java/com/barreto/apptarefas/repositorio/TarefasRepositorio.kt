package com.barreto.apptarefas.repositorio

import com.barreto.apptarefas.datasource.DataSource
import com.barreto.apptarefas.model.Tarefa
import kotlinx.coroutines.flow.Flow

class TarefasRepositorio() {
    private val dataSource= DataSource()
    fun salvarTarefa(tarefa: String, descricao: String, prioridade: Int){
        dataSource.salvarTarefa(tarefa, descricao, prioridade)
    }

    fun recuperaTarefas():Flow<MutableList<Tarefa>>{
        return dataSource.recuperarTarefas()
    }
}