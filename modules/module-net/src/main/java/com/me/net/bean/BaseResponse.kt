package com.me.net.bean

import com.me.net.const.CODE_SUCCESS


data class BaseResponse<Data> (val code:Int?,val message:String?,val data:Data?){

    /**
     * 请求是否成功 ;
     */
    fun isSuccess() = code == CODE_SUCCESS
}