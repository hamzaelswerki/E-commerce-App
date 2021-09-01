package com.example.ecommercekotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeStatus (

    var status: Boolean? = null,

    var message: String? = null,

    var code: Int? = null,

    var data: Data1? = null
)

