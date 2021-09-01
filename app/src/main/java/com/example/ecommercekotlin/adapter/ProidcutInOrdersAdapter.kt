package com.example.ecommercekotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercekotlin.databinding.ItemCartBinding
import com.example.ecommercekotlin.databinding.ItemProductOrderBinding
import com.example.ecommercekotlin.model.entity.Cart2
import com.example.ecommercekotlin.model.entity.OrderProduct
import com.example.ecommercekotlin.utils.DiffUtilsCarts
import com.example.ecommercekotlin.utils.DiffUtilsProductsInOrders
import java.util.Collections.emptyList

class ProidcutInOrdersAdapter( ): RecyclerView.Adapter<ProidcutInOrdersAdapter.ViewHolder>() {

  private var olderPersonList= emptyList<OrderProduct>()



    class ViewHolder(val binding: ItemProductOrderBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
 return  ViewHolder(ItemProductOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.text=olderPersonList[position].product!!.name
      holder.binding.textView28.text="${olderPersonList[position].order_id} Order"
      holder.binding.tvNum.text=olderPersonList[position].quantity.toString()
      holder.binding.textView13.text=olderPersonList[position].product!!.price.toString()+" $"

    }

    override fun getItemCount(): Int {
    return  olderPersonList.size
    }
    fun setData(newPersonList: List<OrderProduct>){
        val diff=DiffUtilsProductsInOrders(olderPersonList, newPersonList)
        val result=DiffUtil.calculateDiff(diff)
        olderPersonList=newPersonList
        result.dispatchUpdatesTo(this)


    }
}