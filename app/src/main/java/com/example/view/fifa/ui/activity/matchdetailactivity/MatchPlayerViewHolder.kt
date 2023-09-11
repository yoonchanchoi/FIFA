package com.example.view.fifa.ui.activity.matchdetailactivity

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.R
import com.example.view.fifa.databinding.ItemMatchPlayerBinding
import com.example.view.fifa.network.models.dto.MatchPlayerDTO

class MatchPlayerViewHolder(
    private val binding: ItemMatchPlayerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, matchPlayDTO: MatchPlayerDTO) {
        binding.tvPlayerNickname.text = matchPlayDTO.spName
        sppositionColor(context,binding.tvPlayerPosition,matchPlayDTO.spPosition)
//        binding.tvPlayerPosition.text = matchPlayDTO.spPosition
    }

    private fun sppositionColor(context: Context, textView: TextView, spPosition: String) {
        textView.text=spPosition
        when (spPosition) {
            "GK" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_CC392E))
            }
            "SW" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "RWB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "RB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "RCB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "CB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "LCB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "LB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "LWB" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_449847))
            }
            "RDM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "CDM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "LDM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "RM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "RCM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "CM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "LCM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "LM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "RAM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "CAM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "LAM" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_673AB7))
            }
            "RF" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_FF9800))
            }
            "CF" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_FF9800))
            }
            "LF" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_FF9800))
            }
            "RW" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_FF9800))
            }
            "RS" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_FF9800))
            }
            "ST" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_FF9800))
            }
            "LS" -> {
                textView.setTextColor(ContextCompat.getColor(context, R.color.clr_FF9800))
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