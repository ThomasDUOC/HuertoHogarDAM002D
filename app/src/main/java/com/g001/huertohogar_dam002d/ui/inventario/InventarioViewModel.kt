package com.g001.huertohogar_dam002d.ui.inventario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g001.huertohogar_dam002d.data.Producto
import com.g001.huertohogar_dam002d.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class InventarioUiState(
    val productos: List<Producto> = emptyList()
)

@HiltViewModel
class InventarioViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    val uiState: StateFlow<InventarioUiState> = productRepository.productos
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = InventarioUiState()
        )

    fun agregarProducto(nombre: String, descripcion: String, detalle: String, precio: Double, stock: Int) {
        viewModelScope.launch {
            productRepository.agregar(nombre, descripcion, detalle, precio, stock)
        }
    }

    fun actualizarProducto(id: Long, nombre: String, descripcion: String, detalle: String, precio: Double, stock: Int) {
        viewModelScope.launch {
            productRepository.actualizar(id, nombre, descripcion, detalle, precio, stock)
        }
    }

    fun eliminarProducto(producto: Producto) {
        viewModelScope.launch {
            productRepository.eliminar(producto)
        }
    }
}
