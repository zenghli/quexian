package com.javaclimb.util.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javaclimb.entity.User;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.TextUtils;
import com.javaclimb.util.http.HttpRequest;

/**
 * 微信登录相关的工具类
 */
public class WxLoginUtils {

    /**
     * 向微信服务器去请求，根据小程序传来的code获取openid
     */
    public static ReturnData jsCode2Session(String loginCode){
        if(TextUtils.isEmpty(loginCode)){
            return ReturnData.fail("参数有误");
        }
        //请求微信服务器的参数
        String params = "appid=" + SettingUtils.WX_APPID
                +"&secret=" + SettingUtils.WX_APPSECRET
                +"&js_code=" + loginCode
                +"&grant_type=" + SettingUtils.GRANT_TYPE_AUTHORIZATION_CODE;
        //发送请求
        String result = HttpRequest.sendGet(UriUtils.GET_SESSION_BY_CODE,params);
        JSONObject jsonObject = JSON.parseObject(result);
        if(jsonObject==null){
            return ReturnData.fail("微信api调用失败，请重试");
        }
        Integer errcode = (Integer)jsonObject.get("errcode");
        String errmsg = (String)jsonObject.get("errmsg");
        if(errcode!=null&&errcode!=ConstantUtils.ERR_CODE_SUCCESS){
            return ReturnData.fail(errmsg);
        }
        String openid = (String)jsonObject.get("openid");
        User user = new User();
        user.setOpenid(openid);
        return ReturnData.success(user);
    }
}
