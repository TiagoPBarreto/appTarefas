package com.barreto.apptarefas.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barreto.apptarefas.components.Botao
import com.barreto.apptarefas.components.CaixadeTexto
import com.barreto.apptarefas.constants.Constants
import com.barreto.apptarefas.repositorio.TarefasRepositorio
import com.barreto.apptarefas.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SalvarTarefa(
    navController: NavController
){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tarefasRepositorio = TarefasRepositorio()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Purple700,
                title = {
                    Text(
                        text = "Salvar Tarefa",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white
                    )
                }
            )
        }
    ) {
        var tituloTarefa by remember {
            mutableStateOf("")
        }
        var descricaoTarefa by remember {
            mutableStateOf("")
        }
        var semPrioridadeTarefa by remember {
            mutableStateOf(false)
        }
        var prioridadeBaixaTarefa by remember {
            mutableStateOf(false)
        }
        var prioridadeMediaTarefa by remember {
            mutableStateOf(false)
        }
        var prioridadeAltaTarefa by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            CaixadeTexto(
                value = tituloTarefa,
                onValueChange = {
                    tituloTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp, 10.dp, 0.dp),
                label = "Titulo Tarefa",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )
            CaixadeTexto(
                value = descricaoTarefa,
                onValueChange = {
                    descricaoTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(10.dp, 10.dp, 10.dp, 0.dp),
                label = "Descrição",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Nivel de Prioridade")
                RadioButton(
                    selected = prioridadeBaixaTarefa,
                    onClick = {
                        prioridadeBaixaTarefa = !prioridadeBaixaTarefa
                    },
                    colors = RadioButtonDefaults.colors (
                        unselectedColor = radio_button_green_disabled,
                        selectedColor = radio_button_green_selected
                    )
                )
                RadioButton(
                    selected = prioridadeMediaTarefa,
                    onClick = {
                        prioridadeMediaTarefa = !prioridadeMediaTarefa
                    },
                    colors = RadioButtonDefaults.colors (
                        unselectedColor = radio_button_yellow_disabled,
                        selectedColor = radio_button_yellow_selected
                    )
                )
                RadioButton(
                    selected = prioridadeAltaTarefa,
                    onClick = {
                        prioridadeAltaTarefa = !prioridadeAltaTarefa
                    },
                    colors = RadioButtonDefaults.colors (
                        unselectedColor = radio_button_red_disabled,
                        selectedColor = radio_button_red_selected
                    )
                )
            }

            Botao(
            onClick = {
                    var mensagem = true
                      scope.launch(Dispatchers.IO){
                            if (tituloTarefa.isEmpty()){
                                mensagem = false
                            }
                            else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeBaixaTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa,descricaoTarefa,Constants.PRIORIDADE_BAIXA)
                                mensagem = true
                            }
                            else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeMediaTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa,descricaoTarefa,Constants.PRIORIDADE_MEDIA)
                                mensagem = true
                            }
                            else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeAltaTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa,descricaoTarefa,Constants.PRIORIDADE_ALTA)
                                mensagem = true
                            }
                            else if(tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && semPrioridadeTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa,descricaoTarefa,Constants.SEM_PRIORIDADE)
                                mensagem = true
                            }
                            else if(tituloTarefa.isNotEmpty() && prioridadeBaixaTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa,descricaoTarefa,Constants.PRIORIDADE_BAIXA)
                                mensagem = true
                            }
                            else if(tituloTarefa.isNotEmpty() && prioridadeMediaTarefa){
                                tarefasRepositorio.salvarTarefa(tituloTarefa,descricaoTarefa,Constants.PRIORIDADE_MEDIA)
                                mensagem = true
                            }
                            else if(tituloTarefa.isNotEmpty() && prioridadeAltaTarefa) {
                                tarefasRepositorio.salvarTarefa(
                                    tituloTarefa,
                                    descricaoTarefa,
                                    Constants.PRIORIDADE_ALTA
                                )
                                mensagem = true
                            }
                          else{
                                tarefasRepositorio.salvarTarefa(tituloTarefa,descricaoTarefa,Constants.SEM_PRIORIDADE)
                                mensagem = true
                            }
                      }
                    scope.launch(Dispatchers.Main){
                        if (mensagem){
                            Toast.makeText(context, "Sucesso ao salvar tarefa",Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                        else{
                            Toast.makeText(context, "Titulo da teraf é obrigatorio",Toast.LENGTH_SHORT).show()
                        }
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(20.dp),
            texto = "Salvar"
            )
        }
    }
}