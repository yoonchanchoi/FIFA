package com.football.view.fifa.ui.activity.searchsubactivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.football.view.fifa.databinding.ActivitySearchSubBinding
import com.football.view.fifa.network.models.dto.UserInfoResult
import com.football.view.fifa.ui.activity.userdetailactivity.UserDetailActivity
import com.football.view.fifa.ui.dialog.LoadingProgressDialog
import com.football.view.fifa.util.Constants
import com.football.view.fifa.util.Pref
import com.football.view.fifa.util.onTextChanged
import com.football.view.fifa.viewmodels.SearchSubViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchSubActivity : AppCompatActivity(), RecentSearchRecyclerListener {

    @Inject
    lateinit var pref: Pref

    private lateinit var searchDataList: ArrayList<UserInfoResult>
    private lateinit var binding: ActivitySearchSubBinding
    private lateinit var searchRecentAdapter: RecentSearchAdapter
    private lateinit var loadingProgressDialog: LoadingProgressDialog
    private val viewModel: SearchSubViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchSubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initData()
        initObserve()
        initListener()
    }

    private fun initData() {
        loadingProgressDialog = LoadingProgressDialog(this)
        binding.etSearch.requestFocus()
        searchDataList = pref.getSearchList() as ArrayList<UserInfoResult>
        setSearchRecentAdapter(searchDataList)
        checkNoRecentSearchView()
    }

    private fun initObserve() {

        //최근 검색어를 저장할지 체크
        viewModel.recentSearchSaveCheck.observe(this) {
            if (it) {
                //받은 값이 ok일때 데이터 추가
                viewModel.userdto.value?.let { userDto ->
                    saveSearchData(userDto)
                    searchRecentAdapter.notifyDataSetChanged()
                    checkNoRecentSearchView()
                }
            }
        }
        //매치데이터 리스트 옵저블
        viewModel.matchDTOList.observe(this) {
            //날짜 기준 정렬
            it.sortByDescending { matchDTO ->
                matchDTO.matchDate
            }
            //* Serializable를 객체들이 상속해줘야된다. 즉 여기서는 MatchDTO가 Serializable를 상속해줘야되나 그리고 만약
            //* MatchDTO안에 다른객체가 있다면 그것 또한 Serializable를 상속해줘야된다.
            //* ViewModel안에 intent, StartActivity 가능! (하지만 개인적으로 조금더 생각해봐야될 부분임)
            val intent = Intent(this,UserDetailActivity::class.java)
            intent.putExtra("ArrayList<MatchDTO>",it)
            intent.putExtra("SearchUserDTO",viewModel.userdto.value)
            intent.putExtra("UserRank",viewModel.userRank.value)
            loadingProgressDialog.dismiss()
            startActivity(intent)
        }
    }

    private fun initListener() {
        //검색어 입력값에 따른 검색어 지우기 버튼 유무
        binding.etSearch.apply {

            this.onTextChanged { s, start, before, after ->
                if (binding.etSearch.text.toString().isNotEmpty()) {
                    binding.btnTextClear.visibility = View.VISIBLE
                } else {
                    binding.btnTextClear.visibility = View.INVISIBLE
                }
            }

            //키보드에 검색Action 설정
            this.setOnEditorActionListener { textView, actionId, keyEvent ->

                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        loadingProgressDialog.show()
                        if (textView.text.isNullOrBlank()) {
                            Log.e("cyc", "검색 값이 null 이거나 비어있을때 ")
                            false
                        } else {
                            viewModel.requestUserInfo(textView.text.toString(),Constants.RECENT_SEARCH_SAVE_TRUE)
                            Log.e("cyc", "검색 값이 null이 아니고 비어있지 않을때 ")
                            true
                        }
                    }
                    else -> {
                        false
                    }
                }
            }
        }

        /**
         * 검색어 삭제 버튼
         */
        binding.btnTextClear.setOnClickListener {
            binding.etSearch.text.clear()
        }

        /**
         * 모든 최근기록 검색어 삭제 버튼
         */
        binding.tvRecentAllDelete.setOnClickListener {
            searchDataList.clear()
            pref.clear()
            searchRecentAdapter.notifyDataSetChanged()
            checkNoRecentSearchView()
        }

        /**
         * 뒤로가기(back) 버튼
         */
        binding.btnBack.setOnClickListener {
            this.finish()
        }
    }

    /**
     * 최근기록 검색어 어댑터 세팅
     */
    private fun setSearchRecentAdapter(searchDataList: ArrayList<UserInfoResult>) {
        searchRecentAdapter = RecentSearchAdapter(this, searchDataList)
        val searchLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        searchLinearLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rvRecentSearch.apply {
            layoutManager = searchLinearLayoutManager
            adapter = searchRecentAdapter
        }
    }

    /**
     * 최근기록 검색어를 preference에 저장
     */
    private fun saveSearchData(userDTO: UserInfoResult) {
        //버전 차이에 대한 생각 고민 중
        //같은 검색일경우 삭제
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            searchDataList.removeIf {
                it.nickname == userDTO.nickname
            }
        } else {
            searchDataList.forEach {
                if (it.nickname == userDTO.nickname) {
                    searchDataList.remove(it)
                }
            }
        }

        this.searchDataList.add(userDTO)

        if (searchDataList.size > 10) {
            searchDataList.removeAt(0)
        }
        // 기존 데이터에 덮어쓰기
        pref.saveSearchList(this.searchDataList)
    }

    /**
     * 최근기록 검색어 하나하나 아이템 삭제
     */
    override fun onItemDelete(position: Int) {
        searchDataList.removeAt(position)
        pref.saveSearchList(searchDataList)
        searchRecentAdapter.notifyDataSetChanged()
        checkNoRecentSearchView()
    }

    /**
     *최근 검색어 아이템 클릭 이벤트
     */
    override fun onItemClick(position: Int, nickname: String) {
        //해당 아이템 클릭시
        loadingProgressDialog.show()
        viewModel.requestUserInfo(nickname,Constants.RECENT_SEARCH_SAVE_FALSE)

    }

    /**
     * "최근 검색된 단어가 없습니다." 뷰 유무 체크
     */
    private fun checkNoRecentSearchView() {
        binding.tvNoRecentSearch.visibility =
            if (searchRecentAdapter.itemCount > 0) View.INVISIBLE else View.VISIBLE
    }
}
