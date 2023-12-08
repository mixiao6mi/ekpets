package com.me.launch.ui

import android.animation.Animator
import android.animation.ValueAnimator
import androidx.databinding.ObservableInt
import androidx.lifecycle.viewModelScope
import com.me.base.ext.currentActivity
import com.me.base.viewmodel.BaseViewModel
import com.me.common.route.ACTIVITY_MAIN
import com.me.common.route.goPath
import kotlinx.coroutines.launch

class LaunchViewModel : BaseViewModel() {
    companion object {
        const val TIME_PROCESS = 3000L
    }

    val animator = ValueAnimator.ofInt(0, 99)
    val processField = ObservableInt()

    //动画是否播放完成
    private var animatorFinished: Boolean = false

    //动画是否播放完成
    private var preloadingFinished: Boolean = true

    fun initAnimator() {
        animator.duration = TIME_PROCESS
        animator.addUpdateListener {
            processField.set((it.animatedValue as Int))
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                animatorFinished = true
                viewModelScope.launch { checkStatus() }
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
        animator.start()
    }

    private fun checkStatus() {
        if (animatorFinished && preloadingFinished) {
            processField.set(100)
            goMain()
        }
    }

    private fun goMain() {
        currentActivity?.let {
            goPath(ACTIVITY_MAIN, it, null)
            ui?.finishUI()
        }
    }

}