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
import com.g001.huertohogar_dam002d.carrito.CarritoItem
import com.g001.huertohogar_dam002d.carrito.CarritoViewModel
import com.g001.huertohogar_dam002d.carrito.EstadoCarrito
import com.g001.huertohogar_dam002d.ui.componentes.BotonConfirmar
import com.g001.huertohogar_dam002d.ui.animaciones.AnimacionAgregarCarrito

@Composable
fun PantallaCarrito(
    navController: NavController,
    vm: CarritoViewModel = viewModel()
) {
    val items by vm.items.collectAsState()
    val estado by vm.estado.collectAsState()
    var animVisible by remember { mutableStateOf(false) }

    LaunchedEffect(estado) {
        if (estado is EstadoCarrito.Actualizado) {
            animVisible = true
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tu carrito", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        AnimacionAgregarCarrito(visible = animVisible) {
            Text("Actualizado", color = MaterialTheme.colorScheme.primary)
        }
        Spacer(Modifier.height(8.dp))
        LazyColumn(Modifier.weight(1f)) {
            items(items) { item ->
                FilaItemCarrito(item = item, onEliminar = { vm.eliminar(item.productoId) })
            }
        }
        val total = items.sumOf { it.subtotal }
        Text("Total: $total", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))
        BotonConfirmar(texto = "Confirmar compra", onClick = { vm.confirmar() }, habilitado = items.isNotEmpty())
    }
}

@Composable
private fun FilaItemCarrito(item: CarritoItem, onEliminar: () -> Unit) {
    Row(Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(item.nombre, style = MaterialTheme.typography.titleMedium)
            Text("x${item.cantidad} • ${item.precioUnitario}", style = MaterialTheme.typography.bodyMedium)
        }
        TextButton(onClick = onEliminar) { Text("Eliminar") }
    }
}
