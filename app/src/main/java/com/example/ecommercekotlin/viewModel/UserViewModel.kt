package com.example.ecommercekotlin.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercekotlin.model.entity.UserStatus
import com.example.ecommercekotlin.model.network.ApiInterface
import com.example.ecommercekotlin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel() : ViewModel() {
    var userLiveData = MutableLiveData<Resource<UserStatus>>()
    var userInfoLiveData = MutableLiveData<Resource<UserStatus>>()
    var userUpdatedInfoData = MutableLiveData<Resource<UserStatus>>()


    fun registerUser(username:String,email:String,password:String,phone_number:String,address:String) {

viewModelScope.launch {
    userLiveData.postValue(Resource.loading(null))

 try {
    val data= ApiInterface.getClient().register(username, email, password,password, phone_number, address)
     userLiveData.postValue(Resource.success(data))
 }   catch (e:Exception){
     userLiveData.postValue(Resource.error("something error",null))
 }
}
    }

    fun loginUser(email:String,password:String) {

        viewModelScope.launch {
            userLiveData.postValue(Resource.loading(null))
            try {
             val data=   ApiInterface.getClient().login(
                    email,
                    password)
                userLiveData.postValue(Resource.success(data))

            }   catch (e:Exception){
                userLiveData.postValue(Resource.error("something error",null))

            }
        }

    }

    fun getUserLiveData():LiveData<Resource<UserStatus>>{
        return userLiveData
    }

    fun getUserInfor()=userInfoLiveData



    fun observeUserInfo(){
        viewModelScope.launch {
            userInfoLiveData.postValue(Resource.loading(null))
        try {

            val data=                   ApiInterface.getClient().getUserInfo()
            userInfoLiveData.postValue(Resource.success(data))
        }catch (e:Exception){
            userInfoLiveData.postValue(Resource.error("something error",null))

        }

        }

    }



    fun updateUser(username: String ,phone_number: String,address: String){
        viewModelScope.launch {
            userUpdatedInfoData.postValue(Resource.loading(null))
            try {
                val data=ApiInterface.getClient().updateUser(username,phone_number,address)
                userUpdatedInfoData.postValue(Resource.success(data))
            }   catch (e:Exception){
                userUpdatedInfoData.postValue(Resource.error("something error",null))

            }
        }




    }

}