package com.example.ecommercekotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercekotlin.databinding.ItemCartBinding
import com.example.ecommercekotlin.model.entity.Cart2
import com.example.ecommercekotlin.utils.DiffUtilsCarts
import java.util.Collections.emptyList

class CartsAdapter(val onQuantiyChanged: OnQuantiyChanged): RecyclerView.Adapter<CartsAdapter.ViewHolder>() {

  private var olderPersonList= emptyList<Cart2>()

    interface OnQuantiyChanged {
        fun onClickedPlusButton(tv: TextView?, productId: Int)
        fun onClickedMinusButton(tv: TextView?, productId: Int)
    }

    class ViewHolder(val binding: ItemCartBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
 return  ViewHolder(ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.text=olderPersonList[position].product.name
      holder.binding.tvNum.text=olderPersonList[position].quantity.toString()
      holder.binding.textView13.text=olderPersonList[position].product.price.toString()+" $"

        holder.binding.plus.setOnClickListener {onQuantiyChanged.onClickedPlusButton(holder.binding.tvNum,olderPersonList[position].product.id)  }
        holder.binding.minus.setOnClickListener {onQuantiyChanged.onClickedMinusButton(holder.binding.tvNum,olderPersonList[position].product.id)  }
    }

    override fun getItemCount(): Int {
    return  olderPersonList.size
    }
    fun setData(newPersonList: List<Cart2>){
        val diff=DiffUtilsCarts(olderPersonList, newPersonList)
        val result=DiffUtil.calculateDiff(diff)
        olderPersonList=newPersonList
        result.dispatchUpdatesTo(this)


    }
}