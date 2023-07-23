package com.example.view.fifa.ui.activity.searchsubactivity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.util.Constants
import com.example.searchstudy.util.Pref
import com.example.searchstudy.util.onTextChanged
import com.example.view.fifa.databinding.ActivitySearchSubBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.viewmodels.SearchSubViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchSubActivity : AppCompatActivity(), RecentSearchRecyclerListener {

    @Inject
    lateinit var pref: Pref

    private lateinit var searchDataList: ArrayList<UserDTO>
    private lateinit var binding: ActivitySearchSubBinding
    private lateinit var searchRecentAdapter: RecentSearchAdapter

    private val viewModel: SearchSubViewModel by viewModels()
    private var query = ""  // 검색어
    private var preQuery = "" // 이전 검색어

    //서브 엑티비티와 main액티비티 개발 목적 다시 표시하기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchSubBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initData()
        initObserve()
        initLisnear()
    }

    private fun initData() {
        binding.etSearch.requestFocus()
        searchDataList = pref.getSearchList() as ArrayList<UserDTO>
//        Log.e("cyc","init 초기의 searchDataList사이즈--->${searchDataList.size}")
        setSearchRecentAdapter(searchDataList)
        checkNoRecentSearchView()
    }

    private fun initObserve() {
        viewModel.recentSearchSaveCheck.observe(this) {
            if (it) {
                Log.e("cyc", "최근기록 저장")
                //받은 값이 ok일때 데이터 추가
                viewModel.userdto.value?.let { userDto ->
                    saveSearchData(userDto)
                    searchRecentAdapter.notifyDataSetChanged()
                    checkNoRecentSearchView()
                }
            }
        }
        viewModel.matchDTOList.observe(this) {
            Log.e("cyc", "좀더 큰 개발자가 되고 싶당")
            //날짜 기준 정렬
            it.sortByDescending { matchDTO ->
                matchDTO.matchDate
            }
            val intent = Intent(this,)
            //유저경기 상세 데이터
        }
    }

    private fun initLisnear() {
        binding.etSearch.apply {
            this.onTextChanged { s, start, before, after ->
                if (binding.etSearch.text.toString().isNotEmpty()) {
                    binding.btnTextClear.visibility = View.VISIBLE
                } else {
                    binding.btnTextClear.visibility = View.INVISIBLE
                }
            }

            this.setOnEditorActionListener { textView, actionId, keyEvent ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
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

        binding.btnTextClear.setOnClickListener {
            binding.etSearch.text.clear()
        }
        binding.tvRecentAllDelete.setOnClickListener {
            searchDataList.clear()
            pref.clear()
            searchRecentAdapter.notifyDataSetChanged()
            checkNoRecentSearchView()
        }
    }


    /**
     * 최근 검색어 어댑터 세팅
     */
    private fun setSearchRecentAdapter(searchDataList: ArrayList<UserDTO>) {
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
     * 최근검색어 preference에 저장
     */
    private fun saveSearchData(userDTO: UserDTO) {
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
     * 아이템 삭제
     */
    override fun onItemDelete(position: Int) {
        searchDataList.removeAt(position)
        pref.saveSearchList(searchDataList)
        searchRecentAdapter.notifyDataSetChanged()
        checkNoRecentSearchView()
    }

    override fun onItemClick(position: Int, nickname: String) {
        //해당 아이템 클릭시
        viewModel.requestUserInfo(nickname,Constants.RECENT_SEARCH_SAVE_FALSE)

    }

    private fun checkNoRecentSearchView() {
        binding.tvNoRecentSearch.visibility =
            if (searchRecentAdapter.itemCount > 0) View.INVISIBLE else View.VISIBLE
    }


}


//package com.example.view.fifa.ui.activity.searchsubactivity
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.Log
//import android.view.View
//import android.view.inputmethod.EditorInfo
//import androidx.activity.viewModels
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.view.fifa.databinding.ActivityMainBinding
//import com.example.view.fifa.databinding.ActivitySearchSubBinding
//import com.example.view.fifa.network.models.dto.MatchDTO
//import com.example.view.fifa.viewmodels.SearchSubViewModel
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class SearchSubActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivitySearchSubBinding
//
//    private lateinit var searchResultAdapter: SearchResultAdapter
//
//    private val viewModel: SearchSubViewModel by viewModels()
//
//    private lateinit var matchDtoList: ArrayList<MatchDTO>
//
//    private var query = ""  // 검색어
//
//    private var preQuery = "" // 이전 검색어
//
//
//    //서브 엑티비티와 main액티비티 개발 목적 다시 표시하기
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivitySearchSubBinding.inflate(layoutInflater)
//        setContentView(binding.root)
////        setContentView(R.layout.activity_search_sub)
//        init()
//    }
//
//    private fun init() {
//        setSearchResultAdapter()
//        initData()
//        initLisnear()
//        initObserve()
//    }
//
//    private fun initData() {
//        binding.etSearch.requestFocus()
//    }
//
//    private fun initLisnear() {
//        binding.etSearch.apply {
//            this.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    if (binding.etSearch.text.toString().isNotEmpty()) {
//                        binding.btnTextClear.visibility = View.VISIBLE
//                    } else {
//                        binding.btnTextClear.visibility = View.INVISIBLE
//
//                    }
//                }
//
//                override fun afterTextChanged(p0: Editable?) {
//                }
//
//            })
//
//            this.setOnEditorActionListener { textView, actionId, keyEvent ->
//                when (actionId) {
//                    EditorInfo.IME_ACTION_SEARCH -> {
//                        Log.e("cyc","검색 클릭시")
//                        checkWord()
////                        viewModel.requestUserInfo(textView.text.toString())
//                        true
//                    }
//                    else -> {
//                        false
//                    }
//                }
//            }
//        }
//        //텍스트 전체 삭제 리스너
//        binding.btnTextClear.setOnClickListener {
//            binding.etSearch.text.clear()
//        }
//    }
//
//    private fun initObserve() {
//
//        viewModel.matchDTOList.observe(this){
//            Log.e("cyc","SearchSubActivity--->observe")
//            Log.e("cyc","SearchSubActivity--size-->${it.size}")
//            searchResultAdapter.setSearchResult(it)
//        }
//    }
//
//    private fun setSearchResultAdapter() {
//        searchResultAdapter = SearchResultAdapter()
//        val searchLinearLayoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
//        binding.rv.apply {
//            layoutManager = searchLinearLayoutManager
//            adapter = searchResultAdapter
//            //            this.scrollToPosition(searchAdapter.itemCount - 1) // 해당 포지션으로 스크롤 이동
//        }
//    }
//    private fun checkWord() {
//        query = binding.etSearch.text.toString()
//        if (preQuery != query && !query.isNullOrBlank()) {
//            preQuery = query
//            viewModel.requestUserInfo(query)
//        }
//    }
//}