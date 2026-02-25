package com.example.aptekaapp

object CartManager {
    private val cartItems = mutableListOf<Medicine>()

    fun addToCart(medicine: Medicine) {
        cartItems.add(medicine)
    }

    fun getCartItems(): List<Medicine> {
        return cartItems.toList()
    }

    fun clearCart() {
        cartItems.clear()
    }
}