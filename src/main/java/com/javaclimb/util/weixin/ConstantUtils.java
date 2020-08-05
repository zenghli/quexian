package com.javaclimb.util.weixin;

public class ConstantUtils {
    public static final Integer AVATAR_640 = 0;
    public static final Integer AVATAR_46 = 46;
    public static final Integer AVATAR_64 = 64;
    public static final Integer AVATAR_96 = 96;
    public static final Integer AVATAR_132 = 132;

    /**
     * 微信api返回的码 系统繁忙，此时请开发者稍候再试
     */
    public static final Integer ERR_CODE_SERVICE_BUSY = -1;
    /**
     * 微信api返回的码 成功
     */
    public static final Integer ERR_CODE_SUCCESS = 0;
    /**
     * 微信api返回的码 登录code 无效
     */
    public static final Integer ERR_CODE_INVALIDATE_CODE = 40029;
    /**
     * 微信api返回的码 频率限制，每个用户每分钟100次
     */
    public static final Integer ERR_CODE_REQUEST_LIMIT = 45011;
    /**
     * 微信api返回的码 code已被使用
     */
    public static final Integer ERR_CODE_CODEUSED = 40163;


}
