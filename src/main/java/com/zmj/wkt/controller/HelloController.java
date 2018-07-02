package com.zmj.wkt.controller;

import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.aspect.RestfulAnnotation;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.jwt.JWTLoginFilter;
import com.zmj.wkt.service.Bs_goodsService;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.utils.DateUtil;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author zmj
 */
@Controller
@RestController
public class HelloController extends CommonController{
    @Autowired
    Bs_goodsService bs_goodsService;

    @Autowired
    Bs_personService bs_personService;

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

    /**
     * 获取默认token
     * @return
     */
    @GetMapping("/noRoot/getDefaultToken")
    public RestfulResult getDefaultToken() throws IOException, ServletException {
        //默认账号
        Bs_person bs_person = bs_personService.findByClientID("WX_21a1c28e2ce046238f8cbe1ad9d36d2c");
        Authentication authentication = JWTLoginFilter.defaultAuthentication(bs_person);
        return RestfulResultUtils.success(successfulAuthentication(authentication));
    }

    /**
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    public JSONObject successfulAuthentication(Authentication auth) throws IOException, ServletException {
        Date date=new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000 *365);
        System.out.println("有效期到："+ DateUtil.formatDate(date));
        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                //设置有效时间（毫秒）
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Authorization","Bearer " + token);
        return jsonObject;
    }


}
