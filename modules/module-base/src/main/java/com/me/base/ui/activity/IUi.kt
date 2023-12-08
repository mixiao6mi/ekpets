package com.me.base.ui.activity

interface IUi:ILoading {
    /**
     * - Dialog dismiss
     * - Activity/Fragment finish
     */
    fun  finishUI()
    fun  getWindowContainer():Any?{
        return null
    }
}