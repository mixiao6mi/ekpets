package com.me.base.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.me.base.BuildConfig
import com.me.base.ext.getCurrentProcessName
import com.me.base.ui.AppManager
import com.me.base.widget.HomeRefreshHeadViewWhite
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import timber.log.Timber

open class App : Application(), Application.ActivityLifecycleCallbacks {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: App

        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
                HomeRefreshHeadViewWhite(context)
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
                ClassicsFooter(context)
            }
        }
    }

    /**
     * 当前处于前台的Activity
     */
    var currentActivity: Activity? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        if (packageName==getCurrentProcessName()){
            //AppManager工具初始化
            AppManager.init(this)
            //Log初始化
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                }
            })
            registerActivityLifecycleCallbacks(this)
            init()
        }
    }

    /**
     * 初始化
     */
    protected  fun init(){
        val map = mapOf<String,Any>(
            TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER to true,
            TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE to true,
        )
        QbSdk.initTbsSettings(map)

        MMKV.initialize(this)
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
        QbSdk.initX5Environment(this,object : QbSdk.PreInitCallback{
            override fun onCoreInitFinished() {
            }

            override fun onViewInitFinished(p0: Boolean) {
                Logger.d("Core init finish : $p0")
            }

        })
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentActivity = activity
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity =activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity =activity
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity == currentActivity) {
            currentActivity = null
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level== TRIM_MEMORY_UI_HIDDEN){
            Glide.get(this).clearMemory()
        }
        Glide.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }
}