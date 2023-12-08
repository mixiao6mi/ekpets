package com.me.launch.ui

import com.me.base.ui.activity.VMBaseActivity
import com.me.launch.BR
import com.me.launch.R
import com.me.launch.databinding.ActivityLaunchBinding

class LaunchActivity : VMBaseActivity<ActivityLaunchBinding,LaunchViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_launch
    }

    override fun getViewModelId(): Int {
        return BR.viewModel
    }

    override fun init() {
        super.init()
        viewModel.initAnimator()

    }
}