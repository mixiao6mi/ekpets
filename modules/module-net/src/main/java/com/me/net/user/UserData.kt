package com.me.net.user

import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import com.me.base.ext.str
import com.me.base.R

data class UserData( val account:String?,
                     val userType:String?,
                     @SerializedName("nickname")
                     var nickName:String?,
                     var realName:String?,
                     var dialCode:String?,
                     var phone:String?,
                     val qq:String?,
                     val email:String?,
                     val wechat:String?,
                     var birthday:String?,
                     var sex:String?,
                     val registerTime:String?,
                     val vipLevel:Int?,
                     val vipGrowth:String?,
                     var cashValidate:String?,
                     var hasWithdrawPwd:Boolean?,
                     var avatar:String?,
                     val bankcardCount:Int?,
                     val virtualCurrencyCount:Int?,
){


    /**
     * 是否绑定了手机 ;
     */
    fun isBindPhone() = !TextUtils.isEmpty(phone)

    /**
     * 提现短信验证码是否开启 ;
     */
    fun isSmsWithdrawOpen() = cashValidate == "1"


    fun getReallyName() :String{
        var reallyName = StringBuffer()
        if (!realName.isNullOrEmpty()){
            realName!!.forEachIndexed { index, c ->
                if (index==0){
                    reallyName.append(c)
                }else if (index==1&&1 == realName!!.length-1){
                    reallyName.append("*")
                }else if (index == realName!!.length-1&&index!=1){
                    reallyName.append(c)
                }else{
                    reallyName.append("*")
                }

            }
        }

        return reallyName.toString()
    }


    fun getReallyPhone():String?{
        if (phone == null)return null
        if (phone!!.contains("*"))return phone
        val sb = StringBuffer()
        phone?.forEachIndexed { index, c ->
            if (index<2||index>6){
                sb.append(c)
            }else{
                sb.append("*")
            }
        }
        return sb.toString()
    }

    /**
     * 是否VIP用户
     */
    fun isAgentUser() = userType == "a" || userType=="A"


    /**
     * 性别选择框文案
     */
    fun sexString() = str(
        when(sex?.toIntOrNull()){
            1-> R.string.common_string_male
            2-> R.string.common_string_famale
            else->R.string.common_string_unknow
        }
    )

    fun birthdayString() =  if (TextUtils.isEmpty(birthday)) str(R.string.common_string_birthday) else birthday
}
