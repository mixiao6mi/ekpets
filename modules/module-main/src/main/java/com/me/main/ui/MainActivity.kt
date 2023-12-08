package com.me.main.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.me.base.ext.ToastType
import com.me.base.ext.appExit
import com.me.base.ext.showToastTips
import com.me.base.ui.activity.VMBaseActivity
import com.me.main.BR
import com.me.main.R
import com.me.main.databinding.ActivityMainBinding
import com.me.common.route.ACTIVITY_MAIN

@Route(path = ACTIVITY_MAIN)
class MainActivity : VMBaseActivity<ActivityMainBinding, MainViewModel>() {
    private var mExitTime = 0L
    private var delayTime = 600L
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getViewModelId(): Int = BR.viewModel
    override fun onBackPressed() {
        if (System.currentTimeMillis()-mExitTime>2000){
            showToastTips(R.string.tips_press_again,this,ToastType.WARN)
            mExitTime = System.currentTimeMillis()
        }else{
            appExit()
        }
    }
}