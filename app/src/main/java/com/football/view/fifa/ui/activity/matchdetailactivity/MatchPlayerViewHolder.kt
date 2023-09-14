package com.football.view.fifa.ui.activity.matchdetailactivity

import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.football.view.fifa.R
import com.football.view.fifa.databinding.ItemMatchPlayerBinding
import com.football.view.fifa.network.models.dto.MatchPlayerResult

class MatchPlayerViewHolder(
    private val binding: ItemMatchPlayerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, matchPlayDTO: MatchPlayerResult) {
        binding.tvPlayerNickname.text = matchPlayDTO.spName
        sppositionColor(context,binding.tvPlayerPosition,matchPlayDTO.spPosition)
    //        binding.tvPlayerPosition.text = matchPlayDTO.spPosition
    }

    private fun sppositionColor(context: Context, textView: TextView, spPosition: String) {
        textView.text=spPosition
        when (spPosition) {
            "GK" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_FF9800))
            }
            "SW" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_3A54E2))
            }
            "RWB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_3A54E2))
            }
            "RB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_3A54E2))
            }
            "RCB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_3A54E2))
            }
            "CB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_3A54E2))
            }
            "LCB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_3A54E2))
            }
            "LB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_3A54E2))
            }
            "LWB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_3A54E2))
            }
            "RDM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "CDM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "LDM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "RM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "RCM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "CM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "LCM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "LM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "RAM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "CAM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "LAM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "RF" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_CC392E))
            }
            "CF" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_CC392E))
            }
            "LF" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_CC392E))
            }
            "RW" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_CC392E))
            }
            "RS" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_CC392E))
            }
            "ST" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_CC392E))
            }
            "LS" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_CC392E))
            }
            "LW" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_5A5A5A))
            }
            "SUB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_5A5A5A))
            }
            else ->{
                Log.e("cyc","해당 포지션이 없거나 에러인 경우")
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_5A5A5A))
            }
        }
    }
}