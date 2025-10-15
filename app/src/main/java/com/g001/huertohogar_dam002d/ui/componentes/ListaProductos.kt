package com.g001.huertohogar_dam002d.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.g001.huertohogar_dam002d.catalogo.Producto

@Composable
fun ListaProductos(
    productos: List<Producto>,
    onProductoClick: (String) -> Unit,
    onAgregarCarrito: (Producto) -> Unit
) {
    LazyColumn {
        items(productos) { p ->
            Card(Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clickable { onProductoClick(p.id) },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(p.nombre, style = MaterialTheme.typography.titleMedium)
                        Text(p.descripcion, style = MaterialTheme.typography.bodyMedium)
                        Text("$${p.precio}", style = MaterialTheme.typography.bodyMedium)
                    }
                    TextButton(onClick = { onAgregarCarrito(p) }) { Text("Agregar") }
                }
            }
        }
    }
}
