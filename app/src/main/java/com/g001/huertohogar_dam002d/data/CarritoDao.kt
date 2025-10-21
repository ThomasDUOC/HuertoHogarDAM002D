package com.g001.huertohogar_dam002d.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoDao {
    @Query("SELECT * FROM carrito_items")
    fun getAll(): Flow<List<CarritoItem>>

    @Query("SELECT * FROM carrito_items WHERE productId = :productId")
    suspend fun getItemByProductId(productId: Long): CarritoItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CarritoItem)

    @Update
    suspend fun update(item: CarritoItem)

    @Delete
    suspend fun delete(item: CarritoItem)
}
