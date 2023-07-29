package com.example.view.fifa.ui.activity.userdetailactivity

import android.annotation.SuppressLint
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
import com.example.view.fifa.network.models.dto.UserDTO
import java.text.SimpleDateFormat

class UserMatchViewHolder(
    private val context: Context,
    private val binding: ItemUserRecordBinding
) : RecyclerView.ViewHolder(binding.root) {


    @SuppressLint("SimpleDateFormat")
    fun bind(
        matchDTO: MatchDTO,
        userMatchRecyclerListener: UserMatchRecyclerListener,
        userDTO: UserDTO
    ) {

        binding.tvMyUser.text = matchDTO.matchInfo[0].nickname
        binding.tvMyScore.text = matchDTO.matchInfo[0].shoot.goalTotal.toString()
        binding.tvOpponentUser.text = matchDTO.matchInfo[1].nickname
        binding.tvOpponentScore.text = matchDTO.matchInfo[1].shoot.goalTotal.toString()

        val date = matchDTO.matchDate.split("T")
        binding.tvMyMonth.text = date[0]
        binding.tvMyDay.text = with(date[1]) {
            // 시간 형식 HH:mm:ss--->HH:mm 변경
            var dayOut=""
            val dayIntFormat = SimpleDateFormat("HH:mm:ss")
            val dayOutFormat = SimpleDateFormat("HH:mm")
            val tempDate = dayIntFormat.parse(this)
            dayOut = dayOutFormat.format(tempDate)
            return@with dayOut
        }
        matchResultViewColor(matchDTO.matchInfo[0],matchDTO.matchInfo[1], userDTO.nickname)




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
        val tempMatchInfoDTO: MatchInfoDTO = if (searchUser == matchInfoUser1.nickname) {
            matchInfoUser1
            //            when(matchInfoUser1.matchDetail.matchResult)
        } else {
            matchInfoUser2
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
                val color = ContextCompat.getColor(context, R.color.clr_FEE7EF)
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
                val color = ContextCompat.getColor(context, R.color.clr_ECEEF0)
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