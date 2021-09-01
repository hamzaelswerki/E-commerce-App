package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.adapter.BookMarkAdapter
import com.example.ecommercekotlin.adapter.ProductsCatAdapter
import com.example.ecommercekotlin.adapter.ProidcutInOrdersAdapter
import com.example.ecommercekotlin.databinding.FragmentBookmarkBinding
import com.example.ecommercekotlin.databinding.FragmentProductsCategoryBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.BookMark
import com.example.ecommercekotlin.model.entity.OrderProduct
import com.example.ecommercekotlin.model.entity.Product
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.BookMarkViewModel
import com.example.ecommercekotlin.viewModel.CategoryViewModel

class ProductsInOrderFragment : Fragment()  {


    lateinit var binding: FragmentBookmarkBinding
    lateinit var adapter: ProidcutInOrdersAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding= FragmentBookmarkBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val  view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgLottie.visibility = View.GONE
        binding.lottie.visibility = View.GONE
        if (arguments!=null){

           val x= ProductsInOrderFragmentArgs.fromBundle(requireArguments()).ProductOrder
          createAdapter(x.toList())
        }

    }


    fun showError(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(text)
            .setContentText("ok")
            .show()
    }

    fun  createAdapter(list: List<OrderProduct>){
        adapter= ProidcutInOrdersAdapter()
        binding.tvBookMark.adapter=adapter
        binding.tvBookMark.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        adapter.setData(list)
    }
    fun showDilog(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setContentText(text)
            .show()
    }




}