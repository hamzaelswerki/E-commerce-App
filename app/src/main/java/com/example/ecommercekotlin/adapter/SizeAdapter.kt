package com.example.ecommercekotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.databinding.SizeRowBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.Size

class SizeAdapter(
    val list: List<Size>, val onCellClickListener: OnCellClickListener) :
    RecyclerView.Adapter<SizeAdapter.ViewHolder>() {
    var selected_position = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  binding =SizeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           holder.bind(list.get(position))

        if (selected_position == position) {
            holder.binding.tvsize.setBackgroundResource(R.drawable.ic_size_back_clicked)
        } else {
            holder.binding.tvsize.setBackgroundResource(R.drawable.ic_size_back)
        }

    }

    override fun getItemCount() =list.size

  inner  class ViewHolder(val binding: SizeRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun  bind(size: Size){
            binding.tvsize.setText(size.value)

            binding.tvsize.setOnClickListener{

                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                notifyItemChanged(selected_position)
                selected_position = adapterPosition
                notifyItemChanged(selected_position)
                onCellClickListener.onProductClicked(size)

            }
        }
    }

}