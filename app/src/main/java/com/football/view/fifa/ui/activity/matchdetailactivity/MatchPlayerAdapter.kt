package com.football.view.fifa.ui.activity.matchdetailactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.football.view.fifa.databinding.ItemMatchPlayerBinding
import com.football.view.fifa.network.models.dto.MatchPlayerResult


class MatchPlayerAdapter(private val context: Context ,private val matchPlayerDTOList: ArrayList<MatchPlayerResult>) : RecyclerView.Adapter<MatchPlayerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPlayerViewHolder {
        val itemBinding =
            ItemMatchPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchPlayerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MatchPlayerViewHolder, position: Int) {
        holder.bind(context, matchPlayerDTOList[position])
    }

    override fun getItemCount(): Int {
        return matchPlayerDTOList.size
    }
}
