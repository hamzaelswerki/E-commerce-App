package com.example.ecommercekotlin.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercekotlin.model.entity.BookMarkStatus
import com.example.ecommercekotlin.model.entity.CartResponse
import com.example.ecommercekotlin.model.entity.CartStatus
import com.example.ecommercekotlin.model.entity.OrderStatus
import com.example.ecommercekotlin.model.network.ApiInterface
import com.example.ecommercekotlin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersViewModel:ViewModel() {

    val allOrders=MutableLiveData<Resource<OrderStatus>>()


    fun allOrderObserve(){
        viewModelScope.launch {
            allOrders.postValue(Resource.loading(null))
            try {
          val data=      ApiInterface.getClient().getOrders()
                allOrders.postValue(Resource.success(data))

            }   catch (e:Exception){
                allOrders.postValue(Resource.error("Something Error",null))

            }
        }
    }



}