package com.g001.huertohogar_dam002d.ui.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.g001.huertohogar_dam002d.logins.EstadoLogin
import com.g001.huertohogar_dam002d.logins.LoginFormulario
import com.g001.huertohogar_dam002d.logins.LoginViewModel
import com.g001.huertohogar_dam002d.logins.RolUsuario
import com.g001.huertohogar_dam002d.ui.componentes.CampoTextoValidado
import com.g001.huertohogar_dam002d.ui.componentes.BotonConfirmar
import kotlin.text.get

@Composable
fun PantallaLogin(onLoginExitoso: (RolUsuario) -> Unit) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = contrasena, onValueChange = { contrasena = it }, label = { Text("Contraseña") })
        Spacer(Modifier.height(16.dp))
        Button(onClick = { onLoginExitoso(RolUsuario.CLIENTE) }, modifier = Modifier.fillMaxWidth()) {
            Text("Ingresar")
        }
    }
}

enum class RolUsuario { CLIENTE, ADMIN }
