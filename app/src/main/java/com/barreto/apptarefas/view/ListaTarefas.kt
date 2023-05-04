package com.barreto.apptarefas.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barreto.apptarefas.R
import com.barreto.apptarefas.itemLista.TarefaItem
import com.barreto.apptarefas.model.Tarefa
import com.barreto.apptarefas.repositorio.TarefasRepositorio
import com.barreto.apptarefas.ui.theme.Purple700
import com.barreto.apptarefas.ui.theme.black
import com.barreto.apptarefas.ui.theme.white
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListarTarefas(
    navController: NavController
){
    val tarefasRepositorio = TarefasRepositorio()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Purple700,
                title = {
                    Text(
                        text = "Lista de Tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white
                    )
                }
            )
        },
        backgroundColor = black,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                navController.navigate("salvarTarefas")
            },
                backgroundColor = Purple700
            ) {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "Icone de salvar tarefa"
                )
            }
        }
    ) {
       val listaTarefa = tarefasRepositorio.recuperaTarefas().collectAsState(mutableListOf()).value
        LazyColumn{
                    itemsIndexed(listaTarefa){position,_,->
                        TarefaItem(position = position, listaTarefas = listaTarefa)
                    }
        }
    }
}