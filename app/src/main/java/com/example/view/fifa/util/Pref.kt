package com.example.view.fifa.util

import android.content.SharedPreferences
import com.example.view.fifa.network.models.dto.UserDTO
import com.google.gson.Gson
import javax.inject.Inject

class Pref @Inject constructor(private val pref: SharedPreferences) {
//    private val pref = PreferenceManager.getDefaultSharedPreferences(context)
    //commit() 동기 처리
    //apply() 비동기 처리, 구글 권장
    //확장함수 사용 방법


    fun saveSearchList(searchList: MutableList<UserDTO>) {
        val searchListString: String = Gson().toJson(searchList)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(Constants.PREF_KEY_SEARCH, searchListString)
        editor.apply()
    }

    fun getSearchList(): MutableList<UserDTO> {
        var saveSearchList = ArrayList<UserDTO>()
        pref.getString(Constants.PREF_KEY_SEARCH, "")?.let {
            if (it.isNotEmpty()) {
                saveSearchList = Gson().fromJson(it, Array<UserDTO>::class.java)
                    .toMutableList() as ArrayList<UserDTO>
            }
        }
        return saveSearchList

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