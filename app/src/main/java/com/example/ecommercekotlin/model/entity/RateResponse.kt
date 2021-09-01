package com.example.ecommercekotlin.model.entity

data class RateResponse(
    val code: Int,
    val data: Double,
    val message: String,
    val status: Boolean
)