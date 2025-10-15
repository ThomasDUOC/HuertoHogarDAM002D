package com.g001.huertohogar_dam002d.inventario

data class AjusteStockFormulario(
    val productoId: String = "",
    val nuevoStock: Int = 0
) {
    val errores: Map<String, String>
        get() {
            val map = mutableMapOf<String, String>()
            if (productoId.isBlank()) map["productoId"] = "Producto inválido"
            if (nuevoStock < 0) map["nuevoStock"] = "Stock no puede ser negativo"
            return map
        }
    fun esValido(): Boolean = errores.isEmpty()
}
