package com.example.ecommercekotlin.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.ecommercekotlin.model.entity.BookMark
import com.example.ecommercekotlin.model.entity.Product

class DiffUtilsBookMark(private val olderList: List<BookMark>,
                        private val newList: List<BookMark>): DiffUtil.Callback() {

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
            olderList[oldItemPosition].product.name!=newList[newItemPosition].product.name->false
            olderList[oldItemPosition].product.price!=newList[newItemPosition].product.price->false
            else -> true
        }
        }
}