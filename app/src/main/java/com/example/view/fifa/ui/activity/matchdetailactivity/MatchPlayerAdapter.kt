package com.example.view.fifa.ui.activity.matchdetailactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemMatchPlayerBinding
import com.example.view.fifa.network.models.dto.MatchPlayerDTO

class MatchPlayerAdapter(private val matchPlayerDTOList: ArrayList<MatchPlayerDTO>) : RecyclerView.Adapter<MatchPlayerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPlayerViewHolder {
        val itemBinding =
            ItemMatchPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchPlayerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MatchPlayerViewHolder, position: Int) {
        holder.bind(matchPlayerDTOList[position])
    }

    override fun getItemCount(): Int {
        return matchPlayerDTOList.size
    }
}