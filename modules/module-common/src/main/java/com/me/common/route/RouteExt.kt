package com.me.common.route

import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.me.base.R

/**
 * ARouter 跳转页面路由方法
 * @param path 目标页面路径
 * @param context
 * @param bundle 跳转页面需要传递的数据
 */
fun goPath(path:String,context:Context,bundle:Bundle?=null){
    ARouter.getInstance()
        .build(path)
        .with(bundle)
        .withTransition(R.anim.activity_in,R.anim.activity_out)
        .navigation(context)
}