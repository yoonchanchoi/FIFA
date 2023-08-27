package com.example.view.fifa.ui.activity.userdetailactivity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
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

        //유저의 이름 및 스코어 값 세팅
        binding.tvMyUser.text = matchDTO.matchInfo[0].nickname
        binding.tvMyScore.text = matchDTO.matchInfo[0].shoot.goalTotal.toString()
        binding.tvOpponentUser.text = matchDTO.matchInfo[1].nickname
        binding.tvOpponentScore.text = matchDTO.matchInfo[1].shoot.goalTotal.toString()

        //매칭의 시간 데이터 값을 세팅
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

        //매칭의 승패에 따른 색깔 결정
        matchResultViewColor(matchDTO.matchInfo[0],matchDTO.matchInfo[1], userDTO.nickname)

        //아이템 선택에 따른 리스너(인터페이스 내용을 구현해야됨)
        binding.clUserRecordItem.setOnClickListener {
            userMatchRecyclerListener.onItemClick(
                matchDTO, userDTO.nickname
            )
        }
    }

    /**
     * 유저의 승패에 따른 색깔 결정
     */
    private fun matchResultViewColor(
        matchInfoUser1: MatchInfoDTO,
        matchInfoUser2: MatchInfoDTO,
        searchUser: String
    ) {
        val tempMatchInfoDTO: MatchInfoDTO = if (searchUser == matchInfoUser1.nickname) {
            matchInfoUser1
        } else {
            matchInfoUser2
        }
        //각각의 매치 승패무 값에 따른 배경 색만 다르게 처리
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
            // 추후에 처리
//            else ->{
//                Log.e("cyc","숭패에 승, 패, 무, 어떤한 값도 없음")
//            }
        }
    }
}