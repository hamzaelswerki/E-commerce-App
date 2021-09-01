package com.example.ecommercekotlin.model.entity

data class CartStatus(
    val code: Int,
    val data: Cart,
    val message: String,
    val status: Boolean
)

