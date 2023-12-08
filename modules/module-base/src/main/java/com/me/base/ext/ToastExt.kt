package com.me.base.ext

import android.app.Activity
import android.app.Dialog
import android.text.TextUtils
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.me.base.ui.AppManager
import com.me.base.ui.toast.AlertToast
import com.me.base.viewmodel.BaseViewModel


enum class ToastType{
    SUCCESS,Error,WARN
}

fun showToastTips(str: String,dialog:Dialog,type:ToastType){
    if (TextUtils.isEmpty(str))return
    AlertToast.create(dialog,type,str)
}

fun showToastTips(@StringRes strRes: Int, dialog:Dialog, type:ToastType){
    AlertToast.create(dialog,type, str(strRes))
}

fun showToastTips(str: String, activity:Activity?= AppManager.currentActivity(), type:ToastType){
    if (TextUtils.isEmpty(str))return
    AlertToast.create(activity,type,str)
}

fun showToastTips(@StringRes strRes: Int, activity: Activity, type:ToastType){
    AlertToast.create(activity,type, str(strRes))
}

fun showToastTips(str: String,fragment:Fragment,type:ToastType){
    if (TextUtils.isEmpty(str))return
    AlertToast.create(fragment,type,str)
}

fun showToastTips(@StringRes strRes: Int, fragment: Fragment, type:ToastType){
    AlertToast.create(fragment,type, str(strRes))
}

fun BaseViewModel.showToast(@StringRes strRes:Int,type: ToastType = ToastType.WARN){
    ui?.getWindowContainer()?.let {
        when(it){
            is Activity-> showToastTips(str(strRes),it,type)
            is Fragment-> showToastTips(str(strRes),it,type)
            is Dialog-> showToastTips(str(strRes),it,type)
        }
    }
}

fun BaseViewModel.showToast(str:String?, type: ToastType = ToastType.WARN){
    if (TextUtils.isEmpty(str))return
    ui?.getWindowContainer()?.let {
        when(it){
            is Activity-> showToastTips(str?:"",it,type)
            is Fragment-> showToastTips(str?:"",it,type)
            is Dialog-> showToastTips(str?:"",it,type)
        }
    }
}
