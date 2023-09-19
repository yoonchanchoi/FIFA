package com.football.view.fifa.network.models.dto

data class MatchPlayerResult(
    val spName: String = "",
    val spDesc: String = "",
    override val spId: Int,
    override val spPosition: Int,
    override val spGrade: Int ,
    override val status: StatusResult
) : PlayerResult(spId, spPosition, spGrade, status)
