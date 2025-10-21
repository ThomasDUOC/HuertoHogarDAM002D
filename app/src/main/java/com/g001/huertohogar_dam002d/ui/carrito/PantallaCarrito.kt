package com.g001.huertohogar_dam002d.ui.carrito

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.g001.huertohogar_dam002d.navigation.Rutas
import com.g001.huertohogar_dam002d.ui.catalogo.CatalogoViewModel
import com.g001.huertohogar_dam002d.ui.historial.HistorialViewModel

@Composable
fun PantallaCarrito(
    navController: NavController,
    carritoViewModel: CarritoViewModel = hiltViewModel(),
    catalogoViewModel: CatalogoViewModel = hiltViewModel(),
    historialViewModel: HistorialViewModel = hiltViewModel()
) {
    val carritoItems by carritoViewModel.carritoItems.collectAsState()
    val productos by catalogoViewModel.productosState.collectAsState()

    val carritoConDetalles = carritoItems.mapNotNull { item ->
        productos.find { it.id == item.productId }?.let { producto ->
            Pair(item, producto)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Shopping Cart") }) }
    ) {
        Column(modifier = Modifier.padding(it).fillMaxSize()) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(carritoConDetalles) { (item, producto) ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "${producto.nombre} x${item.quantity}")
                        Text(text = "$${producto.precio * item.quantity}")
                        Button(onClick = { carritoViewModel.removeFromCart(item) }) {
                            Text("Remove")
                        }
                    }
                }
            }
            val total = carritoConDetalles.sumOf { (item, producto) -> producto.precio * item.quantity }
            Text(text = "Total: $$total", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(16.dp))
            Button(
                onClick = { 
                    historialViewModel.checkout()
                    navController.navigate(Rutas.HISTORIAL)
                }, 
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Checkout")
            }
        }
    }
}
