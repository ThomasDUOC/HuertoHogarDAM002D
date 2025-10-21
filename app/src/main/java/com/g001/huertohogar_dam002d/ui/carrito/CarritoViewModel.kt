package com.g001.huertohogar_dam002d.ui.carrito

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g001.huertohogar_dam002d.data.CarritoItem
import com.g001.huertohogar_dam002d.data.CarritoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarritoViewModel @Inject constructor(private val carritoRepository: CarritoRepository) : ViewModel() {

    val carritoItems: StateFlow<List<CarritoItem>> = carritoRepository.getItems()
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun addToCart(productId: Long, quantity: Int) {
        viewModelScope.launch {
            carritoRepository.addToCart(productId, quantity)
        }
    }

    fun removeFromCart(item: CarritoItem) {
        viewModelScope.launch {
            carritoRepository.removeFromCart(item)
        }
    }
}
