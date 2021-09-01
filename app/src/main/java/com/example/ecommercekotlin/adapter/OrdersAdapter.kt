package com.example.ecommercekotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercekotlin.databinding.ItemOrderBinding
import com.example.ecommercekotlin.databinding.ProductInCatBinding
import com.example.ecommercekotlin.databinding.RowBookmarkBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.BookMark
import com.example.ecommercekotlin.model.entity.Order
import com.example.ecommercekotlin.model.entity.Product
import com.example.ecommercekotlin.utils.DiffUtilsBookMark
import com.example.ecommercekotlin.utils.DiffUtilsOrder
import com.example.ecommercekotlin.utils.DiffUtilsProductsCategory
import java.util.Collections.emptyList

class OrdersAdapter(val onFavClickListener: OnCellClickListener): RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

  private var olderPersonList= emptyList<Order>()


    class ViewHolder(val binding:ItemOrderBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
 return  ViewHolder(ItemOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtTimeReamining.text=olderPersonList[position].created_at
      holder.binding.txtNumberOrder.text="${olderPersonList[position].id} Order"
      holder.binding.txtAddress.text=olderPersonList[position].status
      holder.binding.txtPrice.text=olderPersonList[position].total_price.toString()+"$"
        holder.itemView.setOnClickListener {
            onFavClickListener.onProductClicked(olderPersonList[position])
        }
    }

    override fun getItemCount(): Int {
    return  olderPersonList.size
    }
    fun setData(newPersonList: List<Order>){
        val diff= DiffUtilsOrder(olderPersonList,newPersonList)
        val result=DiffUtil.calculateDiff(diff)
        olderPersonList=newPersonList
        result.dispatchUpdatesTo(this)


    }
}