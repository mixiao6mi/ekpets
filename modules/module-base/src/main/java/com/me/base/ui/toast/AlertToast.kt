package com.me.base.ui.toast

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.me.base.R
import com.me.base.ext.ToastType
import com.tapadoo.alerter.Alerter

object AlertToast {
    const val TIME_SHOW_TOAST = 2000L

    fun create(context: Any?,type:ToastType,str:String){
        var alerter: Alerter?=null
        when(context){
            is Activity -> alerter = Alerter.create(context, R.layout.view_toast)
            is Fragment -> alerter = Alerter.create(context.requireActivity(), R.layout.view_toast)
            is Dialog -> alerter = Alerter.create(context, R.layout.view_toast)
        }

        alerter?.apply {
            setBackgroundColorInt(Color.TRANSPARENT)
            enableClickAnimation(false)
            setDuration(TIME_SHOW_TOAST)
            getLayoutContainer()?.let {
                val ivIcon = it.findViewById<ImageView>(R.id.iv_icon)
                val tvCustomView = it.findViewById<TextView>(R.id.tv_text)
                when (type) {
                    ToastType.SUCCESS -> {
                        ivIcon?.setImageResource(R.drawable.ic_icon_succeed_toast)
                    }
                    ToastType.Error -> {
                        ivIcon?.setImageResource(R.drawable.ic_icon_error_toast)
                    }
                    ToastType.WARN -> {
                        ivIcon?.setImageResource(R.drawable.ic_warn)
                    }
                }
                tvCustomView.text = str
            }
            show()
        }

    }
}