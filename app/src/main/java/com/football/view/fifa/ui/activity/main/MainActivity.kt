package com.football.view.fifa.ui.activity.main

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.football.view.fifa.R
import com.football.view.fifa.databinding.ActivityMainBinding
import com.football.view.fifa.ui.activity.licensoractivity.LicenseActivity
import com.football.view.fifa.ui.activity.searchsubactivity.SearchSubActivity
import com.football.view.fifa.ui.dialog.LoadingProgressDialog
import com.football.view.fifa.util.Pref
import com.football.view.fifa.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var pref: Pref

    private lateinit var binding: ActivityMainBinding
    private lateinit var loadingProgressDialog: LoadingProgressDialog

    private val viewModel: MainViewModel by viewModels()

    private val arrayImage: ArrayList<Drawable> = arrayListOf()
    // 배너 핸들러
    private val bannerHandler = BannerHandler()
    //2초마다 자도 배너 스와이프
    private val intervalTime: Long = 2000
    private var waitTime = 0L

    private var checkSaveAllSpidList: Boolean = false
    private var checkSaveAllSppositionList: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_actionbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun init() {
        val appbar = supportActionBar
        appbar?.let { it.title = "FIFA" }
        initData()
        initObserve()
        initListener()
    }

    private fun initData() {
        loadingProgressDialog = LoadingProgressDialog(this)
        loadingProgressDialog.show()

        viewModel.requestSpId()
        viewModel.requestSpPosition()
        setbanner()
    }
    private fun initObserve(){
        viewModel.spIdDTOList.observe(this){
            pref.saveAllSpidList(it)
            checkSaveAllSpidList=true
            if(checkSaveAllSpidList && checkSaveAllSppositionList){
                loadingProgressDialog.dismiss()
            }
        }
        viewModel.spPositionResultList.observe(this){
            pref.saveAllSppositionList(it)
            checkSaveAllSppositionList = true
            if(checkSaveAllSpidList && checkSaveAllSppositionList){
                loadingProgressDialog.dismiss()
            }
        }
    }

    private fun initListener() {
        binding.btnSearch.setOnClickListener {
            val intent = Intent(this, SearchSubActivity::class.java)
            startActivity(intent)
        }
    }

    // 메뉴 업션 구현(아직 미구헌 추후 추가)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.setting_action){
            val intent = Intent(this, LicenseActivity::class.java)
            startActivity(intent)
        }


        return super.onOptionsItemSelected(item)
    }

    fun setbanner() {

        ContextCompat.getDrawable(this, R.drawable.fifa_image01)?.let { arrayImage.add(it) }
        ContextCompat.getDrawable(this, R.drawable.fifa_image02)?.let { arrayImage.add(it) }
        ContextCompat.getDrawable(this, R.drawable.fifa_image03)?.let { arrayImage.add(it) }

        // initBanner()
        val bannerAdapter = BannerAdapter()
        bannerAdapter.setImage(arrayImage)
        binding.vpImage.offscreenPageLimit = 1 // 해당 뷰페이저의 매개변수만큼 양쪽의 뷰를 미리 생성
        binding.vpImage.adapter = bannerAdapter

    }

    /**
     * 배너 자동 스클롤 핸들러
     */
    inner class BannerHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(message: Message) {
            super.handleMessage(message)
            if (binding.vpImage.currentItem == binding.vpImage.adapter?.let { it.itemCount - 1 }) {
                binding.vpImage.currentItem = 0
                autoScrollStart()
            } else {
                binding.vpImage.currentItem = binding.vpImage.currentItem + 1
                autoScrollStart()
            }
        }
    }

    /**
     * 자동스크롤 시작
     */
    private fun autoScrollStart() {
        bannerHandler.removeMessages(0)
        bannerHandler.sendEmptyMessageDelayed(0, intervalTime)
    }

    /**
     * 자동스클롤 멈춤
     */
    private fun autoScrollStop() {
        bannerHandler.removeMessages(0)
    }

    override fun onPostResume() {
        super.onPostResume()
        autoScrollStart()
    }

    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }

    /**
     * 뒤로 가기 버튼 클릭
     */
    override fun onBackPressed() {
        if (System.currentTimeMillis() - waitTime >= 1500) {
            waitTime = System.currentTimeMillis()
            Toast.makeText(this, getString(R.string.app_finish_toast), Toast.LENGTH_SHORT)
                .show()
        } else {
            finish() // 액티비티 종료
        }
    }
}