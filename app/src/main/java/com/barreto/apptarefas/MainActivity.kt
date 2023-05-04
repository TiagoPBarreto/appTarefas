package com.barreto.apptarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.barreto.apptarefas.ui.theme.AppTarefasTheme
import com.barreto.apptarefas.view.ListarTarefas
import com.barreto.apptarefas.view.SalvarTarefa

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTarefasTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "listaTarefas"){
                    composable(
                        route = "listaTarefas"
                    ){
                        ListarTarefas(navController)
                    }
                    composable(
                        route = "salvarTarefas"
                    ){
                        SalvarTarefa(navController)
                    }
                }
            }
        }
    }
}

