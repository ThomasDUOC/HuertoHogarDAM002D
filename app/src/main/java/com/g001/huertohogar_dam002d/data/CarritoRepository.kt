package com.g001.huertohogar_dam002d.data

import kotlinx.coroutines.flow.Flow

class CarritoRepository(private val carritoDao: CarritoDao) {

    fun getItems(): Flow<List<CarritoItem>> = carritoDao.getAll()

    suspend fun addToCart(productId: Long, quantity: Int) {
        val existingItem = carritoDao.getItemByProductId(productId)
        if (existingItem != null) {
            val newQuantity = existingItem.quantity + quantity
            carritoDao.update(existingItem.copy(quantity = newQuantity))
        } else {
            carritoDao.insert(CarritoItem(productId = productId, quantity = quantity))
        }
    }

    suspend fun removeFromCart(item: CarritoItem) {
        carritoDao.delete(item)
    }
}
