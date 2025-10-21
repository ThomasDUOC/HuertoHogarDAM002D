package com.g001.huertohogar_dam002d.ui.catalogo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.g001.huertohogar_dam002d.data.Producto
import com.g001.huertohogar_dam002d.navigation.Rutas

@Composable
fun PantallaCatalogo(
    navController: NavController,
    catalogoViewModel: CatalogoViewModel = hiltViewModel()
) {
    val productos by catalogoViewModel.productosState.collectAsState()

    Scaffold(
        topBar = { 
            TopAppBar(
                title = { Text("Product Catalog") },
                actions = {
                    IconButton(onClick = { navController.navigate(Rutas.CARRITO) }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Shopping Cart")
                    }
                    IconButton(onClick = { navController.navigate(Rutas.HISTORIAL) }) {
                        Icon(Icons.Default.History, contentDescription = "Purchase History")
                    }
                }
            ) 
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it).padding(16.dp)) {
            items(productos) {
                producto ->
                ProductoCard(producto = producto, onProductClick = {
                    navController.navigate("${Rutas.DETALLE}/${producto.id}")
                })
            }
        }
    }
}

@Composable
fun ProductoCard(producto: Producto, onProductClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = producto.nombre, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = producto.descripcion, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$${producto.precio}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onProductClick) {
                Text("View Details")
            }
        }
    }
}
