package com.example.view.fifa.ui.activity.matchdetailactivity

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.view.fifa.R
import com.example.view.fifa.databinding.ActivityMatchDetailBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MatchInfoDTO
import com.example.view.fifa.network.models.dto.MatchPlayerDTO
import com.example.view.fifa.util.Pref
import com.example.view.fifa.viewmodels.SearchSubViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class MatchDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var pref: Pref

    private val viewModel: SearchSubViewModel by viewModels()


    private lateinit var binding: ActivityMatchDetailBinding
    private lateinit var matchDTO: MatchDTO
    private lateinit var nickName: String
    private lateinit var matchMyPlayerAdapter: MatchPlayerAdapter
    private lateinit var matchOpponentPlayerAdapter: MatchPlayerAdapter
    private lateinit var matchMyPlayerDTOList: ArrayList<MatchPlayerDTO>
    private lateinit var matchOpponentPlayerDTOList: ArrayList<MatchPlayerDTO>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initData()
        initObserve()
        initListener()
    }

    private fun initData() {
        val intent = intent
        intent.getSerializableExtra("MatchDTO")?.let {
            matchDTO = it as MatchDTO
        }
        intent.getStringExtra("NickName")?.let {
            nickName = it
        }
        binding.tvMyUser.text = matchDTO.matchInfo[0].nickname
        binding.tvMyScore.text = matchDTO.matchInfo[0].shoot.goalTotal.toString()
        binding.tvOpponentUser.text = matchDTO.matchInfo[1].nickname
        binding.tvOpponentScore.text = matchDTO.matchInfo[1].shoot.goalTotal.toString()
        val date = matchDTO.matchDate.split("T")
        binding.tvMonth.text = date[0]
        binding.tvDay.text = with(date[1]) {
            // 시간 형식 HH:mm:ss--->HH:mm 변경
            var dayOut = ""
            val dayIntFormat = SimpleDateFormat("HH:mm:ss")
            val dayOutFormat = SimpleDateFormat("HH:mm")
            val tempDate = dayIntFormat.parse(this)
            dayOut = dayOutFormat.format(tempDate)
            return@with dayOut
        }
        binding.tvTitleUserNickname.text = nickName
        matchDetailResultViewColor(matchDTO.matchInfo[0], binding.linearMy, this)
        matchDetailResultViewColor(matchDTO.matchInfo[1], binding.linearOpponent, this)

        //어댑터 세팅2개 각각의 어댑터
        setMatchMyPlayerAdapter(matchMyPlayerDTOList)
        setMatchOpponentPlayerAdapter(matchOpponentPlayerDTOList)
    }

    private fun initObserve() {

    }

    private fun initListener() {
        binding.btnBack.setOnClickListener {
            this.finish()
        }
    }


    /**
     *
     */
    private fun setMatchMyPlayerAdapter(matchPlayerDTOList: ArrayList<MatchPlayerDTO>) {
        matchMyPlayerAdapter = MatchPlayerAdapter(matchPlayerDTOList)
        val searchLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchLinearLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rvMy.apply {
            layoutManager = searchLinearLayoutManager
            adapter = matchMyPlayerAdapter
        }
    }

    /**
     *
     */
    private fun setMatchOpponentPlayerAdapter(matchPlayerDTOList: ArrayList<MatchPlayerDTO>) {
        matchOpponentPlayerAdapter = MatchPlayerAdapter(matchPlayerDTOList)
        val searchLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchLinearLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rvMy.apply {
            layoutManager = searchLinearLayoutManager
            adapter = matchOpponentPlayerAdapter
        }
    }


    /**
     * 유저의 승패에 따른 색깔 결정
     */
    private fun matchDetailResultViewColor(
        matchInfoUser: MatchInfoDTO,
        view: View,
        context: Context
    ) {
        when (matchInfoUser.matchDetail.matchResult) {
            "승" -> {
                val color = ContextCompat.getColor(context, R.color.clr_E1EFFF)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    view.background.colorFilter = BlendModeColorFilter(
                        (color),
                        BlendMode.SRC_ATOP
                    )
                } else {
                    view.background.setColorFilter(
                        color,
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
            "패" -> {
                val color = ContextCompat.getColor(context, R.color.clr_FEE7EF)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    view.background.colorFilter = BlendModeColorFilter(
                        (color),
                        BlendMode.SRC_ATOP
                    )
                } else {
                    view.background.setColorFilter(
                        color,
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
            "무" -> {
                val color = ContextCompat.getColor(context, R.color.clr_ECEEF0)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    view.background.colorFilter = BlendModeColorFilter(
                        (color),
                        BlendMode.SRC_ATOP
                    )
                } else {
                    view.background.setColorFilter(
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