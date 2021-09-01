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
import com.example.ecommercekotlin.databinding.*
import com.example.ecommercekotlin.databinding.ActivityMain2Binding.inflate

class OnBoardFragment3 : Fragment() {


    var binding: FragmentOnBoard3Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    binding= FragmentOnBoard3Binding.inflate(inflater,container,false)
        val  view= binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}