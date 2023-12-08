package com.me.net.api

import com.alibaba.fastjson.JSONObject
import com.me.net.bean.BaseResponse
import com.me.net.bean.login.ForgetPwdBean
import com.me.net.bean.login.ImageCaptcha
import com.me.net.bean.login.LoginEntity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 用户相关接口API
 */
interface LoginApi {
    @POST("/api/auth/signup")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") username:String?=null,
        @Field("password") password: String? = null,
        @Field("rePassword") rePassword: String? = null,
        @Field("dialCode") areaCode: String? = null,
        @Field("phone") phone: String? = null,
        @Field("smsCode") validCode: String? = null,
        @Field("intrCode") recommendCode: String? = null,
        @Field("playerRecommendCode") proxyCode: String? = null,
        @Field("realName") realName: String? = null,
        @Field("email") email: String? = null,
        @Field("wechat") weChat: String? = null,
        @Field("qq") qq: String? = null,
        @Field("captchaKey") captchaKey: String? = null,
        @Field("code") imageCode: String? = null,
        @Field("emailCode")emailCode:String? = null
    )

    @POST("/api/auth/sign_in")
    @FormUrlEncoded
    suspend fun loginWithAccount(
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("phone") phone: String? = null,
        @Field("realName") realName: String? = null,
        @Field("qq") qq: String? = null,
        @Field("email") email: String? = null,
        @Field("cardNo") cardNo: String? = null,
        @Field("wechat") wechat: String? = null,
        @Field("captchaKey") captchaKey: String? = null,
        @Field("code") imageCode: String? = null
    ): BaseResponse<LoginEntity>

    @POST("/api/auth/mobile/sign_in")
    @FormUrlEncoded
    suspend fun loginByMobile(
        @Field("phone") mobile: String?,
        @Field("smsCode") code: String?,
        @Field("dialCode") telArea: String?,
        @Field("captchaKey") captchaKey: String?,
        @Field("code") imageCode: String?,
    ): BaseResponse<LoginEntity>

    @POST("/api/auth/logout")
    suspend fun logout(): BaseResponse<Any>

    @GET("/api/captcha/code")
    suspend fun getCaptchaCode(
        @Query("name") name: String = "image",
        @Query("clientType") clientType: String = "android"
    ): BaseResponse<ImageCaptcha>

    /**
     * 获取手机短信验证码
     */
    @FormUrlEncoded
    @POST("/api/sms/send")
    suspend fun sendSmsCode(@Field("type") type: String,
                            @Field("dialCode") telArea: String,
                            @Field("phone") mobile: String):BaseResponse<Any>
    @POST("/api/email/send")
    @FormUrlEncoded
    suspend fun sendEmailCode(@Field("emailAccount") emailAccount:String) :BaseResponse<Any>

    @POST("/api/auth/forget/reset_password")
    @FormUrlEncoded
    suspend fun modifyPassword(@Field("username") username: String?,@Field("newPassword") newPassword: String?,
                               @Field("code") code: String?,@Field("dialCode") dialCode: String?,
                               @Field("phone") phone: String?
    ):BaseResponse<Any>
    /**
     * 忘记密码 第一步  根据用户名获取用户绑定的手机号
     */
    @GET("/api/auth/forget/query_mobile_phone")
    suspend fun getBindUserInfo(@Query("username") username: String?):BaseResponse<ForgetPwdBean>
}