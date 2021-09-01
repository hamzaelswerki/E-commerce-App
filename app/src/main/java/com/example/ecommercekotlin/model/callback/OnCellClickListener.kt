package com.example.ecommercekotlin.model.callback

import com.example.ecommercekotlin.model.entity.Product
import java.util.*

interface OnCellClickListener {
    fun onProductClicked(product: Any)
}