package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.databinding.FragmentLogin1Binding
import com.example.ecommercekotlin.model.entity.User
import com.example.ecommercekotlin.utils.SharedPrefHelper
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.UserViewModel

class Login1Fragment : Fragment() {


   lateinit var binding: FragmentLogin1Binding
  // var  binding : FragmentLogin1Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

    binding= FragmentLogin1Binding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val  view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mystring = resources.getString(R.string.ForgetPassword)
        val content = SpannableString(mystring)
        content.setSpan(UnderlineSpan(), 0, mystring.length, 0)
        binding.forgetPss.setText(content)
        val rigesterText = resources.getString(R.string.REGISTER)
        val spnRegister = SpannableString(rigesterText)
        spnRegister.setSpan(UnderlineSpan(), 0, rigesterText.length, 0)
        binding.regester.setText(spnRegister)
        binding.regester.setOnClickListener(View.OnClickListener {
                v ->
            Navigation.findNavController(requireView()).navigate(R.id.action_login1Fragment_to_registerFragment2)

        })
        binding.button.setOnClickListener(View.OnClickListener {
               if (checkFields()){
                 loginUser(binding.edUserName1.text.toString(),binding.etPassword.text.toString())
                  }
        })

    }

    fun checkFields():Boolean{

        if (TextUtils.isEmpty(binding?.edUserName1?.text)||
            TextUtils.isEmpty(binding?.etPassword?.text)){
            return  false
        }
        return true
    }


    fun loginUser(email:String,passWord:String){
        val  userViewModel= UserViewModel()
        userViewModel.loginUser(email,passWord)
        userViewModel.getUserLiveData().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                        Status.LOADING -> {
                            binding.progressBar.visibility=View.VISIBLE
                }
                Status.SUCCESS -> {
                    if (it.data?.status!!){
                        binding.progressBar.visibility=View.VISIBLE
                        SharedPrefHelper.setToken(requireContext(), it.data?.data?.token)
                        Log.d("ttt",SharedPrefHelper.getToken(requireContext()).toString())

                        Navigation.findNavController(requireView() ).navigate(R.id.action_login1Fragment_to_homeFragment2)
                    }else{
                        showError(it.message)
                    }

                }
                Status.ERROR -> {
                    showError(it.message)
                }
            }

        })

    }

    fun showError(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(text)
            .setContentText("ok")
            .show()
    }

}