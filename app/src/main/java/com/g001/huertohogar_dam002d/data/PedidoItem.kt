package com.g001.huertohogar_dam002d.data

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "pedido_items",
    primaryKeys = ["pedidoId", "productId"],
    foreignKeys = [
        ForeignKey(
            entity = Pedido::class,
            parentColumns = ["id"],
            childColumns = ["pedidoId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Producto::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PedidoItem(
    val pedidoId: Long,
    val productId: Long,
    val quantity: Int,
    val price: Double // Price at the time of purchase
)
