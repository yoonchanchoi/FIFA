package com.example.view.fifa.ui.activity.userdetailactivity

import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemUserRecordBinding
import com.example.view.fifa.network.models.dto.MatchDTO

class UserMatchViewHolder(private val binding: ItemUserRecordBinding) : RecyclerView.ViewHolder(binding.root){


    fun bind(matchDTO: MatchDTO,userMatchRecyclerListener: UserMatchRecyclerListener){
        binding.tvMyUser.text = matchDTO.matchInfo[0].nickname
        binding.tvMyScore.text = matchDTO.matchInfo[0].shoot.goalTotal.toString()
        binding.tvOpponentUser.text = matchDTO.matchInfo[1].nickname
        binding.tvOpponentScore.text = matchDTO.matchInfo[1].shoot.goalTotal.toString()

        binding.clUserRecordItem.setOnClickListener {
            userMatchRecyclerListener.onItemClick(
                bindingAdapterPosition
            )
        }
    }
}