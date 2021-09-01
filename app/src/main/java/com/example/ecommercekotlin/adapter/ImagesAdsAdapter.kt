package com.example.ecommercekotlin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ecommercekotlin.R
import com.example.ecommercekotlin.model.entity.Ad
import java.util.*

class ImagesAdsAdapter(var context: Context?, var adsList: List<Ad>) : PagerAdapter() {


  override  fun getCount(): Int {
        return adsList!!.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_ads_row, null)
         val img = view.findViewById<ImageView>(R.id.img_pager)


        //  img.setImageURI(adsList[position].image)
       // Glide.with(img).load("https://firebasestorage.googleapis.com/v0/b/sweet-app-2b6e7.appspot.com/o/Categories%20Images%2Fgreeew1.png?alt=media&token=98abefc9-538f-4404-9694-062ec285bd57").into(img)
        Glide.with(img).load("https://via.placeholder.com/640x480.png")
            .override(1600,1600).into(img)

        container.addView(view)
        return view

    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` === view
    }


}
