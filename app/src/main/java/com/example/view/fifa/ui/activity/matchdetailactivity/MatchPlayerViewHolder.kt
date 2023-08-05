package com.example.view.fifa.ui.activity.matchdetailactivity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.R
import com.example.view.fifa.databinding.ItemMatchPlayerBinding
import com.example.view.fifa.databinding.ItemUserRecordBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MatchInfoDTO
import com.example.view.fifa.network.models.dto.MatchPlayDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.util.Util
import java.text.SimpleDateFormat

class MatchPlayerViewHolder(
    private val binding: ItemMatchPlayerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(matchPlayDTO: MatchPlayDTO) {
        binding.tvPlayerNickname.text = matchPlayDTO.spName
        binding.tvPlayerPosition.text = matchPlayDTO.spPosition
    }

}