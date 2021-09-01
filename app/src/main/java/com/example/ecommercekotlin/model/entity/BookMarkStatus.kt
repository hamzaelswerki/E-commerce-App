package com.example.ecommercekotlin.model.entity

import com.example.ecommercekotlin.model.entity.Cart2

data class BookMarkStatus(
    val code: Int,
    val data: List<BookMark>,
    val message: String,
    val status: Boolean
)