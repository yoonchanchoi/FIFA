package com.example.view.fifa.ui.activity.matchdetailactivity

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.view.fifa.R
import com.example.view.fifa.databinding.ActivityMatchDetailBinding
import com.example.view.fifa.network.models.dto.*
import com.example.view.fifa.ui.dialog.LoadingProgressDialog
import com.example.view.fifa.util.Constants
import com.example.view.fifa.util.Pref
import com.example.view.fifa.viewmodels.MatchDetailViewModel
import com.example.view.fifa.viewmodels.SearchSubViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class MatchDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var pref: Pref

    private lateinit var binding: ActivityMatchDetailBinding
    private lateinit var loadingProgressDialog: LoadingProgressDialog
    private lateinit var matchDTO: MatchDTO
    private lateinit var nickName: String
    private lateinit var matchMyPlayerAdapter: MatchPlayerAdapter
    private lateinit var matchOpponentPlayerAdapter: MatchPlayerAdapter
    private lateinit var matchMyPlayerDTOList: ArrayList<MatchPlayerDTO>
    private lateinit var matchOpponentPlayerDTOList: ArrayList<MatchPlayerDTO>
//    private lateinit var spidDTOList: ArrayList<SpidDTO>
//    private lateinit var sppositionDTOList: ArrayList<SppositionDTO>


    private val viewModel: MatchDetailViewModel by viewModels()

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
//        로딩 프로그래서 시작
//        바꾸는중
        loadingProgressDialog = LoadingProgressDialog(this)
        loadingProgressDialog.show()

        viewModel.getPreData()

        //화면이 보여질때 가져올 데이터 intent
        val intent = intent
        intent.getSerializableExtra("MatchDTO")?.let {
            matchDTO = it as MatchDTO
        }
        intent.getStringExtra("NickName")?.let {
            nickName = it
        }

        //유저 이름 및 점수를 화면에 설정
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

        //승패에 따른 화면 색깔 지정
        matchDetailResultViewColor(matchDTO.matchInfo[0], binding.linearMy, this)
        matchDetailResultViewColor(matchDTO.matchInfo[1], binding.linearOpponent, this)

        //아래 상세 데이터 설정
        binding.tvAverageRating.text = getString(
            R.string.MatchDetailActivity_detail_data_form,
            averageRating(matchDTO.matchInfo[0]),
            averageRating(matchDTO.matchInfo[1])
        )
        binding.tvShooting.text = getString(
            R.string.MatchDetailActivity_detail_data_form,
            matchDTO.matchInfo[0].shoot.shootTotal.toString(),
            matchDTO.matchInfo[1].shoot.shootTotal.toString()
        )
        binding.tvEffectiveShootingRating.text = getString(
            R.string.MatchDetailActivity_detail_data_form,
            matchDTO.matchInfo[0].shoot.effectiveShootTotal.toString(),
            matchDTO.matchInfo[1].shoot.effectiveShootTotal.toString()
        )
        binding.tvShare.text = getString(
            R.string.MatchDetailActivity_detail_data_form,
            matchDTO.matchInfo[0].matchDetail.possession.toString(),
            matchDTO.matchInfo[1].matchDetail.possession.toString()
        )
        binding.tvPassSuccessRate.text = getString(
            R.string.MatchDetailActivity_detail_data_form,
            passSuccesRate(matchDTO.matchInfo[0]),
            passSuccesRate(matchDTO.matchInfo[1])
        )
        binding.tvCornerKick.text = getString(
            R.string.MatchDetailActivity_detail_data_form,
            matchDTO.matchInfo[0].matchDetail.cornerKick.toString(),
            matchDTO.matchInfo[1].matchDetail.cornerKick.toString()
        )
        binding.tvTackle.text = getString(
            R.string.MatchDetailActivity_detail_data_form,
            matchDTO.matchInfo[0].defence.tackleSuccess.toString(),
            matchDTO.matchInfo[1].defence.tackleSuccess.toString()
        )
        binding.tvBlock.text = getString(
            R.string.MatchDetailActivity_detail_data_form,
            matchDTO.matchInfo[0].defence.blockSuccess.toString(),
            matchDTO.matchInfo[1].defence.blockSuccess.toString()
        )


        viewModel.setPlayer(matchDTO)

        matchMyPlayerDTOList = viewModel.filtedMatchMyPlayerDTOList
        matchOpponentPlayerDTOList = viewModel.filtedMatchOpponentPlayerDTOList

//        어댑터 세팅2개 각각의 어댑터
        //이미지 test 바꾸기 전
        setMatchMyPlayerAdapter(matchMyPlayerDTOList)
        setMatchOpponentPlayerAdapter(matchOpponentPlayerDTOList)
        //이미지 test 바꾸기 전

//        로딩 프로그래스 다이얼로그 종료
        loadingProgressDialog.dismiss()

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
        matchMyPlayerAdapter = MatchPlayerAdapter(this, matchPlayerDTOList)
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
        matchOpponentPlayerAdapter = MatchPlayerAdapter(this, matchPlayerDTOList)
        val searchLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchLinearLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rvOpponent.apply {
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

    /**
     * 평균 평점 구하기
     */
    private fun averageRating(matchInfoDTO: MatchInfoDTO): String {
        var spRatingSum = 0.0
        var count = 0.0
        matchInfoDTO.player.forEach {
            spRatingSum += it.status.spRating

            if (it.status.spRating != 0f) {
                count++
            }
        }
        val averageSpRating = if ((spRatingSum / count).isNaN()) 0.0 else spRatingSum / count

        return String.format("%.1f", averageSpRating)
    }

    /**
     * 패스 성공률
     */
    private fun passSuccesRate(matchInfoDTO: MatchInfoDTO): String {

        val passTry = matchInfoDTO.pass.passTry.toDouble()
        val passSuccess = matchInfoDTO.pass.passSuccess.toDouble()
        val passSuccesRate =
            if ((passSuccess / passTry * 100).isNaN()) 0.0 else passSuccess / passTry * 100

        return String.format("%.0f", passSuccesRate)
    }
}