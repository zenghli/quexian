package com.javaclimb.util.filter;

import com.alibaba.druid.support.json.JSONUtils;
import com.javaclimb.util.Consts;
import com.nimbusds.jose.JOSEException;
import com.javaclimb.util.MapWrapperUtils;
import com.javaclimb.util.ReturnData;
import com.javaclimb.util.TextUtils;
import com.javaclimb.util.exception.CustomerException;
import com.javaclimb.util.jwt.JwtUtil;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class MyWebFilter extends GenericFilterBean {
    public static final String FROM_MICRO = "micro";
    public static final String USER_TYPE_NORMAL = "userTypeNormal";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpServletResponse resp = ((HttpServletResponse) response);
        System.out.println(req.getRequestURL());

        List<String> urlIgnorelist = new ArrayList<>();
        urlIgnorelist.add("/resources/");
        urlIgnorelist.add("/upload/");
        urlIgnorelist.add("/requestLogin");
        urlIgnorelist.add("/user/getUserByCode");
        urlIgnorelist.add("/advertisement/getList");
        urlIgnorelist.add("/goods/getList");
        urlIgnorelist.add("/goodsType/getList");
        urlIgnorelist.add("/login");
        urlIgnorelist.add("/regist");
        urlIgnorelist.add("/logout");

        boolean isNeedDealwith = true;
        for (String path : urlIgnorelist) {
            if (req.getServletPath().contains(path)) {
                isNeedDealwith = false;
                break;
            }
        }
        try {
            //判断用户的请求来源
            String from = req.getHeader("from");
            if (TextUtils.isEmpty(from)) {
                //web端
                if (isNeedDealwith) {
                    //判断当前用户是否登录
                    HttpSession session = req.getSession();
                    Object o = session.getAttribute(Consts.SYS_USER_INFO);
                    if (o == null) {
                        resp.sendRedirect(req.getContextPath() + "/login");
                    } else {
                        //重新设置，这样就会重新计算失效时间
                        session.setAttribute(Consts.SYS_USER_INFO, o);
                    }
                }
                filterChain.doFilter(req, resp);
            } else if (FROM_MICRO.equals(from)) {

                //移动端登录
                resp.setCharacterEncoding("utf-8");
                resp.setContentType("application/json; charset=utf-8");
                if (!isNeedDealwith) {
                    filterChain.doFilter(req, resp);
                    return;
                }
                String token = req.getHeader("token");
                if (TextUtils.isEmpty(token)) {
                    makeResponse(resp, ReturnData.fail("请携带token"));
                    return;
                }
                ReturnData returnData = JwtUtil.valid(token);
                if (returnData.getCode() != 200) {
                    makeResponse(resp, returnData);
                    return;
                }
                Long id = (Long) returnData.getData();
                if (id == null) {
                    makeResponse(resp, ReturnData.fail("token有误"));
                    return;
                }
                //利用原始的request对象创建自己扩展的request对象并添加自定义参数
                RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(req);
                Map<String, Object> param = new HashMap<>();
                param.put(MapWrapperUtils.KEY_USER_ID, id);
                requestParameterWrapper.addParameters(param);
                filterChain.doFilter(requestParameterWrapper, resp);
            } else {
                makeResponse(resp, ReturnData.fail("from参数有误"));
            }
        } catch (JOSEException e) {
            makeResponse(resp, ReturnData.fail("token异常"));
        } catch (Exception e) {
            Throwable throwable = e.getCause();
            System.out.println(throwable.getCause());
            if (throwable instanceof CustomerException) {
                makeResponse(resp, ReturnData.fail(((CustomerException) throwable).getMsgDes()));
            } else {
                makeResponse(resp, ReturnData.fail(throwable.getCause() == null ? e.getMessage() : throwable.getCause().getMessage()));
            }
        }

    }

    private void makeResponse(HttpServletResponse resp, ReturnData returnData) {
        try {
            resp.getWriter().print(JSONUtils.toJSONString(returnData));
            resp.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
