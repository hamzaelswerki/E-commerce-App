    package com.example.ecommercekotlin.model.entity

data class Ad(
    val ads_url: String,
    val created_at: String,
    val deleted_at: Any,
    val id: Int,
    val image: String,
    val product: Product,
    val product_id: Int,
    val status: String,
    val title: String,
    val type: String,
    val updated_at: String
)