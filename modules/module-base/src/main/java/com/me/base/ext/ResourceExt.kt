package com.me.base.ext

import android.R
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.me.base.ui.AppManager

/**
 * 获取字符串
 */
fun str(@StringRes strRes: Int) = appCxt.getString(strRes)


/**
 * 获取颜色
 */
fun color(@ColorRes colorRes: Int) = appCxt.resources.getColor(colorRes)


fun sp2px(sp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp.toFloat(),
        appCxt.resources.displayMetrics
    ).toInt()
}

fun dp2px(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        appCxt.resources.displayMetrics
    ).toInt()
}

fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        appCxt.resources.displayMetrics
    )
}

fun px2dp(px: Float): Float {
    return px / (appCxt.resources
        .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}


fun px2sp(value: Float): Float {
    val metrics: DisplayMetrics = appCxt.resources.displayMetrics
    return value / metrics.scaledDensity
}

/**
 * 获取屏幕dip宽高 ;
 */
val screenWidthDp get() = appCxt.resources.configuration.screenWidthDp
val screenHeightDp get() = appCxt.resources.configuration.screenHeightDp

/**
 * 获取屏幕像素宽高 ;
 */
val screenWidthPx get() = appCxt.resources.displayMetrics.widthPixels
val screenHeightPx get() = appCxt.resources.displayMetrics.heightPixels

/**
 * 获取状态栏像素高度 ;
 */
fun getStatusBarHeight(): Int = appCxt.resources.getDimensionPixelSize(
    appCxt.resources.getIdentifier(
        "status_bar_height",
        "dimen",
        "android"
    )
)

/** Int 转PX */
fun Int.toPX(): Int = (this * getDisplayMetrics().density + 0.5f).toInt()

/** Int转sp */
fun Int.toSP(): Int = (this * getDisplayMetrics().scaledDensity).toInt()

/** Float转PX */
fun Float.toPX(): Float = this * getDisplayMetrics().density + 0.5f

/** Float转sp */
fun Float.toSP(): Float = this * getDisplayMetrics().scaledDensity

/** Double转PX */
fun Double.toPX(): Double = this * getDisplayMetrics().density + 0.5f

/** Double转sp */
fun Double.toSP(): Double = this * getDisplayMetrics().scaledDensity

/** 获取 displayMetrics */
fun getDisplayMetrics(): DisplayMetrics = appCxt.resources.displayMetrics
fun getResDrawable(@DrawableRes idRes: Int): Drawable =
    AppManager.getTopActivityOrApp().getResDrawable(idRes)
/**
 * 获取Drawable
 */
fun Context.getResDrawable(@DrawableRes resId: Int): Drawable =
    ContextCompat.getDrawable(this, resId)
        ?: ColorDrawable(color(R.color.transparent))

fun View.getResColor(@ColorRes resId: Int): Int =
    color(resId)
fun ViewModel.getResColor(@ColorRes resId: Int): Int =
    color(resId)