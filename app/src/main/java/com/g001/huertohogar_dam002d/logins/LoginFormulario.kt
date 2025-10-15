package com.g001.huertohogar_dam002d.logins

data class LoginFormulario(
    val correo: String = "",
    val contrasena: String = ""
) {
    val errores: Map<String, String>
        get() {
            val map = mutableMapOf<String, String>()
            if (correo.isBlank() || !correo.contains("@")) {
                map["correo"] = "Correo inválido"
            }
            if (contrasena.length < 6) {
                map["contrasena"] = "Mínimo 6 caracteres"
            }
            return map
        }

    fun esValido(): Boolean = errores.isEmpty()
}
