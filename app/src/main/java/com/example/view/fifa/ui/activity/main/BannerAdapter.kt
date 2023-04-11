package com.example.view.fifa.ui.activity.main

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemBannerImageBinding

class BannerAdapter : RecyclerView.Adapter<BannerViewHolder>() {
    private lateinit var bitmaps: ArrayList<Bitmap>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemBinding =
            ItemBannerImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return bitmaps.size
    }
}