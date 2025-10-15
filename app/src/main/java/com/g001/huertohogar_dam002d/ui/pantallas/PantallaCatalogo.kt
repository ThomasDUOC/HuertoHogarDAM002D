package com.g001.huertohogar_dam002d.ui.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.g001.huertohogar_dam002d.catalogo.CatalogoViewModel
import com.g001.huertohogar_dam002d.navigation.Rutas
import com.g001.huertohogar_dam002d.ui.componentes.ListaProductos

@Composable
fun PantallaCatalogo(navController: NavController) {
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Catálogo de productos", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("Aquí se mostrarán los productos...")
    }
}

