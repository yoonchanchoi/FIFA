package com.example.view.fifa.ui.activity.userdetailactivity

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.R
import com.example.view.fifa.databinding.ItemUserRecordBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MatchInfoDTO
import java.text.SimpleDateFormat

class UserMatchViewHolder(
    private val context: Context,
    private val binding: ItemUserRecordBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(
        matchDTO: MatchDTO,
        userMatchRecyclerListener: UserMatchRecyclerListener,
        nickName: String
    ) {
        binding.tvMyUser.text = matchDTO.matchInfo[0].nickname
        binding.tvMyScore.text = matchDTO.matchInfo[0].shoot.goalTotal.toString()
        binding.tvOpponentUser.text = matchDTO.matchInfo[1].nickname
        binding.tvOpponentScore.text = matchDTO.matchInfo[1].shoot.goalTotal.toString()
        // 날짜 데이터 가져오기
        // 날짜 포맷 해서 형식 맞추기
        // 승패 따지기위해서 검색 유저 이름 가져오기
        val date = matchDTO.matchDate.split("T")
        binding.tvMyMonth.text = date[0]
        binding.tvMyDay.text = date[1].apply {
            val dayFormat = SimpleDateFormat("hh:mm")
            dayFormat.format(this)
        }
        matchResultViewColor(matchDTO.matchInfo[0],matchDTO.matchInfo[1], nickName)

        binding.clUserRecordItem.setOnClickListener {
            userMatchRecyclerListener.onItemClick(
                bindingAdapterPosition
            )
        }
    }

    private fun matchResultViewColor(
        matchInfoUser1: MatchInfoDTO,
        matchInfoUser2: MatchInfoDTO,
        searchUser: String
    ) {
        val tempMatchInfoDTO: MatchInfoDTO
        if (searchUser == matchInfoUser1.nickname) {
            tempMatchInfoDTO = matchInfoUser1
            //            when(matchInfoUser1.matchDetail.matchResult)
        } else {
            tempMatchInfoDTO = matchInfoUser2
        }

        when (tempMatchInfoDTO.matchDetail.matchResult) {
            "승" -> {
//                val gradientDrawable = ContextCompat.getDrawable(context, R.drawable.shape_item_round_bg)
                val color = ContextCompat.getColor(context, R.color.clr_E1EFFF)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    binding.clUserRecordItem.background.colorFilter = BlendModeColorFilter(
                        (color),
                        BlendMode.SRC_ATOP
                    )
                } else {
                    binding.clUserRecordItem.background.setColorFilter(
                        color,
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
            "패" -> {
//                val gradientDrawable = ContextCompat.getDrawable(context, R.drawable.shape_item_round_bg)
                val color = ContextCompat.getColor(context, R.color.clr_E1EFFF)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    binding.clUserRecordItem.background.colorFilter = BlendModeColorFilter(
                        (color),
                        BlendMode.SRC_ATOP
                    )
                } else {
                    binding.clUserRecordItem.background.setColorFilter(
                        color,
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
            "무" -> {
//                val gradientDrawable = ContextCompat.getDrawable(context, R.drawable.shape_item_round_bg)
                val color = ContextCompat.getColor(context, R.color.clr_E1EFFF)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    binding.clUserRecordItem.background.colorFilter = BlendModeColorFilter(
                        (color),
                        BlendMode.SRC_ATOP
                    )
                } else {
                    binding.clUserRecordItem.background.setColorFilter(
                        color,
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
//            else ->{
//                Log.e("cyc","숭패에 승, 패, 무, 어떤한 값도 없음")
//            }
        }


    }

}