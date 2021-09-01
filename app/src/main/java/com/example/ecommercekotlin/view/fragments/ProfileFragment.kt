package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.databinding.*
import com.example.ecommercekotlin.model.entity.User
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.UserViewModel

class ProfileFragment : Fragment() {


   lateinit var binding: FragmentProfileBinding

   lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding= FragmentProfileBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val  view= binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel= UserViewModel()
        binding.textView22.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_bookMarkFragment)
        }

        binding.textView23.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_orderFragment)
        }
        binding.editeImg.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_editeUserFragment)
        }
        binding.textView24.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_profileFragment_to_mapsFragment)
        }
        userViewModel.observeUserInfo()
        userViewModel.userInfoLiveData.observe(viewLifecycleOwner, Observer {

            when (it.status) {
                Status.LOADING -> {
                    binding.imgLottie.visibility = View.VISIBLE
                    binding.lottie.visibility = View.VISIBLE

                }
                Status.SUCCESS -> {
                    binding.imgLottie.visibility = View.GONE
                    binding.lottie.visibility = View.GONE
                binding.textView18.text=it.data!!.data!!.username
                binding.textView19.text=it.data!!.data!!.address
                binding.textView20.text=it.data!!.data!!.email
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