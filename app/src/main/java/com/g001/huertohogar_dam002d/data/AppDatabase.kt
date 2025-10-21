package com.g001.huertohogar_dam002d.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Producto::class, User::class, CarritoItem::class, Pedido::class, PedidoItem::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductoDao
    abstract fun userDao(): UserDao
    abstract fun carritoDao(): CarritoDao
    abstract fun pedidoDao(): PedidoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "productos.db"
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
    }
}
