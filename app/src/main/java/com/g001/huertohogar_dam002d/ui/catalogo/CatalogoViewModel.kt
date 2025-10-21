package com.g001.huertohogar_dam002d.ui.catalogo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g001.huertohogar_dam002d.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CatalogoViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    val productosState: StateFlow<List<com.g001.huertohogar_dam002d.data.Producto>> = productRepository.productos
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}
