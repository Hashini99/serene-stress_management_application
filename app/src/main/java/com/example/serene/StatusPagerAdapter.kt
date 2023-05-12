package com.example.serene

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

//class StatusPagerAdapter (private val context: Context, private val images: List<Int>) : PagerAdapter() {
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val imageView = ImageView(context)
//        imageView.setImageResource(images[position])
//        container.addView(imageView)
//        return imageView
//    }
//
//    override fun isViewFromObject(view: View, obj: Any): Boolean {
//        return view == obj
//    }
//
//    override fun getCount(): Int {
//        return images.size
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
//        container.removeView(obj as View)
//    }
//}