package com.example.ecommercekotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserStatus (

    var status: Boolean? = null,

    var message: String? = null,

    var code: Int? = null,

    var data: User? = null
)

