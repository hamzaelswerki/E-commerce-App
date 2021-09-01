package com.example.ecommercekotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.databinding.ItemBranchBinding
import com.example.ecommercekotlin.databinding.SizeRowBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.Size
import com.example.ecommercekotlin.utils.Branch

class BranchesAdapter(
    val list: List<Branch>, val onCellClickListener: OnCellClickListener) :
    RecyclerView.Adapter<BranchesAdapter.ViewHolder>() {
    var selected_position = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  binding =ItemBranchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           holder.bind(list.get(position))

        if (selected_position == position) {
            holder.binding.textView8.setTextColor(Color.RED)
            holder.binding.imageViewProfile.setImageResource(R.drawable.ic_maps)
        } else {
            holder.binding.textView8.setTextColor(Color.BLACK)
            holder.binding.imageViewProfile.setImageResource(R.drawable.ic_maps_black)

        }

    }

    override fun getItemCount() =list.size

  inner  class ViewHolder(val binding: ItemBranchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun  bind(size: Branch){
            binding.textView8.setText(size.address)


            binding.viewItem.setOnClickListener{

                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                notifyItemChanged(selected_position)
                selected_position = adapterPosition
                notifyItemChanged(selected_position)
                onCellClickListener.onProductClicked(size)

            }
        }
    }

}