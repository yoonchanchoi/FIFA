package com.example.view.fifa.ui.activity.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.view.fifa.R
import com.example.view.fifa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val arrayImage : ArrayList<Drawable> = arrayListOf()

    // 배너 핸들러
    private val bannerHandler = BannerHandler()
//    private lateinit var bannerHandler : BannerHandler
    //2초마다 자도 배너 스와이프
    private val intervalTime: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actionbar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun init() {
        val appbar = supportActionBar
        appbar?.let { it.title = "FIFA"}
        initData()
        initObserve()
        initListener()
    }

    private fun initData() {
        setbanner()
    }

    private fun initListener() {
    }

    private fun initObserve() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, "FIFA 세팅 중", Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }

    fun setbanner(){

        ContextCompat.getDrawable(this, R.drawable.fifa01)?.let { arrayImage.add(it) }
        ContextCompat.getDrawable(this, R.drawable.fifa02)?.let { arrayImage.add(it) }
        ContextCompat.getDrawable(this, R.drawable.fifa03)?.let { arrayImage.add(it) }

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
            if (binding.vpImage.currentItem == binding.vpImage.adapter?.let { it.itemCount-1 }) {
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


}