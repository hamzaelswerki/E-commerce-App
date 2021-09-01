package com.example.ecommercekotlin.model.entity

import com.example.ecommercekotlin.model.entity.Ad
import com.example.ecommercekotlin.model.entity.Category
import com.example.ecommercekotlin.model.entity.Product
import com.google.gson.annotations.SerializedName

data class Data1(
    val ads: List<Ad>,
    val categories: List<Category>,
    val dailyDeals: List<Product>,
    @SerializedName("Recently added")
    val recentlyadded: List<Product>,
    val trending: List<Product>
)