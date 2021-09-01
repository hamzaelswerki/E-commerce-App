package com.example.ecommercekotlin.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommercekotlin.databinding.ProductItemBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.Product

class HomeProductAdapter(val list: List<Product>, val onProductClickListener: OnCellClickListener) :
    RecyclerView.Adapter<HomeProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  binding =ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           holder.bind(list.get(position))
        holder.itemView.setOnClickListener {
            onProductClickListener.onProductClicked(list.get(position))
        }
    }

    override fun getItemCount() =list.size

    class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun  bind(product: Product){
            Glide.with(binding.imgP).load("https://via.placeholder.com/640x480.png").into(binding.imgP)

            binding.textView10.setText(product.name)
            binding.textView9.setText(product.price.toString())
            binding.priceBefore.setText(product.price.toString())
            binding.priceBefore.setPaintFlags(binding.priceBefore.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
            binding.priceAfter.setText(product.sale_price.toString())
        }
    }

}