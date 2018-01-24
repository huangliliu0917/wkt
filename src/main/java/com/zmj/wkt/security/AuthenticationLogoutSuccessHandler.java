package com.zmj.wkt.security;

import com.zmj.wkt.utils.RestfulResultUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zmj.wkt.utils.ZmjUtil.isAjaxRequest;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author : zmj
 * @description : 注销成功后转跳方法
 * ---------------------------------
 */
public class AuthenticationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

    /**
     * 注销成功后转跳方法
     * @param request
     * @param response
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        //判断是否是Ajax方式进入
        if (isAjaxRequest(request)) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.getWriter().print(RestfulResultUtils.success("注销成功!").toString());
            response.getWriter().flush();
        } else {
            super.onLogoutSuccess(request, response, auth);
        }
    }
}
