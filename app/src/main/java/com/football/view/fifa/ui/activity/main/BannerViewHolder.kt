package com.football.view.fifa.ui.activity.main

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.football.view.fifa.databinding.ItemBannerImageBinding

class BannerViewHolder(private val binding: ItemBannerImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(imageDrawable : Drawable){
        binding.ivBannerItem.setImageDrawable(imageDrawable)
    }
}