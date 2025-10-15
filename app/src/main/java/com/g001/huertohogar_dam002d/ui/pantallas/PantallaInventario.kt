package com.g001.huertohogar_dam002d.ui.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.g001.huertohogar_dam002d.inventario.AjusteStockFormulario
import com.g001.huertohogar_dam002d.inventario.InventarioViewModel
import com.g001.huertohogar_dam002d.inventario.StockItem
import com.g001.huertohogar_dam002d.ui.componentes.BotonConfirmar
import com.g001.huertohogar_dam002d.ui.componentes.CampoTextoValidado

@Composable
fun PantallaInventario(
    navController: NavController,
    vm: InventarioViewModel = viewModel()
) {
    LaunchedEffect(Unit) { vm.cargarDemo() }
    val items by vm.items.collectAsState()

    var productoId by remember { mutableStateOf("") }
    var nuevoStockTexto by remember { mutableStateOf("") }

    val nuevoStock = nuevoStockTexto.toIntOrNull() ?: -1
    val formulario = AjusteStockFormulario(productoId = productoId, nuevoStock = nuevoStock)

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Inventario", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(10.dp))
        LazyColumn(Modifier.weight(1f)) {
            items(items) { item -> FilaStock(item) }
        }
        Spacer(Modifier.height(12.dp))
        CampoTextoValidado(
            valor = productoId,
            onValorChange = { productoId = it },
            etiqueta = "ID Producto",
            error = formulario.errores["productoId"]
        )
        Spacer(Modifier.height(8.dp))
        CampoTextoValidado(
            valor = nuevoStockTexto,
            onValorChange = { nuevoStockTexto = it },
            etiqueta = "Nuevo stock",
            error = formulario.errores["nuevoStock"]
        )
        Spacer(Modifier.height(12.dp))
        BotonConfirmar(
            texto = "Aplicar ajuste",
            onClick = { vm.aplicarAjuste(formulario) },
            habilitado = formulario.esValido()
        )
    }
}

@Composable
private fun FilaStock(item: StockItem) {
    Row(Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(item.nombre, style = MaterialTheme.typography.titleMedium)
            Text("Stock: ${item.stock} (mín: ${item.minimo})", style = MaterialTheme.typography.bodyMedium)
        }
        val color = if (item.stock > item.minimo) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        Text(if (item.stock > item.minimo) "OK" else "Bajo", color = color)
    }
}
