package com.zmj.wkt.controller;

import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.aspect.RestfulAnnotation;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zmj
 */
@Controller
public class HelloController extends CommonController{
    @RequestMapping(value = "/hello" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/login" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public RestfulResult login()throws CommonException{
        throw new CommonException(ErrorCode.NOT_LOGIN);
    }

    @RequestMapping(value = "/notLogin" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public void notLogin(){
        throw new CommonException(ErrorCode.NOT_LOGIN);
    }

    @RequestMapping(value = "/loginError" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public void loginError(){
        throw new CommonException(ErrorCode.VERIFY_ERROR,"登录失败，账号密码错误");
    }

    @RequestMapping(value = "/loginSuccess" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public RestfulResult loginSuccess(){
        return RestfulResultUtils.success("登录成功");
    }

    @RequestMapping(value = "/home" , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public RestfulResult home(){
        System.out.println(ZmjUtil.isAjaxRequest(request));
        return RestfulResultUtils.success("测试home");
    }


}
