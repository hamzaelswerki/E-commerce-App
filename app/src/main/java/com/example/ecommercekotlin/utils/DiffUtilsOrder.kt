package com.example.ecommercekotlin.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.ecommercekotlin.model.entity.BookMark
import com.example.ecommercekotlin.model.entity.Order
import com.example.ecommercekotlin.model.entity.Product

class DiffUtilsOrder(private val olderList: List<Order>,
                     private val newList: List<Order>): DiffUtil.Callback() {

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
            olderList[oldItemPosition].status!=newList[newItemPosition].status->false
            olderList[oldItemPosition].total_price!=newList[newItemPosition].total_price->false
            else -> true
        }
        }
}