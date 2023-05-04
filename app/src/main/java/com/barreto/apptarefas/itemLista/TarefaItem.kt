package com.barreto.apptarefas.itemLista

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.barreto.apptarefas.R
import com.barreto.apptarefas.model.Tarefa
import com.barreto.apptarefas.ui.theme.*

@Composable
fun TarefaItem(
    position: Int,
    listaTarefas: MutableList<Tarefa>
){
    val tituloTarefa = listaTarefas[position].tarefa
    val descricaoTarefa = listaTarefas[position].descricao
    val prioridadeTarefa = listaTarefas[position].prioridade

    var nivelPrioridade: String = when(prioridadeTarefa){
        0 ->{
            "Sem Prioridade"
        }
        1 ->{
            "Prioridade Baixa"
        }
        2->{
            "Prioridade Media"
        }
        else ->{
            "Prioridade Alta"
        }
    }

    val color = when(prioridadeTarefa){
        0 ->{
            Color.Black
        }
        1 ->{
          radio_button_green_selected
        }
        2 ->{
            radio_button_yellow_selected
        }
        else ->{
            radio_button_red_selected
        }
    }

    Card(
        backgroundColor = white,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            val (txtTitulo, txtDescricao, cardPrioridade, txtPrioridade, btDeletar) = createRefs()
            Text(
                text = tituloTarefa.toString(),
                modifier = Modifier.constrainAs(txtTitulo){
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = descricaoTarefa.toString(),
                modifier = Modifier.constrainAs(txtDescricao){
                    top.linkTo(txtTitulo.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = nivelPrioridade,
                modifier = Modifier.constrainAs(txtPrioridade){
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )
            
            Card(
                backgroundColor = color,
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardPrioridade) {
                        top.linkTo(txtDescricao.bottom, margin = 10.dp)
                        start.linkTo(txtPrioridade.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                shape = ShapeCardPrioridade.large
            ) {

            }
            IconButton(
                onClick = {

                },
                modifier = Modifier.constrainAs(btDeletar){
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(cardPrioridade.end, margin = 30.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = null)
            }
        }
    }
}