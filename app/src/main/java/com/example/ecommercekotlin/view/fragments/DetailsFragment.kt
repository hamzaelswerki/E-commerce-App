package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.adapter.ColorAdapter
import com.example.ecommercekotlin.adapter.ImagesProductAdapter
import com.example.ecommercekotlin.adapter.SizeAdapter
import com.example.ecommercekotlin.databinding.FragmentDetailsBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.model.entity.Color
import com.example.ecommercekotlin.model.entity.ProductImage
import com.example.ecommercekotlin.model.entity.Size
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.BookMarkViewModel
import com.example.ecommercekotlin.viewModel.CartViewModel

class DetailsFragment : Fragment() {


 lateinit  var binding: FragmentDetailsBinding
var colorId:Int = 25
var sizeId:Int = 61
    lateinit var pDialog: SweetAlertDialog
    lateinit var pDialogSucess: SweetAlertDialog
    lateinit var bookMarkViewModel: BookMarkViewModel
    lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
    binding= FragmentDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val  view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         pDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)

        binding.cartImg.setOnClickListener{
            Navigation.findNavController(view).navigate(com.example.ecommercekotlin.R.id.action_detailsFragment_to_cartsFragment)
        }

        bookMarkViewModel= BookMarkViewModel()
        cartViewModel=CartViewModel()

        cartViewModel.addProductData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING->{
                    showProg(true)
                }
                Status.SUCCESS->{
                    showProg(false)
                    showDilog(it.data!!.message,true)
                }
                Status.ERROR->{
                    showError(it.message)
                }
            }
        })
        bookMarkViewModel.addBookMarkData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING->{
                    showProg(true)
                }
                Status.SUCCESS->{
                    showProg(false)
                    showDilog(it.data!!.message,true)
                }
                Status.ERROR->{
                    showError(it.message)
                }
            }
        })
        if (arguments!=null){
        val product=    DetailsFragmentArgs.fromBundle(requireArguments()).product
            binding.textView12.text=product.name
            binding.textView13.text=product.price.toString()+"$"
            binding.textView14.text=product.description
            binding.ratingBar.rating= product.rate.toFloat()
            createAdsImages(product.product_images)
            createSizeAdapter(product.sizes)
            createColorAdapter(product.colors)
            binding.button4.setOnClickListener {
                cartViewModel.addProductToMyCart(product.id,binding.tvNum.text.toString().toInt(), colorId, sizeId
                )

            }
            binding.button3.setOnClickListener {
                addProductToBookMark(product.id)
            }


            binding.ratingBar.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener {
                override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
                    cartViewModel.rateProduct(product.id,p1.toInt())
                }
            })

        }


        binding.plus.setOnClickListener {
         var num=   binding.tvNum.text.toString().toInt()
            num+=1
            binding.tvNum.text=num.toString()
        }
        binding.minus.setOnClickListener {
         var num=   binding.tvNum.text.toString().toInt()

            if (num>1)
                num-=1
            binding.tvNum.text=num.toString()
        }






    }

    private fun addProductToBookMark(productId: Int) {
        bookMarkViewModel.addProductToBookMark(productId)
    }


    fun createAdsImages(list: List<ProductImage>){
        val adsAdapter = ImagesProductAdapter(context, list)
        binding.viewPagerAds.adapter=adsAdapter
        binding.springDotsIndicator.setViewPager(binding.viewPagerAds)
    }

    fun createSizeAdapter(list: List<Size>){
        val sizeAdapter=SizeAdapter(list, object : OnCellClickListener {
            override fun onProductClicked(any: Any) {
                var size = any as Size
                sizeId = size.id

            }

        })
        binding.rvSize.adapter=sizeAdapter
        binding.rvSize.layoutManager=LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }
    fun createColorAdapter(list: List<Color>){
        val colorAdapter=ColorAdapter(list, object : OnCellClickListener {
            override fun onProductClicked(any: Any) {
                var color = any as Color
                colorId = color.id
                Log.d("ttt", color.id.toString())
            }

        })
        binding.rvColor.adapter=colorAdapter
        binding.rvColor.layoutManager=LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    fun showProg(isShow: Boolean) {
        pDialog.getProgressHelper().setBarColor(android.graphics.Color.parseColor("#A5DC86"))
        pDialog.setTitleText("Loading")
        pDialog.setCancelable(false)
        if (isShow) {
            pDialog.show()
        } else {
            pDialog.hide()
        }
    }

    fun showDilog(text: String?,isShow: Boolean) {
        pDialogSucess = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
        pDialogSucess.setContentText(text)
        if (isShow) {
            pDialogSucess.show()
        } else {
            pDialogSucess.hide()
        }
    }

    fun showError(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(text)
            .setContentText("ok")
            .show()
    }
}