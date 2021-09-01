package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.adapter.CatAdapter
import com.example.ecommercekotlin.adapter.HomeProductAdapter
import com.example.ecommercekotlin.adapter.ImagesAdsAdapter
import com.example.ecommercekotlin.databinding.FragmentHomeBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.Ad
import com.example.ecommercekotlin.model.entity.Category
import com.example.ecommercekotlin.model.entity.Product
import com.example.ecommercekotlin.utils.SharedPrefHelper
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.HomeViewModel

class HomeFragment : Fragment() ,OnCellClickListener {



   lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
    binding= FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val  view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.imgCart.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_cartsFragment)
        }
        binding.imgPofile.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.imgSearch.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }


    fun getData(){

        val  homeViewModel= HomeViewModel()
        homeViewModel.obserHomeData()
        homeViewModel.getHomeData().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.imgLottie.visibility = View.VISIBLE
                    binding.lottie.visibility = View.VISIBLE

                }
                Status.SUCCESS -> {
                    binding.imgLottie.visibility = View.GONE
                    binding.lottie.visibility = View.GONE
                    createAdsImages(it.data?.data?.ads!!)
                    createDailyDeals(it.data.data?.dailyDeals!!)
                    createRecentlyAdded(it.data.data?.recentlyadded!!)
                    createTrending(it.data.data?.trending!!)
                    createCategoryAdapter(it.data.data?.categories!!)
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
    fun createAdsImages(list: List<Ad>){
        val adsAdapter =ImagesAdsAdapter(context, list)
        binding.viewPagerAds.adapter=adsAdapter
        binding.springDotsIndicator.setViewPager(binding.viewPagerAds)
    }
    fun  createDailyDeals(list: List<Product>){
        val adapter =HomeProductAdapter(list, this)
        binding.rvdeals.adapter=adapter
        binding.rvdeals.layoutManager=getLinear()
    }
    fun  createRecentlyAdded(list: List<Product>){
        val adapter=HomeProductAdapter(list, this)
        binding.recentRv.adapter=adapter
        binding.recentRv.layoutManager=getLinear()
    }fun  createTrending(list: List<Product>){
        val adapter=HomeProductAdapter(list, this)
        binding.rvTrending.adapter=adapter
        binding.rvTrending.layoutManager=getLinear()
    }

    fun  createCategoryAdapter(list: List<Category>){
        val adapter=CatAdapter(list, context,object :CatAdapter.OnCatClickListener{
            override fun onClicked(productId: Int,catTitle:String) {
                Navigation.findNavController(requireView())
                    .navigate(HomeFragmentDirections.actionHomeFragmentToProductsCategoryFragment(productId,catTitle))
            }
        })
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=getLinear()
    }

    fun getLinear()=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


    override fun onProductClicked(product: Any) {
        Navigation.findNavController(requireView()).navigate(HomeFragmentDirections.
        actionHomeFragmentToDetailsFragment(product as Product))

    }


}