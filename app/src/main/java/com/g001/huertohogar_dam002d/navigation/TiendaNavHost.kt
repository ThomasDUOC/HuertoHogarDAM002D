package com.g001.huertohogar_dam002d.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.g001.huertohogar_dam002d.ui.carrito.PantallaCarrito
import com.g001.huertohogar_dam002d.ui.catalogo.PantallaCatalogo
import com.g001.huertohogar_dam002d.ui.detalle.PantallaDetalle
import com.g001.huertohogar_dam002d.ui.historial.PantallaHistorial
import com.g001.huertohogar_dam002d.ui.inventario.PantallaInventario
import com.g001.huertohogar_dam002d.ui.login.LoginScreen

@Composable
fun TiendaNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Rutas.LOGIN) {
        composable(Rutas.LOGIN) {
            LoginScreen(
                navController = navController
            )
        }
        composable(Rutas.CATALOGO) { PantallaCatalogo(navController) }
        composable(Rutas.INVENTARIO) { PantallaInventario() }
        composable(
            route = "${Rutas.DETALLE}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) {
            PantallaDetalle(navController)
        }
        composable(Rutas.CARRITO) { PantallaCarrito(navController) }
        composable(Rutas.HISTORIAL) { PantallaHistorial() }
    }
}
