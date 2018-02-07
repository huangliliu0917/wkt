package com.zmj.wkt.controller;

import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.aspect.RestfulAnnotation;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.service.Bs_goodsService;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zmj
 */
@Controller
@RestController
public class HelloController extends CommonController{
    @Autowired
    Bs_goodsService bs_goodsService;

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

    /**
     * 微信群上传申请
     * @param imgFile
     * @return
     */
    @PostMapping("/updateTest")
    public RestfulResult goodsApply(@RequestParam("imgFile") MultipartFile imgFile){
        bs_goodsService.uploadfileTest(imgFile);
        return RestfulResultUtils.success("测试上传文件成功！");
    }
}
