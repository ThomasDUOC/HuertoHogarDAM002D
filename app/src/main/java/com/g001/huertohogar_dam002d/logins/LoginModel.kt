package com.g001.huertohogar_dam002d.logins

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class EstadoLogin {
    data object Idle : EstadoLogin()
    data class Error(val mensaje: String) : EstadoLogin()
    data class Exitoso(val rol: RolUsuario) : EstadoLogin()
}

enum class RolUsuario { CLIENTE, ADMIN }

class LoginViewModel : ViewModel() {
    private val _estado = MutableStateFlow<EstadoLogin>(EstadoLogin.Idle)
    val estado: StateFlow<EstadoLogin> = _estado.asStateFlow()

    fun intentarLogin(formulario: LoginFormulario) {
        if (!formulario.esValido()) {
            _estado.value = EstadoLogin.Error("Revisa los campos")
            return
        }
        val rol = if (formulario.correo.contains("admin")) RolUsuario.ADMIN else RolUsuario.CLIENTE
        SesionActual.iniciarSesion(correo = formulario.correo, rol = rol)
        _estado.value = EstadoLogin.Exitoso(rol)
    }

    fun cerrarSesion() {
        SesionActual.cerrarSesion()
        _estado.value = EstadoLogin.Idle
    }
}
