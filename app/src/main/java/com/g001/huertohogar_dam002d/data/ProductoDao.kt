package com.g001.huertohogar_dam002d.data

import com.g001.huertohogar_dam002d.data.Producto
import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface ProductoDao {

    @Query("SELECT * FROM productos ORDER BY id DESC")
    fun getAll(): Flow<List<Producto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Producto): Long

    @Update
    suspend fun update(product: Producto)

    @Delete
    suspend fun delete(product: Producto)

    @Query("SELECT * FROM productos WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): Producto?
}