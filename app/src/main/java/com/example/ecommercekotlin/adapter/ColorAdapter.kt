package com.example.ecommercekotlin.adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.databinding.ColorRowBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.Color

class ColorAdapter(val list: List<Color>
 , val onCellClickListener: OnCellClickListener
) :
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

var selected_position=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  binding =ColorRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           holder.bind(list.get(position))

        if (selected_position==position){
            holder.binding.divider.visibility= View.VISIBLE
        }else{
            holder.binding.divider.visibility= View.GONE

        }
    }

    override fun getItemCount() =list.size

  inner  class ViewHolder(val binding: ColorRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun  bind(color:  Color){

            binding.view.setBackgroundColor(android.graphics.Color.parseColor(color.value))

            binding.view.setOnClickListener {

            if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
            notifyItemChanged(selected_position)
            selected_position = adapterPosition
            notifyItemChanged(selected_position)
            onCellClickListener.onProductClicked(color)
            }

        }
    }

}