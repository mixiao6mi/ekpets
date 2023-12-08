package com.me.base.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.StyleRes
import com.me.base.R
import com.me.base.ext.VoidCallback
import com.me.base.ext.currentActivity
import com.me.base.ui.activity.BaseActivity
import com.me.base.ui.activity.IUi
import com.me.base.widget.OutSideClickFrameLayout

class SimpleDialog (context: Context, private val params:Params): Dialog(context, if (params.hasFrame) R.style.dialogStyle else R.style.dialogStyle_No_Frame), IUi {


    override fun showLoadingDialog() {
        (currentActivity as? BaseActivity)?.showLoadingDialog()
    }

    override fun hideLoadingDialog() {
        (currentActivity as? BaseActivity)?.hideLoadingDialog()
    }

    override fun finishUI() {
        dismiss()
    }

    override fun getWindowContainer()  = this

    override fun onCreate(savedInstanceState: Bundle?) {
        //hideNavigation()
        super.onCreate(savedInstanceState)
        if (params.windowAnimationsStyleRes!=0){
            window?.setWindowAnimations(params.windowAnimationsStyleRes)
        }

        window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        window?.decorView?.setPadding(0,0,0,0)
        val param= window?.attributes
        param?.width = WindowManager.LayoutParams.MATCH_PARENT
        param?.height= WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes=param
        setContentView(R.layout.dialog_simple)

        var par = params.layoutView.layoutParams as? FrameLayout.LayoutParams

        if (par==null){
            par = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,if (params.isWindowHeightFullScreen) FrameLayout.LayoutParams.MATCH_PARENT else FrameLayout.LayoutParams.WRAP_CONTENT)
        }
        par.gravity = params.gravity
        params.layoutView.layoutParams = par
        findViewById<OutSideClickFrameLayout>(R.id.container).apply {
            addView(params.layoutView)

            setOutSideClickCallback {
                if (params.canceledOnTouchOutside){
                    if (isShowing) dismiss()
                }
            }
        }
    }


    override fun dismiss() {
        super.dismiss()
        params.dismissCallback?.invoke()
    }


    override fun show() {
        window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        super.show()
        val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        this.window?.decorView?.systemUiVisibility = uiOptions
        this.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
    }


    class Params{
        /**
         * 是否需要遮罩 ;
         */
        var hasFrame = true


        /**
         * 弹窗窗口显示消失动画
         */
        @StyleRes
        var windowAnimationsStyleRes:Int = 0


        /**
         * 内容宽度是否填充满屏幕
         */
        var isWindowWidthFullScreen = true


        /**
         * 内容高度是否填充满屏幕
         */
        var isWindowHeightFullScreen = false


        /**
         * 内容在窗口中的位置
         */
        var gravity:Int = Gravity.CENTER


        /**
         * 布局
         */
        lateinit var layoutView: View



        var canceledOnTouchOutside:Boolean = true


        var  cancelable = true


        var dismissCallback:VoidCallback?=null

    }



    class Builder(private val context: Context){
        private val params:Params = Params()


        fun hasFrame(hasFrame:Boolean = true):Builder{
            params.hasFrame = hasFrame
            return this
        }

        fun setWindowAnimationsStyleRes(@StyleRes windowAnimationsStyleRes:Int = R.style.dialog_window_anim_style):Builder{
            params.windowAnimationsStyleRes = windowAnimationsStyleRes
            return this
        }

        /**
         *   设置Dialog的Root View是否填充屏幕 ;
         */
        fun applyRootViewSize(isWindowWidthFullScreen:Boolean = true,
                              isWindowHeightFullScreen:Boolean = false):Builder{
            params.isWindowWidthFullScreen = isWindowWidthFullScreen
            params.isWindowHeightFullScreen = isWindowHeightFullScreen
            return this
        }



        fun setGravity(gravity: Int = Gravity.CENTER):Builder{
            params.gravity = gravity
            return this
        }

        fun setLayoutView(view: View):Builder{
            params.layoutView = view
            return this
        }


        fun setCanceledOnTouchOutside(can:Boolean = true):Builder{
            params.canceledOnTouchOutside = can
            return this
        }


        fun setCancelable(can:Boolean = true):Builder{
            params.cancelable = can
            return this
        }

        fun setDismissCallback(callback:VoidCallback):Builder{
            params.dismissCallback = callback
            return this
        }

        fun build():SimpleDialog{
            val dialog = SimpleDialog(context,params)
            return dialog
        }

    }

}