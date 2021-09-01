package com.example.ecommercekotlin.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercekotlin.model.entity.CartResponse
import com.example.ecommercekotlin.model.entity.CartStatus
import com.example.ecommercekotlin.model.entity.RateResponse
import com.example.ecommercekotlin.model.network.ApiInterface
import com.example.ecommercekotlin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class CartViewModel : ViewModel() {
    val addProductData = MutableLiveData<Resource<CartStatus>>()
    val cartsData = MutableLiveData<Resource<CartResponse>>()
    val deleteCartData = MutableLiveData<Resource<CartResponse>>()
    val updateCartsData = MutableLiveData<Resource<CartResponse>>()
    val confirmCartsData = MutableLiveData<Resource<CartResponse>>()
    val rateData = MutableLiveData<Resource<RateResponse>>()

    fun addProductToMyCart(productId: Int, quantity: Int, colorId: Int, sizeId: Int) {
        viewModelScope.launch {
            addProductData.postValue(Resource.loading(null))

            try {

                val data =
                    ApiInterface.getClient().addProductToCart(productId, quantity, colorId, sizeId)
                addProductData.postValue(Resource.success(data))
            } catch (e: Exception) {
                addProductData.postValue(Resource.error("Something Error", null))

            }
        }

    }


    fun cartsDataObserve() {
        viewModelScope.launch {
            cartsData.postValue(Resource.loading(null))
            try {
                val data = ApiInterface.getClient().showCartsForUser()
                cartsData.postValue(Resource.success(data))

            } catch (e: Exception) {
                cartsData.postValue(Resource.error("Something Error", null))

            }
        }

    }


    fun deleteDataObserve(id: Int) {
        viewModelScope.launch {
            deleteCartData.postValue(Resource.loading(null))
            try {
                val  data= ApiInterface.getClient().deleteCart(id)
                deleteCartData.postValue(Resource.success(data))

            }catch (e:Exception){
                deleteCartData.postValue(Resource.error("Something Error", null))

            }
        }

    }

    fun updateCarts(id: Int, quantity: Int) {
      viewModelScope.launch {
          updateCartsData.postValue(Resource.loading(null))
          try {
              val data=ApiInterface.getClient().updateCart(id, quantity)
              updateCartsData.postValue(Resource.success(data))

          }catch (e:Exception){
              updateCartsData.postValue(Resource.error("Somthing error", null))

          }
      }


    }


    fun confirmCarts(): MutableLiveData<Resource<CartResponse>> {
      viewModelScope.launch {
          confirmCartsData.postValue(Resource.loading(null))
try {
    val data= ApiInterface.getClient().confirmCarts("test")
    confirmCartsData.postValue(Resource.success(data))

}   catch (e:Exception){
    confirmCartsData.postValue(Resource.error("SOmething Error", null))

}
      }
        return confirmCartsData
    }

    fun rateProduct(id: Int, rate: Int) {
        viewModelScope.launch {
            rateData.postValue(Resource.loading(null))
            try {
               val data= ApiInterface.getClient().rateProduct(id, rate)
                rateData.postValue(Resource.success(data))
            }catch (e:Exception){
                rateData.postValue(Resource.error("Something Error ya bitch", null))

            }
        }

    }

}