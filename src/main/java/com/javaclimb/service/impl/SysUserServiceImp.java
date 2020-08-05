package com.javaclimb.service.impl;

import com.javaclimb.entity.SysUser;
import com.javaclimb.mapper.SysUserMapper;
import com.javaclimb.service.SysUserService;
import com.javaclimb.util.Consts;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员管理的service实现
 */
@Service
public class SysUserServiceImp implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 用户登录判断，返回登录是否成功的对象
     */
    @Override
    public ReturnData login(SysUser user, HttpServletRequest request) {
        ReturnData returnData = ReturnData.fail("登录失败");
        if(user==null){
            returnData = ReturnData.fail("非法调用");
        }else if(TextUtils.isEmpty(user.getUsername())){
            returnData = ReturnData.fail("用户名不能为空");
        }else if(TextUtils.isEmpty(user.getPassword())){
            returnData = ReturnData.fail("密码不能为空");
        }else{
            SysUser dbUser = sysUserMapper.getUserByUserName(user.getUsername());
            if(dbUser==null){
                returnData = ReturnData.fail("用户不存在");
            }else if(!dbUser.getPassword().equals(user.getPassword())){
                returnData = ReturnData.fail("密码错误");
            }else{
                request.getSession().setAttribute(Consts.SYS_USER_INFO,dbUser);
                returnData = ReturnData.success("登录成功");
            }
        }
        return returnData;
    }
}
