package com.example.view.fifa.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

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


