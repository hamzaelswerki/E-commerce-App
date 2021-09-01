package com.example.ecommercekotlin.model.entity

import com.example.ecommercekotlin.model.entity.Cart2

data class CartResponse(
    val code: Int,
    val data: List<Cart2>,
    val message: String,
    val status: Boolean
)