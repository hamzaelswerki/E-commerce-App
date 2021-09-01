package com.example.ecommercekotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercekotlin.model.entity.CategoryStatus
import com.example.ecommercekotlin.model.entity.HomeStatus
import com.example.ecommercekotlin.model.entity.SearchResponse
import com.example.ecommercekotlin.model.network.ApiInterface
import com.example.ecommercekotlin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel : ViewModel() {
    var categoryData= MutableLiveData<Resource<CategoryStatus>>()
    var searchLiveData=MutableLiveData<Resource<SearchResponse>>()

    fun getCategoryDataa()= categoryData

    fun  observeCategoryData(catId:Int){

        viewModelScope.launch {
            categoryData.postValue(Resource.loading(null))
            try {
              val data=  ApiInterface.getClient().getProductsForSingleCat(catId)

                categoryData.postValue(Resource.success(data))
            }catch (e:Exception){
                categoryData.postValue(Resource.error("something Error",null))

            }
        }

    }

    fun search(word:String,sortType:String,sortvalue: String){
        viewModelScope.launch {
            searchLiveData.postValue(Resource.loading(null))
            try {
                val data= ApiInterface.getClient().search(word,sortType,sortvalue)
                searchLiveData.postValue(Resource.success(data))

            }catch (e:Exception){
                searchLiveData.postValue(Resource.error("SOmthingError",null))

            }
        }

    }
}