package com.example.view.fifa.ui.activity.matchdetailactivity

import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemMatchPlayerBinding
import com.example.view.fifa.network.models.dto.MatchPlayerDTO

class MatchPlayerViewHolder(
    private val binding: ItemMatchPlayerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(matchPlayDTO: MatchPlayerDTO) {
        binding.tvPlayerNickname.text = matchPlayDTO.spName
        binding.tvPlayerPosition.text = matchPlayDTO.spPosition
    }

}