package com.football.view.fifa.util

import android.content.SharedPreferences
import com.football.view.fifa.network.models.dto.SeasonIdResult
import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import com.football.view.fifa.network.models.dto.UserInfoResult
import com.google.gson.Gson
import javax.inject.Inject

class Pref @Inject constructor(private val pref: SharedPreferences) {
//    private val pref = PreferenceManager.getDefaultSharedPreferences(context)
    //commit() 동기 처리
    //apply() 비동기 처리, 구글 권장
    //확장함수 사용 방법

    fun saveSearchList(searchList: MutableList<UserInfoResult>) {
        val searchListString: String = Gson().toJson(searchList)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(Constants.PREF_KEY_SEARCH, searchListString)
        editor.apply()
    }

    fun getSearchList(): MutableList<UserInfoResult> {
        var saveSearchList = ArrayList<UserInfoResult>()
        pref.getString(Constants.PREF_KEY_SEARCH, "")?.let {
            if (it.isNotEmpty()) {
                saveSearchList = Gson().fromJson(it, Array<UserInfoResult>::class.java)
                    .toMutableList() as ArrayList<UserInfoResult>
            }
        }
        return saveSearchList
    }

    fun saveAllSpidList(allSpidList: MutableList<SpIdResult>){
        val allSpidListString: String = Gson().toJson(allSpidList)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(Constants.PREF_KEY_ALL_SPID, allSpidListString)
        editor.apply()
    }

    fun getAllSpidList(): MutableList<SpIdResult> {
        var saveAllSpidList = ArrayList<SpIdResult>()
        pref.getString(Constants.PREF_KEY_ALL_SPID, "")?.let {
            if (it.isNotEmpty()) {
                saveAllSpidList = Gson().fromJson(it, Array<SpIdResult>::class.java)
                    .toMutableList() as ArrayList<SpIdResult>
            }
        }
        return saveAllSpidList
    }

    //여기 DTO 명칭 수정
    fun saveAllSppositionList(allSppositionDTOList: MutableList<SpPositionResult>){
        val allSppositionListString: String = Gson().toJson(allSppositionDTOList)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(Constants.PREF_KEY_ALL_SPPOSITION, allSppositionListString)
        editor.apply()
    }

    fun getAllSppositionList(): MutableList<SpPositionResult> {
        var saveAllSppositionList = ArrayList<SpPositionResult>()
        pref.getString(Constants.PREF_KEY_ALL_SPPOSITION, "")?.let {
            if (it.isNotEmpty()) {
                saveAllSppositionList = Gson().fromJson(it, Array<SpPositionResult>::class.java)
                    .toMutableList() as ArrayList<SpPositionResult>
            }
        }
        return saveAllSppositionList
    }

    fun saveAllSeasonIdList(allSeasonIdList: MutableList<SeasonIdResult>){
        val allSeasonIdListString: String = Gson().toJson(allSeasonIdList)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(Constants.PREF_KEY_ALL_SEASONID, allSeasonIdListString)
        editor.apply()
    }

    fun getAllSeasonIdList(): MutableList<SeasonIdResult> {
        var saveAllSeasonIdList = ArrayList<SeasonIdResult>()
        pref.getString(Constants.PREF_KEY_ALL_SEASONID, "")?.let {
            if (it.isNotEmpty()) {
                saveAllSeasonIdList = Gson().fromJson(it, Array<SeasonIdResult>::class.java)
                    .toMutableList() as ArrayList<SeasonIdResult>
            }
        }
        return saveAllSeasonIdList
    }

    fun putData(key: String, value: Any) {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
        }
    }

    private fun putString(key: String, value: String) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun putInt(key: String, value: Int) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun putLong(key: String, value: Long) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    private fun putBoolean(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun putFloat(key: String, value: Float) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return pref.getString(key, "")
    }

    fun getInt(key: String): Int {
        return pref.getInt(key, 0)
    }

    fun getLong(key: String): Long {
        return pref.getLong(key, 0)
    }

    fun getBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }

    fun getFloat(key: String): Float {
        return pref.getFloat(key, 0.0f)
    }

    fun removeKey(key: String) {
        pref.edit().remove(key).apply()
    }

    fun clear() {
        pref.edit().clear().apply()
    }
}