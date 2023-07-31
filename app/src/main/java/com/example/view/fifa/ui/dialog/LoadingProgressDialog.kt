package com.example.view.fifa.ui.dialog

import android.R
import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import androidx.core.content.ContextCompat
import com.example.view.fifa.databinding.DialogProgressbarBinding

class LoadingProgressDialog(context: Context) : Dialog(context) {

    private var binding: DialogProgressbarBinding = DialogProgressbarBinding.inflate(layoutInflater)

    init {
        // 다이얼 로그 제목을 안보이게...

        binding.pb.isIndeterminate = true
//        binding.pb.indeterminateDrawable.setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.MULTIPLY)
        val color = ContextCompat.getColor(context, com.example.view.fifa.R.color.white)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.pb.indeterminateDrawable.colorFilter = BlendModeColorFilter((color), BlendMode.SRC_ATOP)
        }else {
            binding.pb.indeterminateDrawable.setColorFilter(
                color,
                PorterDuff.Mode.SRC_IN
            )
        }
        this.setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

    }

}