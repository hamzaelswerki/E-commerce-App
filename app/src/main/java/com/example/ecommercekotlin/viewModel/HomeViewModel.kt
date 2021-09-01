package com.example.ecommercekotlin.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercekotlin.model.entity.HomeStatus
import com.example.ecommercekotlin.model.entity.SearchResponse
import com.example.ecommercekotlin.model.network.ApiInterface
import com.example.ecommercekotlin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {
    var homeScreenLiveData = MutableLiveData<Resource<HomeStatus>>()

    var searchLiveData=MutableLiveData<Resource<SearchResponse>>()


    fun obserHomeData(){
        viewModelScope.launch {
            homeScreenLiveData.postValue(Resource.loading(null))

            try {
                val data=        ApiInterface.getClient().getHomeData()
                homeScreenLiveData.postValue(Resource.success(data))
            }catch (e:Exception){
                homeScreenLiveData.postValue(Resource.error("something error",null))

            }

        }

    }
    fun getHomeData(): MutableLiveData<Resource<HomeStatus>> {
        return homeScreenLiveData
    }

    fun search(word:String,sortType:String,sortvalue: String){
        viewModelScope.launch {
            searchLiveData.postValue(Resource.loading(null))
            try {
                val data=ApiInterface.getClient().search(word,sortType,sortvalue)
                searchLiveData.postValue(Resource.success(data))
            }   catch (e:Exception){
                searchLiveData.postValue(Resource.error("SOmthingError",null))

            }
        }

    }

}