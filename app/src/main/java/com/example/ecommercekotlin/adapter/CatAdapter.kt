package com.example.ecommercekotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommercekotlin.databinding.ItemCatBinding
import com.example.ecommercekotlin.model.entity.Category
import android.widget.Toast.makeText as makeText1

class CatAdapter(
    val list: List<Category>,
    val context: Context?,
    val onCatClick: OnCatClickListener) : RecyclerView.Adapter<CatAdapter.ViewHolder>() {


    interface OnCatClickListener {
        fun onClicked(productId: Int,catmName:String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCatBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener {
              onCatClick.onClicked(list.get(position).id,list.get(position).title)
        }
    }

    override fun getItemCount() = list.size
    class ViewHolder(val binding: ItemCatBinding ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(category: Category) {
            binding.textView8.setText(category.title)
            Glide.with(binding.imageViewProfile).load("https://via.placeholder.com/640x480.png")
                .into(binding.imageViewProfile)
            Glide.with(binding.imageView3).load("https://via.placeholder.com/640x480.png")
                .into(binding.imageView3)
        }


    }

}