package com.example.view.fifa.ui.activity.matchdetailactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.view.fifa.databinding.ActivitySearchSubBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.util.Pref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MatchDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var pref: Pref

    private lateinit var binding: ActivitySearchSubBinding
    private lateinit var matchDTO: MatchDTO
    private lateinit var nickName: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchSubBinding.inflate(layoutInflater)
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
//        binding.
    }

    private fun initObserve(){

    }

    private fun initListener() {

    }


}