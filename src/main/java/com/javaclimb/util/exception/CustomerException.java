package com.javaclimb.util.exception;

import com.alibaba.druid.support.json.JSONUtils;
import com.javaclimb.util.ReturnData;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerException extends RuntimeException implements HandlerExceptionResolver {
    private String msgDes;  //异常对应的描述信息

    public CustomerException(String message) {
        super(message);
        this.msgDes = message;
    }

    public String getMsgDes() {

        return msgDes;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Throwable throwable = e.getCause();
        if (httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            if (throwable instanceof CustomerException) {
                makeResponse(httpServletResponse, ReturnData.fail(((CustomerException) throwable).getMsgDes()));
            } else {
                makeResponse(httpServletResponse, ReturnData.fail(e.getMessage()));
            }
            return null;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(httpServletRequest.getContextPath() + "/error/500");
            if (throwable instanceof CustomerException) {
                modelAndView.addObject("msg", ((CustomerException) throwable).getMsgDes());
            } else {
                modelAndView.addObject("msg", e.getMessage());
            }
            return modelAndView;
        }

    }

    private void makeResponse(ServletResponse resp, ReturnData returnData) {
        try {
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().print(JSONUtils.toJSONString(returnData));
            resp.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}