package com.g001.huertohogar_dam002d.ui.inventario

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.g001.huertohogar_dam002d.data.Producto

@Composable
fun PantallaInventario(inventarioViewModel: InventarioViewModel = hiltViewModel()) {
    val uiState by inventarioViewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedProducto by remember { mutableStateOf<Producto?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(uiState.productos) {
                producto ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(producto.nombre)
                    Row {
                        Button(onClick = { 
                            selectedProducto = producto
                            showDialog = true 
                        }) {
                            Text("Edit")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { inventarioViewModel.eliminarProducto(producto) }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        ProductoEditDialog(
            producto = selectedProducto,
            onDismiss = { 
                showDialog = false
                selectedProducto = null
            },
            onConfirm = { nombre, descripcion, detalle, precio, stock ->
                if (selectedProducto != null) {
                    inventarioViewModel.actualizarProducto(selectedProducto!!.id, nombre, descripcion, detalle, precio, stock)
                } else {
                    inventarioViewModel.agregarProducto(nombre, descripcion, detalle, precio, stock)
                }
                showDialog = false
                selectedProducto = null
            }
        )
    }
}
