package com.zmj.wkt.jwt;

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
 * @description :
 * ---------------------------------
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.mapper.Bs_personMapper;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.SpringApplicationContextHolder;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    Bs_personService bs_personService;

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws CommonException {
        try {
            String code = req.getParameter("code");
            if(ZmjUtil.isNullOrEmpty(code)) {
                throw new CommonException(ErrorCode.NULL_ERROR, "code为空！");
            }
            JSONObject sessionKeyOropenid = getSessionKeyOropenid(code);
            String openid = (String) sessionKeyOropenid.get("openid");
            if (ZmjUtil.isNullOrEmpty(openid)){
                throw new CommonException(ErrorCode.NULL_ERROR, "找不到openid！");
            }
            System.out.println("openid:"+openid);
            if(bs_personService==null){
                bs_personService = (Bs_personService) SpringApplicationContextHolder.getSpringBean("bs_personServiceImpl");
            }
            Bs_person bs_person = bs_personService.findByWXOpenID(openid.toString());
            if (bs_person==null){
                throw new CommonException(ErrorCode.NULL_ERROR,"未找到该用户！");
            }
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            bs_person.getUserName(),
                            bs_person.getPersonPassword(),
                            new ArrayList<>())
            );
        } catch (Exception e) {
            throw new CommonException("登录失败："+e.getMessage());
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
        res.addHeader("Authorization", "Bearer " + token);
        res.setHeader("Content-type", "text/html;charset=UTF-8");
        res.setHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Credentials", "true");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Authorization","Bearer " + token);
        res.getWriter().print(RestfulResultUtils.success(jsonObject).toString());
        res.getWriter().flush();
    }

    /**
     * 获取微信小程序 session_key 和 openid
     *
     * @author zmj
     * @param code 调用微信登陆返回的Code
     * @return
     */
    public static JSONObject getSessionKeyOropenid(String code){
        String requestUrl ="https://api.weixin.qq.com/sns/jscode2session";  //请求地址 https://api.weixin.qq.com/sns/jscode2session
        Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid", "wxf61b2a55b1278855");  //开发者设置中的appId
        requestUrlParam.put("secret", "aa94536b40570662963259f71b6901c3"); //开发者设置中的appSecret
        requestUrlParam.put("js_code", code); //小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", "authorization_code");    //默认参数
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSONObject.fromObject(ZmjUtil.sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }
}
