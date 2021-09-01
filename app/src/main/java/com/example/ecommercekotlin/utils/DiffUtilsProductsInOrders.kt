package com.example.ecommercekotlin.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.ecommercekotlin.model.entity.Cart2
import com.example.ecommercekotlin.model.entity.OrderProduct
import com.example.ecommercekotlin.model.entity.Product

class DiffUtilsProductsInOrders(private val olderList: List<OrderProduct>,
                                private val newList: List<OrderProduct>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
    return    olderList.size
    }

    override fun getNewListSize(): Int {
   return  newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return  olderList[oldItemPosition].id==newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            olderList[oldItemPosition].id!=newList[newItemPosition].id->false
            olderList[oldItemPosition].quantity!=newList[newItemPosition].quantity->false
            olderList[oldItemPosition].order_id!=newList[newItemPosition].order_id->false
            olderList[oldItemPosition].color_id!=newList[newItemPosition].color_id->false
            olderList[oldItemPosition].size_id!=newList[newItemPosition].size_id->false
            else -> true
        }
        }
}