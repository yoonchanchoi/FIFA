package com.example.view.fifa.ui.activity.searchsubactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.util.Pref
import com.example.searchstudy.util.onTextChanged
import com.example.view.fifa.databinding.ActivitySearchSubBinding
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.viewmodels.SearchSubViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchSubActivity : AppCompatActivity() {

    @Inject
    lateinit var pref: Pref

    private lateinit var searchDataList: ArrayList<UserDTO>
    private lateinit var binding: ActivitySearchSubBinding
    private lateinit var searchRecentAdapter: SearchRecentAdapter

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
        setSearchRecentAdapter(searchDataList)
    }

    private fun initObserve(){
        viewModel.userCheck.observe(this){
            if(it){
                //받은 값이 ok일때 데이터 추가
                viewModel.userdto.value?.let { userDto -> searchDataList.add(userDto) }
            //  검색 후 저장하는데 오류가 안나는지 확인
            //                pref.saveSearchList()
            }
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
                            viewModel.requestUserInfo(textView.text.toString())
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
            //리사이클러뷰의 모든 item삭제
        }
    }


    /**
     * 최근 검색어 어댑터 세팅
     */
    private fun setSearchRecentAdapter(searchDataList: ArrayList<UserDTO>) {
        searchRecentAdapter = SearchRecentAdapter(searchDataList)
        val searchLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        searchLinearLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rvRecentSearch.apply {
            layoutManager = searchLinearLayoutManager
            adapter = searchRecentAdapter
        }
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