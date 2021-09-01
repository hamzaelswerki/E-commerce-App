package com.example.ecommercekotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercekotlin.databinding.ProductInCatBinding
import com.example.ecommercekotlin.databinding.RowBookmarkBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.BookMark
import com.example.ecommercekotlin.model.entity.Product
import com.example.ecommercekotlin.utils.DiffUtilsBookMark
import com.example.ecommercekotlin.utils.DiffUtilsProductsCategory
import java.util.Collections.emptyList

class BookMarkAdapter(val onFavClickListener: OnCellClickListener): RecyclerView.Adapter<BookMarkAdapter.ViewHolder>() {

  private var olderPersonList= emptyList<BookMark>()


    class ViewHolder(val binding:RowBookmarkBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
 return  ViewHolder(RowBookmarkBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView26.text=olderPersonList[position].product.name
      holder.binding.textView27.text=olderPersonList[position].product.price.toString()
      holder.binding.ratingBar.rating=olderPersonList[position].product.rate.toFloat()
        holder.binding.imgFav.setOnClickListener {
            onFavClickListener.onProductClicked(olderPersonList[position].product)
        }
    }

    override fun getItemCount(): Int {
    return  olderPersonList.size
    }
    fun setData(newPersonList: List<BookMark>){
        val diff= DiffUtilsBookMark(olderPersonList,newPersonList)
        val result=DiffUtil.calculateDiff(diff)
        olderPersonList=newPersonList
        result.dispatchUpdatesTo(this)


    }
}