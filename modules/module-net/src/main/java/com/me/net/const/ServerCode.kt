package com.me.net.const

const val KEY_CODE = "code"
const val KEY_MESSAGE = "message"

/**
 * 服务器返回数据成功 ;
 */
const val CODE_SUCCESS = 0


/**
 * 链接服务器异常 ;
 */
const val CODE_CONNECT_EXCEPTION = 10001

/**
 * 链接服务器超时 ;
 */
const val CODE_CONNECT_TIME_OUT = 10002

/**
 * 证书验证失败 ;
 */
const val CODE_SSL_ERROR = 10003

/**
 * 解析服务器数据失败 ;
 */
const val CODE_JSON_PARSE_EXCEPTION = 10004



const val CODE_NEED_MEMBERSHIP_CERTIFICATION = 10005

const val USER_NEED_BIND_REAL_NAME = 10006 // 提现时会出现，需要完善资料

const val USER_NEED_BIND_BANK_CARD = 10007 // 充值时会出现，需要绑银行卡
const val RECHARGE_WITHDRAW_IMG_CODE_ERROR = 10010 // 充值/提现验证码错误
/**
 *  登录失效 ;
 */
const val CODE_HTTP_UNAUTHORIZED = 401

/**
 *  服务器拒绝访问 ;
 */
const val CODE_HTTP_FORBIDDEN = 403


const val CODE_HTTP_NOT_FOUND = 404
const val CODE_HTTP_SERVICE_UNAVAILABLE = 503
const val CODE_HTTP_SERVICE_UNAVAILABLE2 = 502
const val CODE_HTTP_REQUEST_TIMEOUT = 408
const val CODE_HTTP_INTERNAL_SERVER_ERROR = 500
const val CODE_HTTP_TOKEN_EXPIRED = 600
const val CODE_HTTP_MUTI_DEVICE = 603
