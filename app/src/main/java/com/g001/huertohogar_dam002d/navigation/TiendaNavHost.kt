package com.g001.huertohogar_dam002d.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.g001.huertohogar_dam002d.ui.pantallas.PantallaLogin
import com.g001.huertohogar_dam002d.logins.RolUsuario
import com.g001.huertohogar_dam002d.ui.pantallas.PantallaCatalogo
import com.g001.huertohogar_dam002d.ui.pantallas.PantallaCarrito
import com.g001.huertohogar_dam002d.ui.pantallas.PantallaHistorial
import com.g001.huertohogar_dam002d.ui.pantallas.PantallaInventario

@Composable
fun TiendaNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Rutas.LOGIN) {
        composable(Rutas.LOGIN) {
            PantallaLogin(onLoginExitoso = { rol ->
                navController.navigate(Rutas.CATALOGO) {
                    popUpTo(Rutas.LOGIN) { inclusive = true }
                }
            })
        }
        composable(Rutas.CATALOGO) { PantallaCatalogo(navController) }

    }
}


