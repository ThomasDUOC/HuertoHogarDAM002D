package com.g001.huertohogar_dam002d.logins

object SesionActual {
    var correoUsuario: String? = null
        private set
    var rol: RolUsuario? = null
        private set
    val estaLogueado: Boolean get() = correoUsuario != null && rol != null

    fun iniciarSesion(correo: String, rol: RolUsuario) {
        correoUsuario = correo
        this.rol = rol
    }

    fun cerrarSesion() {
        correoUsuario = null
        rol = null
    }
}

