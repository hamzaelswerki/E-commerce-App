package com.example.ecommercekotlin.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercekotlin.model.entity.BookMarkStatus
import com.example.ecommercekotlin.model.entity.CartResponse
import com.example.ecommercekotlin.model.entity.CartStatus
import com.example.ecommercekotlin.model.network.ApiInterface
import com.example.ecommercekotlin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookMarkViewModel : ViewModel() {

    val addBookMarkData = MutableLiveData<Resource<CartStatus>>()
    val allBookMark = MutableLiveData<Resource<BookMarkStatus>>()
    val deleteBookMark = MutableLiveData<Resource<CartStatus>>()

    fun addProductToBookMark(productId: Int) {
        addBookMarkData.postValue(Resource.loading(null))
        ApiInterface.getClient().addProductToBookMark(productId).enqueue(object :
            Callback<CartStatus> {
            override fun onResponse(call: Call<CartStatus>, response: Response<CartStatus>) {
                addBookMarkData.postValue(Resource.success(response.body()))
            }

            override fun onFailure(call: Call<CartStatus>, t: Throwable) {
                addBookMarkData.postValue(Resource.error("Something Eror", null))
                Log.d("ttt", t.message.toString());
            }

        })
    }

    fun getAddBokMarkDataa() = addBookMarkData


    fun bookMarkObserve() {

        viewModelScope.launch {
            allBookMark.postValue(Resource.loading(null))

            try {
                val data = ApiInterface.getClient().getBookMark();

                allBookMark.postValue(Resource.success(data))
            } catch (e: Exception) {
                allBookMark.postValue(e.message?.let { Resource.error(it, null) })

            }

        }

    }

    fun getBookMark() = allBookMark


    fun deleteBookMark(productId: Int) {
        viewModelScope.launch {
            deleteBookMark.postValue(Resource.loading(null))
            val data= ApiInterface.getClient().deleteBookMark(productId)
            try {
                deleteBookMark.postValue(Resource.success(data))
            }catch (e:Exception){
                deleteBookMark.postValue(Resource.error("Something Eror", null))
            }
        }


    }

    fun getDeleteBookMarkData() = deleteBookMark

}