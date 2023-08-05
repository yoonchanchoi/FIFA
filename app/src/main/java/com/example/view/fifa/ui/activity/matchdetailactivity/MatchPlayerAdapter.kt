package com.example.view.fifa.ui.activity.matchdetailactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemUserRecordBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MatchPlayDTO
import com.example.view.fifa.network.models.dto.UserDTO

class MatchPlayerAdapter(private val matchPlayerDTOList: ArrayList<MatchPlayDTO>) : RecyclerView.Adapter<MatchPlayerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPlayerViewHolder {
        val itemBinding =
            ItemUserRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchPlayerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MatchPlayerViewHolder, position: Int) {
        holder.bind(matchPlayerDTOList[position])
    }

    override fun getItemCount(): Int {
        return matchPlayerDTOList.size
    }

}