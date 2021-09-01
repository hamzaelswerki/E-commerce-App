package com.example.ecommercekotlin.view.fragments

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.adapter.ProductsCatAdapter
import com.example.ecommercekotlin.databinding.FragmentProductsCategoryBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.Product
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.CategoryViewModel

class ProductsCategoryFragment : Fragment() ,OnCellClickListener{


    lateinit var binding: FragmentProductsCategoryBinding
    lateinit var adapter:ProductsCatAdapter
   lateinit var   viewModel:CategoryViewModel
    var sortValue=""
    var sortType=""
    var catName=""
    var categoryId=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
    binding= FragmentProductsCategoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val  view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          adapter= ProductsCatAdapter(this)
        viewModel  = CategoryViewModel()


        binding.cartImg.setOnClickListener{
            Navigation.findNavController(view).navigate(com.example.ecommercekotlin.R.id.action_productsCategoryFragment_to_cartsFragment)
        }

        binding.imgSearch.setOnClickListener{
            Navigation.findNavController(it).navigate(com.example.ecommercekotlin.R.id.action_productsCategoryFragment_to_searchFragment)
        }

        if (arguments!=null){
            categoryId= ProductsCategoryFragmentArgs.fromBundle(requireArguments()).itemId
            catName= ProductsCategoryFragmentArgs.fromBundle(requireArguments()).catName
            binding.tvCatTitle.text=catName
            getData(categoryId)
        }

        createSpinnerlistSortType()
        createSpinnerlistSortValue()
        observeSearch()

    }
    fun observeSearch(){
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
    }
    fun getData(productId: Int) {
        viewModel.observeCategoryData(productId)
            viewModel.getCategoryDataa().observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.LOADING -> {
                        Log.d("ttt", "lolo")
                        binding.imgLottie.visibility = View.VISIBLE
                        binding.lottie.visibility = View.VISIBLE

                    }
                    Status.SUCCESS -> {
                        Log.d("ttt", "suc ${it.data!!.data.get(0).name}")
                        binding.imgLottie.visibility = View.GONE
                        binding.lottie.visibility = View.GONE
                        createAdapter(it.data.data)
                    }
                    Status.ERROR -> {
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

    fun  createAdapter(list: List<Product>){
        binding.rvProductsCat.adapter=adapter
        binding.rvProductsCat.layoutManager=GridLayoutManager(context, 2)
        adapter.setData(list)
    }

    override fun onProductClicked(product: Any) {
     Navigation.findNavController(requireView()).navigate(
         ProductsCategoryFragmentDirections.actionProductsCategoryFragmentToDetailsFragment(product as Product)
     )

    }
    fun createSpinnerlistSortType(){
  val listSortType= listOf<String>("Sort Type","sale_price","rate")
        val adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_spinner_dropdown_item, listSortType)

        binding.spinnetPopularity.setAdapter(adapter)

        binding.spinnetPopularity.setSelection(0)
        binding.spinnetPopularity.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                if (i != 0) {
                   sortType =  binding.spinnetPopularity.getItemAtPosition(i) as String
                    Log.d("ttt",sortType)
                    viewModel.search(catName,sortType,sortValue)
                }
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })

    }

    fun createSpinnerlistSortValue(){
        val listSortValue= listOf<String>("Sort Value","asc","desc")

        val adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_spinner_dropdown_item, listSortValue)

        binding.spinnerPrices.setAdapter(adapter)

        binding.spinnerPrices.setSelection(0)
        binding.spinnerPrices.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                if (i != 0) {
                   sortValue =  binding.spinnerPrices.getItemAtPosition(i) as String
                    viewModel.search(catName,sortType,sortValue)

                }
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {} })

    }

}