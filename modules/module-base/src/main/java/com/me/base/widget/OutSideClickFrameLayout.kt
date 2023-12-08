package com.me.base.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class OutSideClickFrameLayout : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, def: Int) : super(context, attrs, def)
    private val rect = Rect()

    private  var mOutSideClickCallback:(()->Unit)?=null
    fun setOutSideClickCallback(l:()->Unit){
        mOutSideClickCallback = l
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val result =checkIsOutSide(event?.x?:0f,event?.y?:0f)
        when(event?.action){
            MotionEvent.ACTION_MOVE->mOutSideClickCallback?.invoke()
            MotionEvent.ACTION_UP->mOutSideClickCallback?.invoke()
        }
        return !result
    }
    private fun checkIsOutSide(x:Float,y:Float):Boolean{
        getChildAt(0)?.let {
            it.getGlobalVisibleRect(rect)
            return rect.contains(x.toInt(),y.toInt())
        }
        return false
    }
}