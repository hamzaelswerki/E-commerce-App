package com.example.ecommercekotlin.utils

import android.content.Context

class SharedPrefHelper {


    companion object{


    fun getToken(context: Context): String? {
        val sharedpreferences =
            context.getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE)
        return sharedpreferences.getString("token", "null")
    }

    fun setToken(context: Context, token: String?) {
        val sharedpreferences =
            context.getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedpreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun setIsFirst(context: Context) {
        val sharedpreferences =
            context.getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedpreferences.edit()
        editor.putBoolean(Constants.IS_FIRST, false)
        editor.apply()
    }

    fun getIsFirst(context: Context): Boolean {
        return context.getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE)
            .getBoolean(Constants.IS_FIRST, true)
    }
    }

}