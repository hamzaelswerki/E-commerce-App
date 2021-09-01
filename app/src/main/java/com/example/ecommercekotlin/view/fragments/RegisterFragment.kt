package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.databinding.FragmentRegisterBinding
import com.example.ecommercekotlin.model.entity.User
import com.example.ecommercekotlin.utils.SharedPrefHelper
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.UserViewModel

class RegisterFragment : Fragment() {


  lateinit  var binding:FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding=  FragmentRegisterBinding.inflate(inflater, container, false)
      return  binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.signUp?.setOnClickListener(View.OnClickListener { v ->

               if (checkFields()) {


                   val  userViewModel=UserViewModel()

                  userViewModel.registerUser(
                      binding.edUserName.text.toString(),
                       binding.edMail.text.toString()  ,
                       binding.edPssword.text.toString(),
                      binding.edPhone.text.toString(),
                      binding.edAddress.text.toString() )
                   userViewModel.getUserLiveData().observe(viewLifecycleOwner, Observer {
                       when (it.status) {
                           Status.LOADING -> {
                               binding.progressBar.setVisibility(View.VISIBLE)
                           }
                           Status.SUCCESS -> {

                               Log.d("ttt",it.data?.data?.token.toString());
                               SharedPrefHelper.setToken(requireContext(), it.data?.data?.token)
                               findNavController(requireView()).navigate(R.id.action_registerFragment2_to_onBoardingFragment)

                           }
                           Status.ERROR -> {
                               showError(it.message)
                           }
                       }

                   })
            }

        })

    }

    fun showError(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(text)
            .setContentText("ok")
            .show()
    }

    fun checkFields():Boolean{
        if (TextUtils.isEmpty(binding?.edUserName?.text)||
            TextUtils.isEmpty(binding?.edPssword?.text)||
            TextUtils.isEmpty(binding?.edPhone?.text)||
            TextUtils.isEmpty(binding?.edMail?.text)||
            TextUtils.isEmpty(binding?.edAddress?.text)){
            return false
        }
        return true
    }

}


