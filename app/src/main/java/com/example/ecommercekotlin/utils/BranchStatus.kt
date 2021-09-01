package com.example.ecommercekotlin.utils

data class BranchStatus(
    val code: Int,
    val data: List<Branch>,
    val message: String,
    val status: Boolean
)

data class Branch(
    val active: String,
    val address: String,
    val created_at: String,
    val id: Int,
    val latitude: String,
    val logo: String,
    val longitude: String,
    val name: String,
    val updated_at: String,
    val zone: String
)