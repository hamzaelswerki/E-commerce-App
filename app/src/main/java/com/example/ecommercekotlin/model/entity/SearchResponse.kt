package com.example.ecommercekotlin.model.entity

import com.example.ecommercekotlin.model.entity.Cart2

data class SearchResponse(
    val code: Int,
    val data: List<Product>,
    val message: String,
    val status: Boolean
)