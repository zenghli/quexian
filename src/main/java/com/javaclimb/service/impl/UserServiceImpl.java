package com.javaclimb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.entity.Advertisement;
import com.javaclimb.entity.User;
import com.javaclimb.mapper.UserMapper;
import com.javaclimb.service.UserService;
import com.javaclimb.util.MapWrapperUtils;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;
import com.javaclimb.util.jwt.JwtUtil;
import com.javaclimb.util.weixin.WxAvatarDownloadThread;
import com.javaclimb.util.weixin.WxLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ReturnData selectUserByCode(String code) {
        //根据code去微信查询openid
        ReturnData returnData = WxLoginUtils.jsCode2Session(code);
        if(returnData.getCode()!=ReturnData.KEY_CODE_SUCCESS){
            return returnData;
        }
        User newUser = (User) returnData.getData();
        //查询用户是否已经注册，如果没有注册进行注册操作
        User dbUser = userMapper.selectUserByOpenid(newUser.getOpenid());
        if(dbUser==null){       //没注册呢
            newUser.setHaveGetUserInfo(User.NOT_GET_USERINFO);
            int count = userMapper.insertUser(newUser);
            if(count<=0){
                return ReturnData.fail("登录失败，请重试");
            }
            Long userId = userMapper.selectUserByOpenid(newUser.getOpenid()).getId();
            Map<String,Object> map = MapWrapperUtils.builder(MapWrapperUtils.KEY_USER_ID,userId);
            String token = JwtUtil.creatToken(map);
            return ReturnData.notGetUserInfo(token);
        }else if(User.NOT_GET_USERINFO.equals(dbUser.getHaveGetUserInfo())){        //已经注册过但是尚未完善用户信息
            Long userId = dbUser.getId();
            Map<String,Object> map = MapWrapperUtils.builder(MapWrapperUtils.KEY_USER_ID,userId);
            String token = JwtUtil.creatToken(map);
            return ReturnData.notGetUserInfo(token);
        }else {         //已经注册并已经完善了用户信息
            Long userId = dbUser.getId();
            Map<String,Object> map = MapWrapperUtils.builder(MapWrapperUtils.KEY_USER_ID,userId);
            String token = JwtUtil.creatToken(map);
            return (ReturnData) ReturnData.success().put(ReturnData.KEY_DATA,token);
        }
    }

    /**
     * 更新用户信息的微信部分
     *
     * @param servletContext
     * @param user
     */
    @Override
    public ReturnData updateUserOfWxPart(ServletContext servletContext, User user) {
        if(user.getId()==null){
            return ReturnData.fail("参数有误，userid");
        }
        //先保存用户的微信部分信息，不包括头像
        int count = userMapper.updateUserOfWx(user);
        if(count>0){
            //将用户的头像保存到本地，由子线程来控制。因为图片下载是在其它信息已经保存成功的前提下才开始的，所以用户信息是否完善的标记放在子线程里控制
            new WxAvatarDownloadThread(userMapper,servletContext,user.getAvatarUrl(),user.getId()).start();
            return ReturnData.success("保存成功");
        }
        return ReturnData.fail("用户信息保存失败");
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    @Override
    public ReturnDataForLayui getList(User user) {
        PageHelper.startPage(user.getPage(),user.getLimit());
        List<User> list = userMapper.getList(user);
        PageInfo<User> info = new PageInfo<>(list);
        return ReturnDataForLayui.success(list,info.getTotal());
    }
}
















