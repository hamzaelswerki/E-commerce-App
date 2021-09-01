package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.utils.SharedPrefHelper

class SplashFragment : Fragment() {
    private val SPLASH_DISPLAY_LENGTH = 650


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    openActivity()
    }

    fun openActivity() {
        Handler().postDelayed({

            if (SharedPrefHelper.getToken(context!!)?.equals("null")!!) {
                Log.d("ttt","if")
                findNavController(view!!).navigate(R.id.action_splashFragment_to_login1Fragment)
            }else{
                Log.d("ttt","else")
                findNavController(view!!).navigate(R.id.action_splashFragment_to_homeFragment)
            }

        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

}