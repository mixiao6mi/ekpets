@file:JvmName("AppExt")
package com.me.base.ext

import android.app.ActivityManager
import android.content.Context
import com.me.base.app.App
import android.os.Process
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

val currentActivity get()=App.instance.currentActivity
val appCxt:Context get() = App.instance

/**
 * 当前进程名
 */
fun  getCurrentProcessName():String?{
    val pid = Process.myPid()
    val am = appCxt.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    am.runningAppProcesses?.let {
        for (p in  it){
            if (p.pid == pid){
                return  p.processName
            }
        }
    }
    return null
}
fun appExit(){
    try {
        Process.killProcess(Process.myPid())
    }catch (e:java.lang.Exception){
        e.printStackTrace()
    }
}
val mainScope = MainScope()
fun  launch(block:suspend  CoroutineScope.() -> Unit)= mainScope.launch ( block=block )