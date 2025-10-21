package com.g001.huertohogar_dam002d.ui.historial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PantallaHistorial(historialViewModel: HistorialViewModel = hiltViewModel()) {
    val historial by historialViewModel.historial.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Purchase History") }) }
    ) {
        LazyColumn(modifier = Modifier.padding(it).padding(16.dp)) {
            items(historial) { pedidoConItems ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Order on ${SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(pedidoConItems.pedido.timestamp))}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        pedidoConItems.items.forEach { (item, producto) ->
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "${producto.nombre} x${item.quantity}")
                                Text(text = "$${item.price * item.quantity}")
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        val total = pedidoConItems.items.sumOf { (item, _) -> item.price * item.quantity }
                        Text(
                            text = "Total: $$total",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.align(androidx.compose.ui.Alignment.End)
                        )
                    }
                }
            }
        }
    }
}