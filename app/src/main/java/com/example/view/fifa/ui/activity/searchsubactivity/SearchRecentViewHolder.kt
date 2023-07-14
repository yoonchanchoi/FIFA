package com.example.view.fifa.ui.activity.searchsubactivity

import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemRecentSearchBinding
import com.example.view.fifa.databinding.ItemUserRecordBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.UserDTO

class SearchRecentViewHolder(private val binding: ItemRecentSearchBinding) : RecyclerView.ViewHolder(binding.root){


    fun bind(userDTO: UserDTO){
        binding.tvName.text =userDTO.nickname
        binding.tvLevel.text=userDTO.level.toString()

        //삭제 버튼 생성
    }
}