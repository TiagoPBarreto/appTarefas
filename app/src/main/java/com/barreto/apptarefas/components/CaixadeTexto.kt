package com.barreto.apptarefas.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.barreto.apptarefas.ui.theme.ShapeEditText
import com.barreto.apptarefas.ui.theme.black
import com.barreto.apptarefas.ui.theme.light_blue
import com.barreto.apptarefas.ui.theme.white


@Composable
fun CaixadeTexto(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType
){
    OutlinedTextField(
        value = value,
        onValueChange,
        modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = black,
            focusedBorderColor = light_blue,
            focusedLabelColor = light_blue,
            backgroundColor = white,
            cursorColor = light_blue
        ),
        shape = ShapeEditText.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}