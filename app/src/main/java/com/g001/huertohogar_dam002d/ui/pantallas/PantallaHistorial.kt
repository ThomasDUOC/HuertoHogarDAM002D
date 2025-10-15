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
import com.g001.huertohogar_dam002d.logins.SesionActual
import com.g001.huertohogar_dam002d.pedidos.Pedido
import com.g001.huertohogar_dam002d.pedidos.PedidoViewModel

@Composable
fun PantallaHistorial(
    navController: NavController,
    vm: PedidoViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        vm.cargarDemo(clienteId = SesionActual.correoUsuario.orEmpty())
    }
    val historial by vm.historial.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Historial de pedidos", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(historial) { pedido -> FilaPedido(pedido) }
        }
    }
}

@Composable
private fun FilaPedido(pedido: Pedido) {
    Column(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text("Pedido #${pedido.id}", style = MaterialTheme.typography.titleMedium)
        Text("Items: ${pedido.items.sumOf { it.second }}", style = MaterialTheme.typography.bodyMedium)
        Text("Estado: ${pedido.estado}", style = MaterialTheme.typography.bodyMedium)
        Divider(Modifier.padding(top = 8.dp))
    }
}
