package com.football.view.fifa.ui.activity.matchdetailactivity

import com.football.view.fifa.network.models.dto.MatchPlayerResult

interface MatchPlayerRecyclerListener {
    fun onItemClick(matchPlayDTO: MatchPlayerResult)

}