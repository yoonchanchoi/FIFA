package com.example.view.fifa.ui.activity.userdetailactivity

import com.example.view.fifa.network.models.dto.MatchDTO

interface UserMatchRecyclerListener {
    fun onItemClick(matchDTO: MatchDTO, nickName: String)
}