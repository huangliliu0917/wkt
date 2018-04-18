package com.zmj.wkt.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmj.wkt.common.aspect.RestfulAnnotation;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.jwt.JWTAuthenticationFilter;
import com.zmj.wkt.mapper.Bs_personMapper;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
code is far away from bug with the animal protecting
 *  ┏┓　　　┏┓
 *┏┛┻━━━┛┻┓
 *┃　　　　　　　┃ 　
 *┃　　　━　　　┃
 *┃　┳┛　┗┳　┃
 *┃　　　　　　　┃
 *┃　　　┻　　　┃
 *┃　　　　　　　┃
 *┗━┓　　　┏━┛
 *　　┃　　　┃神兽保佑
 *　　┃　　　┃代码无BUG！
 *　　┃　　　┗━━━┓
 *　　┃　　　　　　　┣┓
 *　　┃　　　　　　　┏┛
 *　　┗┓┓┏━┳┓┏┛
 *　　　┃┫┫　┃┫┫
 *　　　┗┻┛　┗┻┛
 *
 *   @description :  公共Controller所有Controller都继承CommonController
 *   ---------------------------------
 *   @author : zmj
 *   @date : Create in 2017/12/27 11:34　
 */
@Controller
public class CommonController {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Bs_personService bs_personService;

    @Autowired
    Bs_personMapper bs_personMapper;

    protected String message="";

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;
    /**
     * jackson 对象转换器
     */
    protected ObjectMapper mapper = new ObjectMapper();

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){

        this.request = request;

        this.response = response;

        this.session = request.getSession();

        this.message="";

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取当前用户对象
     * @return
     * @throws Exception
     */
    public Bs_person getThisUser() throws Exception{
        String username="";
        try {
            username=SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal().toString();
        }catch (Exception e){
            System.out.println("No token dev...");
            String username2 = request.getHeader("username");
            String password = request.getHeader("password");
            Bs_person user =bs_personMapper.findByName(username);
            if(password.equals(user.getPersonPassword())){
                username=username2;
            }
        }
        if(!ZmjUtil.isNullOrEmpty(username)){
            return bs_personService.findByName(username);
        }else {
            throw new CommonException(ErrorCode.NOT_FIND_ERROR,"无法获取当前用户对象，请检查！");
        }
    }

}
