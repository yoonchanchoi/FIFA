package com.example.view.fifa.ui.activity.matchdetailactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.view.fifa.databinding.ActivityMatchDetailBinding
import com.example.view.fifa.databinding.ActivitySearchSubBinding
import com.example.view.fifa.databinding.ActivityUserDetailBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.util.Pref
import com.example.view.fifa.util.Util
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class MatchDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var pref: Pref

    private lateinit var binding: ActivityMatchDetailBinding
    private lateinit var matchDTO: MatchDTO
    private lateinit var nickName: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        initData()
        initObserve()
        initListener()
    }

    private fun initData(){
        val intent = intent
        intent.getSerializableExtra("MatchDTO")?.let{
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
            var dayOut=""
            val dayIntFormat = SimpleDateFormat("HH:mm:ss")
            val dayOutFormat = SimpleDateFormat("HH:mm")
            val tempDate = dayIntFormat.parse(this)
            dayOut = dayOutFormat.format(tempDate)
            return@with dayOut
        }

        Util.matchResultViewColor(matchDTO.matchInfo[0], matchDTO.matchInfo[1],nickName,binding.linearMy, this)
        Util.matchResultViewColor(matchDTO.matchInfo[0], matchDTO.matchInfo[1],nickName,binding.linearOpponent, this)

    }

    private fun initObserve(){

    }

    private fun initListener() {
        binding.btnBack.setOnClickListener {
            this.finish()
        }
    }


}