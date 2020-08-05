package com.javaclimb.util.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.TextUtils;
import com.javaclimb.util.http.HttpRequest;

public class AccessTokenUtils {
    public static ReturnData getAccessToken() {
        //请求参数
        String params = "grant_type=client_credential" +
                "&appid=" + SettingUtils.WX_APPID +
                "&secret=" + SettingUtils.WX_APPSECRET;
        //发送请求
        String result = HttpRequest.sendGet(UriUtils.GET_ACCESS_TOKEN, params);
        //解析相应内容（转换成json对象）
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject == null) {
            return ReturnData.fail("网络获取access_token失败");
        }
        String access_token = jsonObject.get("access_token").toString();
        Integer expires_in = (Integer) jsonObject.get("expires_in");//凭证有效时间，单位：秒
        if (TextUtils.isEmpty(access_token) || expires_in == null || expires_in <= 10) {
            return ReturnData.fail("网络获取access_token错误");
        }
        return ReturnData.success(access_token);
    }
//    public static void main(String[] args) {
//        ReturnData accessToken = AccessTokenUtils.getAccessToken();
//        System.out.println(accessToken.getMsg());
//    }
}