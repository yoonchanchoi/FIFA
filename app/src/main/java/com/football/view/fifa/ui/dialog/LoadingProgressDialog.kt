package com.football.view.fifa.ui.dialog

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
import com.football.view.fifa.databinding.DialogProgressbarBinding

class LoadingProgressDialog(context: Context) : Dialog(context) {

    private var binding: DialogProgressbarBinding = DialogProgressbarBinding.inflate(layoutInflater)

    init {
        binding.pb.isIndeterminate = true
        val color = ContextCompat.getColor(context, com.football.view.fifa.R.color.white)
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