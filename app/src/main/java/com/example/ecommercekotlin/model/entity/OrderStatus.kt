package com.example.ecommercekotlin.model.entity

import android.os.Parcel
import android.os.Parcelable

data class OrderStatus(
    val code: Int,
    val data: List<Order>,
    val message: String,
    val status: Boolean
)

data class Order(
    val created_at: String,
    val deleted_at: Any,
    val id: Int,
    val order_products: List<OrderProduct>,
    val status: String,
    val total_price: Int,
    val updated_at: String,
    val user_id: Int
)

