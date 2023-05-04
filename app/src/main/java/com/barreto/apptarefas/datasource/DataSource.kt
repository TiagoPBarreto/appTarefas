package com.barreto.apptarefas.datasource

import com.barreto.apptarefas.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {
    private val db = FirebaseFirestore.getInstance()
    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private val todasTarefas:StateFlow<MutableList<Tarefa>> = _todasTarefas
    fun salvarTarefa( tarefa: String, descricao: String, prioridade: Int ){
        val tarefaMap = hashMapOf(
            "tarefa" to tarefa,
            "descricao" to descricao,
            "prioridade" to prioridade
        )
        db.collection("tarefas").document(tarefa).set(tarefaMap).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }
    fun recuperarTarefas():Flow<MutableList<Tarefa>>{

        val listaTarefa: MutableList<Tarefa> = mutableListOf()
        db.collection("tarefas").get().addOnCompleteListener { querySnapshot ->
            if (querySnapshot.isSuccessful){
                for (documento in querySnapshot.result){
                    val tarefa = documento.toObject(Tarefa::class.java)
                    listaTarefa.add(tarefa)
                    _todasTarefas.value = listaTarefa
                }
            }
        }
        return todasTarefas
    }
}