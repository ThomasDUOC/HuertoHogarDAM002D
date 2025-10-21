package com.g001.huertohogar_dam002d.ui.detalle

import androidx.compose.foundation.layout.*
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
import com.g001.huertohogar_dam002d.navigation.Rutas
import com.g001.huertohogar_dam002d.ui.carrito.CarritoViewModel

@Composable
fun PantallaDetalle(
    navController: NavController,
    detalleViewModel: DetalleViewModel = hiltViewModel(),
    carritoViewModel: CarritoViewModel = hiltViewModel()
) {
    val producto by detalleViewModel.producto.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Detail") },
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
        producto?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            ) {
                Text(text = it.nombre, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.descripcion, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.detalle, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Price: $${it.precio}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Stock: ${it.stock}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { 
                        carritoViewModel.addToCart(it.id, 1) 
                        navController.navigate(Rutas.CARRITO)
                    }, 
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add to Cart")
                }
            }
        }
    }
}