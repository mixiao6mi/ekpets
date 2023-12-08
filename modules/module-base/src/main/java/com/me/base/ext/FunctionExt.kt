package com.me.base.ext

typealias VoidCallback = ()->Unit


inline fun voidCallback(callback: VoidCallback){
    callback()
}


typealias BoolFunctionCallback = ()->Boolean



