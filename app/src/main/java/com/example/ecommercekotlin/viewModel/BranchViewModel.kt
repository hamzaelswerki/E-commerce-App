package com.example.ecommercekotlin.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercekotlin.model.entity.HomeStatus
import com.example.ecommercekotlin.model.entity.SearchResponse
import com.example.ecommercekotlin.model.network.ApiInterface
import com.example.ecommercekotlin.utils.BranchStatus
import com.example.ecommercekotlin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BranchViewModel() : ViewModel() {
    var branchesLiveData = MutableLiveData<Resource<BranchStatus>>()
    var nearestBranches = MutableLiveData<Resource<BranchStatus>>()



    fun observeBranshes(){
        viewModelScope.launch {
            branchesLiveData.postValue(Resource.loading(null))
            try {
                val data=        ApiInterface.getClient().getBranches()
                branchesLiveData.postValue(Resource.success(data))

            }catch (e:Exception){
                branchesLiveData.postValue(Resource.error("something error",null))

            }
        }
    }

    fun observeNearestBranshes(lat:Double,long:Double,raduis:Int){
        viewModelScope.launch {
            nearestBranches.postValue(Resource.loading(null))
            try {
                val data=ApiInterface.getClient().getNearestBranches(lat,long,raduis)
                nearestBranches.postValue(Resource.success(data))

            }catch (e:Exception){
                nearestBranches.postValue(Resource.error("something error",null))

            }
        }


    }

}