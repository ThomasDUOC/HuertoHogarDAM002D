package com.g001.huertohogar_dam002d.ui.componentes

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun CampoTextoValidado(
    valor: String,
    onValorChange: (String) -> Unit,
    etiqueta: String,
    error: String? = null,
    esPassword: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(etiqueta) },
        isError = error != null,
        visualTransformation = if (esPassword) PasswordVisualTransformation() else VisualTransformation.None,
        supportingText = {
            if (error != null) Text(error, color = MaterialTheme.colorScheme.error)
        },
        modifier = modifier
    )
}
