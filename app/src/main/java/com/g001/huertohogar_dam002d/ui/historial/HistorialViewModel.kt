package com.g001.huertohogar_dam002d.ui.historial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g001.huertohogar_dam002d.data.HistorialRepository
import com.g001.huertohogar_dam002d.data.PedidoConItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorialViewModel @Inject constructor(private val historialRepository: HistorialRepository) : ViewModel() {

    val historial: StateFlow<List<PedidoConItems>> = historialRepository.getOrderHistory()
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun checkout() {
        viewModelScope.launch {
            historialRepository.createOrderFromCart()
        }
    }
}
