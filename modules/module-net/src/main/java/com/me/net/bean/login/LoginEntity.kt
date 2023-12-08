package com.me.net.bean.login

import com.me.net.user.UserData

data class LoginEntity( val tokenExpireIn:Long?,
                        val refreshToken:String?,
                        val accessToken:String?,
                        val useInfo: UserData?)
