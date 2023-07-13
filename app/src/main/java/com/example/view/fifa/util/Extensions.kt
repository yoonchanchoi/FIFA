package com.example.searchstudy.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

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


