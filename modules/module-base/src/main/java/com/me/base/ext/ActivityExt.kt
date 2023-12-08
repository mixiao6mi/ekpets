package com.me.base.ext

import android.app.Activity
import android.app.Dialog
import android.util.DisplayMetrics
import android.view.View
import android.widget.PopupWindow
import androidx.fragment.app.DialogFragment

fun Activity.initFontScale() {
    val configuration = resources.configuration
     configuration.fontScale = 1f
     val metrics = DisplayMetrics()
     windowManager.defaultDisplay.getMetrics(metrics)
     metrics.scaledDensity = configuration.fontScale*metrics.density
     resources.updateConfiguration(configuration,metrics)
}

/**
 * 隐藏系统状态来
 */
fun Activity.hideNavigation(){
 window.decorView.setSystemUiVisibility(
  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
          or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
          or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
          or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
          or View.SYSTEM_UI_FLAG_FULLSCREEN
          or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
 )
}


fun Dialog.hideNavigation(){
 window?.decorView?.setSystemUiVisibility(
  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
          or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
          or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
          or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
          or View.SYSTEM_UI_FLAG_FULLSCREEN
          or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
 )
}


fun DialogFragment.hideNavigation(){
 dialog?.window?.decorView?.setSystemUiVisibility(
           View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
          or View.SYSTEM_UI_FLAG_FULLSCREEN
                   or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
 )
}

fun PopupWindow.hideNavigation(){
 contentView?.setSystemUiVisibility(
  View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
          or View.SYSTEM_UI_FLAG_FULLSCREEN
          or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
 )
}