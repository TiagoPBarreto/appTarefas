package com.barreto.apptarefas.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.barreto.apptarefas.ui.theme.light_blue
import com.barreto.apptarefas.ui.theme.white

@Composable
fun Botao(
    onClick:() -> Unit,
    modifier: Modifier,
    texto:String
){
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = light_blue,
            contentColor = white
        )
    ) {
        Text(
            text = texto,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}