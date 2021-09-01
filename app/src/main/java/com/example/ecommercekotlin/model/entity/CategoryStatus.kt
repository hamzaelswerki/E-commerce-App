package com.example.ecommercekotlin.model.entity

import com.example.ecommercekotlin.model.entity.Product

data class CategoryStatus(
    val code: Int,
    val `data`: List<Product>,
    val message: String,
    val status: Boolean
)