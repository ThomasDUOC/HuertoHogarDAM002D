package com.g001.huertohogar_dam002d.ui.inventario

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import com.g001.huertohogar_dam002d.data.Producto

@Composable
fun ProductoEditDialog(
    producto: Producto?,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String, Double, Int) -> Unit
) {
    var nombre by remember { mutableStateOf(producto?.nombre ?: "") }
    var descripcion by remember { mutableStateOf(producto?.descripcion ?: "") }
    var detalle by remember { mutableStateOf(producto?.detalle ?: "") }
    var precio by remember { mutableStateOf(producto?.precio?.toString() ?: "") }
    var stock by remember { mutableStateOf(producto?.stock?.toString() ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (producto == null) "Add Product" else "Edit Product") },
        text = {
            Column {
                TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Name") })
                TextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Description") })
                TextField(value = detalle, onValueChange = { detalle = it }, label = { Text("Detail") })
                TextField(value = precio, onValueChange = { precio = it }, label = { Text("Price") })
                TextField(value = stock, onValueChange = { stock = it }, label = { Text("Stock") })
            }
        },
        confirmButton = {
            Button(onClick = { 
                onConfirm(nombre, descripcion, detalle, precio.toDouble(), stock.toInt())
            }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
