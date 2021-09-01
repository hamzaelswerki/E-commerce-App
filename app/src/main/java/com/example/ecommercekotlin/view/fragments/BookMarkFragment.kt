package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.adapter.BookMarkAdapter
import com.example.ecommercekotlin.adapter.ProductsCatAdapter
import com.example.ecommercekotlin.databinding.FragmentBookmarkBinding
import com.example.ecommercekotlin.databinding.FragmentProductsCategoryBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.BookMark
import com.example.ecommercekotlin.model.entity.Product
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.BookMarkViewModel
import com.example.ecommercekotlin.viewModel.CategoryViewModel

class BookMarkFragment : Fragment() ,OnCellClickListener{


    lateinit var binding: FragmentBookmarkBinding
    lateinit var viewModel: BookMarkViewModel
    lateinit var adapter: BookMarkAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding= FragmentBookmarkBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val  view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          viewModel= BookMarkViewModel()
          adapter= BookMarkAdapter(this)

        viewModel.bookMarkObserve()

        viewModel.getBookMark().observe(viewLifecycleOwner,{
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

        viewModel.getDeleteBookMarkData().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING->{
                    binding.imgLottie.visibility = View.VISIBLE
                    binding.lottie.visibility = View.VISIBLE

                }
                Status.SUCCESS->{
                    binding.imgLottie.visibility = View.GONE
                    binding.lottie.visibility = View.GONE
                    viewModel.bookMarkObserve()
                    showDilog(it.message)
                }
                Status.ERROR->{
                    showError(it.message)
                }
            }
        })
    }


    fun showError(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(text)
            .setContentText("ok")
            .show()
    }

    fun  createAdapter(list: List<BookMark>){
        binding.tvBookMark.adapter=adapter
        binding.tvBookMark.layoutManager=GridLayoutManager(context,2)
        adapter.setData(list)
    }
    fun showDilog(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setContentText(text)
            .show()
    }
    override fun onProductClicked(any: Any) {
        val product=any as Product
        viewModel.deleteBookMark(product.id)
    }



}