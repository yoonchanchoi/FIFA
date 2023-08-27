package com.example.view.fifa.ui.activity.main

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemBannerImageBinding

class BannerAdapter : RecyclerView.Adapter<BannerViewHolder>() {

    private lateinit var arrayImage : ArrayList<Drawable>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemBinding =
            ItemBannerImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(arrayImage[position])
    }

    override fun getItemCount(): Int {
        return arrayImage.size
    }

    fun setImage(arrayImage : ArrayList<Drawable>){
        this.arrayImage = arrayImage
    }
}