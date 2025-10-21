package com.g001.huertohogar_dam002d.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.g001.huertohogar_dam002d.data.UserRole
import com.g001.huertohogar_dam002d.navigation.Rutas

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by loginViewModel.uiState.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (uiState.loginSuccess) {
        LaunchedEffect(Unit) {
            val destination = if (uiState.user?.role == UserRole.ADMIN) Rutas.INVENTARIO else Rutas.CATALOGO
            navController.navigate(destination) {
                popUpTo(Rutas.LOGIN) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { loginViewModel.login(username, password) }) {
                Text("Login")
            }
            uiState.error?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}