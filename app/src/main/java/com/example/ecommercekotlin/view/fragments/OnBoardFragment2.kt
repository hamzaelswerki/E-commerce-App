package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.databinding.ActivityMain2Binding.inflate
import com.example.ecommercekotlin.databinding.FragmentLogin1Binding
import com.example.ecommercekotlin.databinding.FragmentOnBoard1Binding
import com.example.ecommercekotlin.databinding.FragmentOnBoard2Binding
import com.example.ecommercekotlin.databinding.FragmentOnBoardingBinding

class OnBoardFragment2 : Fragment() {


    var binding: FragmentOnBoard2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    binding= FragmentOnBoard2Binding.inflate(inflater,container,false)
        val  view= binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}