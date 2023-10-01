package com.football.view.fifa.ui.activity.userdetailactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView.OnScrollChangeListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.football.view.fifa.R
import com.football.view.fifa.util.Pref
import com.football.view.fifa.databinding.ActivityUserDetailBinding
import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.UserInfoResult
import com.football.view.fifa.ui.activity.matchdetailactivity.MatchDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//* android 기능 ctrl + shift + 방향키는 해당 코드 위치 변경(신기하넹)
@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity(), UserMatchRecyclerListener {

    @Inject
    lateinit var pref: Pref

    private lateinit var userDTO: UserInfoResult
    private lateinit var userRank: String
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var matchDTOList: ArrayList<MatchMetaDataResult>
    private lateinit var userMatchAdapter: UserMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initData()
        initListener()
    }

    private fun initData() {
        //검색 페이지에서 넘어온값 확인 및 세팅
        val intent = intent
        intent.getSerializableExtra("ArrayList<MatchDTO>")?.let {
            matchDTOList = it as ArrayList<MatchMetaDataResult>
        }
        intent.getSerializableExtra("SearchUserDTO")?.let {
            userDTO = it as UserInfoResult
        }
        intent.getStringExtra("UserRank")?.let {
            userRank = it
        }
        binding.tvUserNickname.text = userDTO.nickname
        binding.tvTitleUserNickname.text = userDTO.nickname
        binding.tvUserLevel.text = "Lv " + userDTO.level
        binding.tvUserMostRank.text = userRank

        Log.e("cyc","유저 상세 Activity---matchDTOList---->${matchDTOList}")
        Log.e("cyc","유저 상세 Activity---userDTO---->${userDTO}")

        //유저의 매칭기록 어댑터 세팅
        setUserMatchAdapter(matchDTOList, userDTO)
    }

    private fun initListener() {
        //스크롤에 따른 최상당 topBar 보여주기
        binding.nsv.setOnScrollChangeListener(OnScrollChangeListener { view, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY>isTouchInside(binding.tvUserNickname)) {
                binding.linearTop.isSelected = true
                binding.tvTitleUserNickname.isSelected = true
            }else {
                binding.linearTop.isSelected = false
                binding.tvTitleUserNickname.isSelected = false
            }
        })

        /**
         * 뒤로가기 버튼
         */
        binding.btnBack.setOnClickListener {
            this.finish()
        }
    }

    /**
     * 최근 검색어 어댑터 세팅
     */
    private fun setUserMatchAdapter(matchDTOList: ArrayList<MatchMetaDataResult>, userDTO: UserInfoResult) {
        userMatchAdapter = UserMatchAdapter(this@UserDetailActivity, this, matchDTOList, userDTO)
        val searchLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchLinearLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rv.apply {
            layoutManager = searchLinearLayoutManager
            adapter = userMatchAdapter
        }
    }

    override fun onItemClick(matchDTO: MatchMetaDataResult, nickName: String) {
        val intent = Intent(this,MatchDetailActivity::class.java)
        intent.putExtra("MatchDTO",matchDTO)
        intent.putExtra("NickName",nickName)
        startActivity(intent)

    }

    override fun onErrorItemClick() {
        Toast.makeText(this, getString(R.string.app_error_game), Toast.LENGTH_SHORT)
            .show()
    }

    //해당 view의 y좌표 구하기 (기능 및 맞게 돌아가지만 확실하지 않음 재공부 및  다시 구현해야됨)
    private fun isTouchInside(view: View): Int {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return location[1] + view.height
    }
}


