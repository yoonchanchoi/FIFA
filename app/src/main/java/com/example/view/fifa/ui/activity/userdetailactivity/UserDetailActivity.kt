package com.example.view.fifa.ui.activity.userdetailactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.util.Pref
import com.example.view.fifa.databinding.ActivitySearchSubBinding
import com.example.view.fifa.databinding.ActivityUserDetailBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.ui.activity.searchsubactivity.RecentSearchAdapter
import com.example.view.fifa.ui.activity.searchsubactivity.RecentSearchRecyclerListener
import com.example.view.fifa.viewmodels.SearchSubViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity(), UserMatchRecyclerListener {

    @Inject
    lateinit var pref: Pref

    private lateinit var nickName: String
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var matchDTOList: ArrayList<MatchDTO>
    private lateinit var userMatchAdapter: UserMatchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init() {
        initData()
        initObserve()
        initLisnear()
    }


    private fun initData() {
        val intent = intent
        matchDTOList = intent.getSerializableExtra("ArrayList<MatchDTO>") as ArrayList<MatchDTO>
        intent.getStringExtra("nickName")?.let {
            nickName = it
        }
        Log.e("cyc", "nickName--->${nickName}")
        setUserMatchAdapter(matchDTOList, nickName)
        Log.e("cyc", "ArrayList<MatchDTO>--->$matchDTOList")
    }

    private fun initObserve() {

    }

    private fun initLisnear() {

    }


    /**
     * 최근 검색어 어댑터 세팅
     */
    private fun setUserMatchAdapter(matchDTOList: ArrayList<MatchDTO>, nickName: String) {
        userMatchAdapter = UserMatchAdapter(this@UserDetailActivity,this, matchDTOList,nickName)
        val searchLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        searchLinearLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rv.apply {
            layoutManager = searchLinearLayoutManager
            adapter = userMatchAdapter
        }
    }

    override fun onItemDelete(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

}


