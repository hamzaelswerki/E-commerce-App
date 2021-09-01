package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.databinding.FragmentEditeUserBinding
import com.example.ecommercekotlin.databinding.FragmentLogin1Binding
import com.example.ecommercekotlin.utils.SharedPrefHelper
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.UserViewModel

class EditeUserFragment : Fragment() {


   lateinit var binding: FragmentEditeUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

    binding= FragmentEditeUserBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val  view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUp.setOnClickListener(View.OnClickListener {
                v ->
         if (checkFields()){
                updateUser(binding.edUserName.text.toString(),binding.edMail.text.toString()
                    ,binding.edAddress.text.toString())
            }

        })



    }

    fun checkFields():Boolean{

        if (TextUtils.isEmpty(binding?.edUserName?.text)||
            TextUtils.isEmpty(binding?.edAddress?.text)){
            return  false
        }
        return true
    }


    fun updateUser(userName:String, phone:String, address:String){
        val  userViewModel= UserViewModel()

        userViewModel.updateUser(userName,phone,address)
        userViewModel.userUpdatedInfoData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                        Status.LOADING -> {
                            Log.d("ttt","lolo")
                            binding.progressBar.visibility=View.VISIBLE
                }
                Status.SUCCESS -> {
                    Log.d("ttt",it.toString())
                   Navigation.findNavController(requireView() ).navigate(R.id.action_editeUserFragment_to_profileFragment)
                }
                Status.ERROR -> {
                    binding.progressBar.visibility=View.GONE

                    showError(it.message)
                }
            }

        })

    }

    fun showError(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(text)
            .setContentText("$text")
            .show()
    }

}