package com.me.base.ui.dialog

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.me.base.R
import com.me.base.ext.dp2px

/**
 * 全局的加载进度框
 */
class LoadingDialog(private  val context:Context) {
    private  val mSimpleDialog:SimpleDialog
    init {
        val layout=LayoutInflater.from(context).inflate(R.layout.dialog_loading,null,false)
        layout.layoutParams=ViewGroup.LayoutParams(dp2px(120), dp2px(120))
        mSimpleDialog = SimpleDialog.Builder(context)
            .setGravity(Gravity.CENTER)
            .hasFrame(false)
            .setCancelable(false)
            .setCanceledOnTouchOutside(false)
            .setLayoutView(layout)
            .build()


    }
    fun  isShowing()=mSimpleDialog.isShowing
    fun  show()=mSimpleDialog.show()
    fun  dismiss()=mSimpleDialog.dismiss()
}