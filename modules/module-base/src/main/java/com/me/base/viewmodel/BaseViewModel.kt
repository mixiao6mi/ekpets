package com.me.base.viewmodel

import androidx.lifecycle.*
import com.me.base.ui.activity.IUi

open class BaseViewModel : ViewModel(), LifecycleEventObserver, LifecycleOwner {

    var ui: IUi? = null

    fun attachUi(ui: IUi) {
        this.ui = ui
    }


    fun detachUi() {
        ui = null
    }

    private var mLifecycleRegistry: LifecycleRegistry? = null

    override fun getLifecycle(): LifecycleRegistry {

        if (mLifecycleRegistry == null) {
            mLifecycleRegistry = LifecycleRegistry(this)
        }
        return mLifecycleRegistry as LifecycleRegistry
    }

    /**
     * 是否注册给，lifecyclerOwner，默认为true，不需要监听生命周期，重写返回false
     * @return
     */
    open fun needObserver(): Boolean {
        return true
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        // 不需要生命周期 直接返回
        if (!needObserver()) return
        when (event) {
            Lifecycle.Event.ON_CREATE -> onCreate()
            Lifecycle.Event.ON_START -> onStart()
            Lifecycle.Event.ON_RESUME -> onResume()
            Lifecycle.Event.ON_PAUSE -> onPause()
            Lifecycle.Event.ON_STOP -> onStop()
            Lifecycle.Event.ON_DESTROY -> onDestroy()
            else -> {}
        }
        // 事件转发，具有LifecycleOwner能力
        lifecycle.handleLifecycleEvent(event)
    }

    open fun onCreate() {}
    open fun onStart() {}
    open fun onResume() {}
    open fun onPause() {}
    open fun onStop() {}
    open fun onDestroy() {}
}