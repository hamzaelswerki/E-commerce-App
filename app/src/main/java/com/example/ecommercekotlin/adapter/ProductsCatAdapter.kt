package com.example.ecommercekotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercekotlin.databinding.ProductInCatBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.Product
import com.example.ecommercekotlin.utils.DiffUtilsProductsCategory
import java.util.Collections.emptyList

class ProductsCatAdapter( val onProductClickListener: OnCellClickListener): RecyclerView.Adapter<ProductsCatAdapter.ViewHolder>() {

  private var olderPersonList= emptyList<Product>()

    class ViewHolder(val binding:ProductInCatBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
 return  ViewHolder(ProductInCatBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView26.text=olderPersonList[position].name
      holder.binding.textView27.text=olderPersonList[position].price.toString()+"$"
      holder.binding.ratingBar.rating=olderPersonList[position].rate.toFloat()
        holder.itemView.setOnClickListener {
            onProductClickListener.onProductClicked(olderPersonList[position])
        }  }

    override fun getItemCount(): Int {
    return  olderPersonList.size
    }
    fun setData(newPersonList: List<Product>){
        val diff=DiffUtilsProductsCategory(olderPersonList,newPersonList)
        val result=DiffUtil.calculateDiff(diff)
        olderPersonList=newPersonList
        result.dispatchUpdatesTo(this)


    }
}