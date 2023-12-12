package com.football.view.fifa.util

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.io.Serializable

//* 확장함수를 이용한 editext의 TextChanged의 각각의 함수 override 측 3개 다쓰지 않고도 한개만 가능

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.onTextChanged(onTextChanged: (s:CharSequence, start: Int, before: Int, after: Int) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(editable: Editable?) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(s,start,before,count)
        }
    })
}

fun EditText.beforeTextChanged(onTextChanged: (s:CharSequence, start: Int, count: Int, after: Int) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(editable: Editable?) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            onTextChanged.invoke(s,start,count,after)
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }
    })
}

@Suppress("DEPRECATION")
fun <T: Serializable> Bundle.customGetSerializable(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializable(key, clazz)
    } else {
        this.getSerializable(key) as T?
    }
}

//@Suppress("DEPRECATION")
//inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
//    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//        getSerializable(key, T::class.java)
//    } else {
//        getSerializable(key) as? T
//    }
//}




