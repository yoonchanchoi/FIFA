package com.example.view.fifa.ui.activity.searchresultactivity

import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemUserRecordBinding
import com.example.view.fifa.network.models.dto.MatchDTO

class UserMatchViewHolder(private val binding: ItemUserRecordBinding) : RecyclerView.ViewHolder(binding.root){


    fun bind(matchDtoList: MatchDTO){
//        Log.e("cyc","adapter---matchDtoList--->${matchDtoList}")
//        Log.e("cyc","adapter---matchDtoList.matchInfo[0].nickname--->${matchDtoList.matchInfo[0].nickname}")
//        Log.e("cyc","adapter---matchDtoList.matchInfo[0].shoot.goalTotal.toString()--->${matchDtoList.matchInfo[0].shoot.goalTotal.toString()}")

        binding.tvMyUser.text = matchDtoList.matchInfo[0].nickname
        binding.tvMyScore.text = matchDtoList.matchInfo[0].shoot.goalTotal.toString()
        binding.tvOpponentUser.text = matchDtoList.matchInfo[1].nickname
        binding.tvOpponentScore.text = matchDtoList.matchInfo[1].shoot.goalTotal.toString()
    }
}