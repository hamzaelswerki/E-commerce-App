package com.example.ecommercekotlin.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.ecommercekotlin.model.entity.Product

class DiffUtilsProductsCategory(private val olderList: List<Product>,
                                private val newList: List<Product>): DiffUtil.Callback() {

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
            olderList[oldItemPosition].name!=newList[newItemPosition].name->false
            olderList[oldItemPosition].price!=newList[newItemPosition].price->false
            else -> true
        }
        }
}