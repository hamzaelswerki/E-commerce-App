package com.example.ecommercekotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.adapter.FragmentPagerAdapterOnBoard
import com.example.ecommercekotlin.databinding.FragmentOnBoardingBinding
import java.util.*

class OnBoardingFragment : Fragment() {


    var binding: FragmentOnBoardingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    binding= FragmentOnBoardingBinding.inflate(inflater, container, false)
        val  view= binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
createViewPager()
    }

    fun createViewPager() {
        val fragmentList = ArrayList(
            Arrays.asList(OnBoardFragment1(), OnBoardFragment2(), OnBoardFragment3()))

        val adapter = FragmentPagerAdapterOnBoard(activity as AppCompatActivity, fragmentList)
        binding?.viewPager?.adapter = adapter
        binding!!.springDotsIndicator.setViewPager2(binding!!.viewPager)
        binding!!.next.setOnClickListener {
            binding!!.viewPager.currentItem = binding!!.viewPager.currentItem + 1
        }
        binding!!.skip.setOnClickListener {
            v ->
            Navigation.findNavController(v).navigate(R.id.action_onBoardingFragment_to_homeFragment)
        }
    }

}