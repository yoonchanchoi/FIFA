package com.football.view.fifa.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.football.view.fifa.R
import com.football.view.fifa.databinding.FragmentPlayerDetailDialogBinding
import com.football.view.fifa.network.models.dto.MatchPlayerResult
import com.football.view.fifa.network.models.dto.SeasonIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import com.football.view.fifa.util.Constants
import com.football.view.fifa.util.Pref
import com.football.view.fifa.util.customGetSerializable
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayerDetailDialogFragment : DialogFragment() {

    @Inject
    lateinit var pref: Pref

    private lateinit var binding: FragmentPlayerDetailDialogBinding
    private lateinit var matchPlayDTO: MatchPlayerResult
    private lateinit var seasonIdDTOList: ArrayList<SeasonIdResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerDetailDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        arguments?.let {
            matchPlayDTO = it.customGetSerializable(
                Constants.BUNDLE_NAME_MATCHPLAYDTO,
                MatchPlayerResult::class.java
            )!!
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }


    private fun initData() {

        Glide.with(this)
            .load(String.format(Constants.IMAGE_BASE_URL, matchPlayDTO.spId))
            .error(R.drawable.null_player1)
            .placeholder(R.drawable.loading_player)
            .into(binding.ivPlayer)

        // 에러 났을때 이미지, 이미지 로딩되기 전의 이미지 설정 하기 찾아보기
        Glide.with(this)
            .load(findSeasonId())
            .error(R.drawable.red_cross_image)
            .placeholder(R.drawable.white_image)
            .into(binding.ivSpSeason)

        binding.tvPlayerName.text = matchPlayDTO.spName
        binding.tvSpGrade.text = matchPlayDTO.spGrade.toString()
        binding.tvSpPosition.text = matchPlayDTO.spDesc
        binding.tvAverageRating.text = matchPlayDTO.status.spRating.toString()
        binding.tvScore.text = matchPlayDTO.status.goal.toString()
        binding.tvAssist.text = matchPlayDTO.status.assist.toString()
        binding.tvPassSuccessRate.text = setPassSuccesRate()
        binding.tvShoot.text = matchPlayDTO.status.shoot.toString()
        binding.tvEffectiveShot.text = matchPlayDTO.status.effectiveShoot.toString()

    }

    private fun findSeasonId(): String {
        var seasonImg = ""
        val seasonId = matchPlayDTO.spId.toString().substring(0 until 3)
        seasonIdDTOList = pref.getAllSeasonIdList() as ArrayList<SeasonIdResult>
        seasonIdDTOList.forEach {
            if (it.seasonId.toString() == seasonId) {
                seasonImg = it.seasonImg
            }
        }
        return seasonImg
    }

    private fun setPassSuccesRate(): String {
        val passSuccess = matchPlayDTO.status.passSuccess.toDouble()
        val passTry = matchPlayDTO.status.passTry.toDouble()
        val tempDouble = (passSuccess / passTry * 100)
        return String.format("%.0f", tempDouble) + "%"
    }
}