package com.javaclimb.controller;

import com.javaclimb.entity.SysUser;
import com.javaclimb.service.SysUserService;
import com.javaclimb.util.Consts;
import com.javaclimb.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/")
    public String rootUrl() {
        return "redirect:login";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping("requestLogin")
    @ResponseBody
    public ReturnData requestLogin(SysUser user, HttpServletRequest request){
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser)session.getAttribute(Consts.SYS_USER_INFO);
        if(sysUser!=null){
            return ReturnData.success("登录成功");
        }
        return sysUserService.login(user,request);
    }

    /**
     * 后台首页
     */
    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView,HttpServletRequest request){
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser)session.getAttribute(Consts.SYS_USER_INFO);
        if(sysUser==null){
            modelAndView.setViewName("login");
        }else{
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    /**
     * 后台用户退出
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute(Consts.SYS_USER_INFO,null);
        return "redirect:login";
    }
}
