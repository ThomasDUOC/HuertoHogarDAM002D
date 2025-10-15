package com.g001.huertohogar_dam002d.inventario

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InventarioViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<StockItem>>(emptyList())
    val items: StateFlow<List<StockItem>> = _items.asStateFlow()

    private val _ultimoAjuste = MutableStateFlow<AjusteStockFormulario?>(null)
    val ultimoAjuste: StateFlow<AjusteStockFormulario?> = _ultimoAjuste.asStateFlow()

    fun cargarDemo() {
        _items.value = listOf(
            StockItem(productoId = "p1", nombre = "Silla Eames", stock = 5, minimo = 1),
            StockItem(productoId = "p2", nombre = "Mesa Roble", stock = 2, minimo = 1)
        )
    }

    fun aplicarAjuste(formulario: AjusteStockFormulario) {
        if (!formulario.esValido()) return
        _items.value = _items.value.map {
            if (it.productoId == formulario.productoId) it.copy(stock = formulario.nuevoStock) else it
        }
        _ultimoAjuste.value = formulario
    }
}
