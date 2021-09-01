package com.example.ecommercekotlin.view.fragments

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.android.talabaty.util.LocationManager
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.adapter.BranchesAdapter
import com.example.ecommercekotlin.databinding.FragmentMapsBinding
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.utils.Branch
import com.example.ecommercekotlin.utils.Status
import com.example.ecommercekotlin.viewModel.BranchViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.mahmouddev.appweather.location.LocationHelper

class MapsFragment : Fragment() ,OnCellClickListener{
    lateinit var binding: FragmentMapsBinding

    private var locationManager : LocationManager? = null

    var list= mutableListOf<Marker>()
    var branchViewModel= BranchViewModel()
    lateinit var map:GoogleMap


    private val callback = OnMapReadyCallback { googleMap ->
            map=googleMap

        val gaza = LatLng(31.416665, 34.333332)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gaza, 11f), 3000, null)
        createLocationHelper()
    }

    fun createLocationHelper(){
       val locationHelper= LocationHelper(requireActivity(), object : LocationManager {
            override fun onLocationChanged(location: Location?) {
            }

            override fun getLastKnownLocation(location: Location?) {
                createMyMarker(location!!)
                getNearesBranches(location)
            }
        })
        if (locationHelper.checkLocationPermissions()) {
            if (locationHelper.checkMapServices()) {
                locationHelper.startLocationUpdates()
            }
        }

    }

   fun createMyMarker(location: Location){
       val latLng = LatLng(location.latitude , location.longitude )
       val myMarker=MarkerOptions().position(latLng).title("Its Me")
           .icon(bitmapDescriptorFromVector(requireContext(),R.drawable.ic_my_location))
       map.addMarker(myMarker)
       map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11f), 3000, null)


   }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding= FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        branchViewModel.observeBranshes()
        branchViewModel.branchesLiveData.observe((viewLifecycleOwner), Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.imgLottie.visibility = View.VISIBLE
                    binding.lottie.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.imgLottie.visibility = View.GONE
                    binding.lottie.visibility = View.GONE
                    createAdapter(it.data!!.data)
                }
                Status.ERROR -> {
                    showError(it.message)
                }
            }
        })

    }

    private fun createAdapter(data: List<Branch>) {
            val adaper=BranchesAdapter(data, this)
                binding.rvBranches.adapter=adaper
        binding.rvBranches.layoutManager= LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    override fun onProductClicked(any: Any) {
        val bransh=any as Branch
        val latLng = LatLng(bransh.latitude.toDouble(), bransh.longitude.toDouble())
        val marker = MarkerOptions().position(latLng).title(bransh.name)
            .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_maps_blue))
       if (!list.isEmpty()){
           list.get(0).remove()
       }
      val  marker1 = map.addMarker(marker)
        list.add(0, marker1)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11f), 2000, null)
    }

    fun showError(text: String?) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText(text)
            .setContentText("ok")
            .show()
    }

    fun  getNearesBranches(location: Location){
        branchViewModel.observeNearestBranshes(location.latitude, location.longitude, 400000)
        branchViewModel.nearestBranches.observe((viewLifecycleOwner), Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.imgLottie.visibility = View.VISIBLE
                    binding.lottie.visibility = View.VISIBLE

                }
                Status.SUCCESS -> {
                    binding.imgLottie.visibility = View.GONE
                    binding.lottie.visibility = View.GONE
                    createMarkers(it.data!!.data)
                }
                Status.ERROR -> {
                    showError(it.message)
                }
            }
        })
    }
 fun  createMarkers(branches: List<Branch>){
     for (branch in branches) {
         val latLng = LatLng(branch.latitude.toDouble(), branch.longitude.toDouble())
         val marker = MarkerOptions().position(latLng).title(branch.name)
             .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_maps_loc))
         val marker1: Marker = map.addMarker(marker)
        // marker1.setTag(branches.get(i))
     }

 }
    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}