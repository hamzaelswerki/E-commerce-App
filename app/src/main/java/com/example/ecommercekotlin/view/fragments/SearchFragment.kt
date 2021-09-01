package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.adapter.BookMarkAdapter
import com.example.ecommercekotlin.adapter.HomeProductAdapter
import com.example.ecommercekotlin.adapter.ProductsCatAdapter
import com.example.ecommercekotlin.databinding.FragmentBookmarkBinding
import com.example.ecommercekotlin.databinding.FragmentProductsCategoryBinding
import com.example.ecommercekotlin.databinding.FragmentSearchBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.BookMark
import com.example.ecommercekotlin.model.entity.Product
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.BookMarkViewModel
import com.example.ecommercekotlin.viewModel.CategoryViewModel
import com.example.ecommercekotlin.viewModel.HomeViewModel

class SearchFragment : Fragment()  ,OnCellClickListener {


    lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: HomeViewModel
    lateinit var adapter: ProductsCatAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding= FragmentSearchBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val  view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          viewModel= HomeViewModel()
          adapter= ProductsCatAdapter(this)

        viewModel.search("quo","","")

        viewModel.searchLiveData.observe(viewLifecycleOwner,{
            when(it.status){
                Status.LOADING->{
                    binding.imgLottie.visibility = View.VISIBLE
                    binding.lottie.visibility = View.VISIBLE

                }
                Status.SUCCESS->{
                    binding.imgLottie.visibility = View.GONE
                    binding.lottie.visibility = View.GONE
                    createAdapter(it.data!!.data)
                }
                Status.ERROR->{
                    showError(it.message)
                }
            }
        })

        binding.imageView10.setOnClickListener {
            viewModel.search(binding.tvCatTitle.text.toString(),"","")
        }
    }


    fun showError(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(text)
            .setContentText("ok")
            .show()
    }

    fun  createAdapter(list: List<Product>){
        binding.rvSearch.adapter=adapter
        binding.rvSearch.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        adapter.setData(list)
    }
    fun showDilog(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setContentText(text)
            .show()
    }

    override fun onProductClicked(product: Any) {
  Navigation.findNavController(requireView()).navigate(SearchFragmentDirections
      .actionSearchFragmentToDetailsFragment(product as Product))

    }



}