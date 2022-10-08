package com.movies.themoviestestapp.utils.customviews

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.movies.themoviestestapp.R
import com.movies.themoviestestapp.databinding.CustomDialogProgressbarCircleBinding


class LoadingDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {

    private lateinit var binding: CustomDialogProgressbarCircleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = CustomDialogProgressbarCircleBinding.inflate(inflater)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)

        window?.setGravity(Gravity.CENTER)
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        setCancelable(false)

        window?.setDimAmount(0.1f)
    }

    companion object {
        fun newInstance(
            context: Context
        ) = LoadingDialog(context)
    }
}