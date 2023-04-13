package com.example.view.fifa.ui.activity.main

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemBannerImageBinding


class BannerViewHolder(private val binding: ItemBannerImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(imageDrawable : Drawable){
        binding.ivBannerItem.setImageDrawable(imageDrawable)
    }
}