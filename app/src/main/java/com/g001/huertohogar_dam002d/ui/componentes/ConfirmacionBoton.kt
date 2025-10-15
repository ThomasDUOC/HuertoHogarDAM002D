package com.g001.huertohogar_dam002d.ui.componentes

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BotonConfirmar(
    texto: String,
    onClick: () -> Unit,
    habilitado: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(onClick = onClick, enabled = habilitado, modifier = modifier) {
        Text(texto)
    }
}
