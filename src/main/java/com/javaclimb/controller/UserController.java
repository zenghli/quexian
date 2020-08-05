package com.javaclimb.controller;

import com.javaclimb.entity.Advertisement;
import com.javaclimb.entity.User;
import com.javaclimb.service.UserService;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.ReturnDataForLayui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信端用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserByCode")
    @ResponseBody
    public ReturnData getUserByCode(String code){
        return userService.selectUserByCode(code);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnData update(HttpServletRequest request, Long userId, User user){
        user.setId(userId);
        return userService.updateUserOfWxPart(request.getServletContext(),user);
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ReturnDataForLayui getList(User user, Long userId){
        user.setId(userId);
        return userService.getList(user);
    }

    @RequestMapping("/listPage")
    public String listPage(){
        return "user/list";
    }

}
