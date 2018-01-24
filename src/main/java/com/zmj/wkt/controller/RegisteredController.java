package com.zmj.wkt.controller;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.aspect.RestfulAnnotation;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.service.Bs_role_personService;
import com.zmj.wkt.utils.*;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.RoleCode;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
@RestController()
@RequestMapping("/noRoot")
public class RegisteredController extends CommonController {

    @Autowired
    Bs_personService bs_personService;

    @Autowired
    Bs_role_personService bs_role_personService;

    /**
     * 注册
     * @param bs_person
     * @return
     * @throws CommonException
     */
    @RequestMapping(value = "/register" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public RestfulResult register(Bs_person bs_person)throws CommonException {
        try {
            if(ZmjUtil.isNullOrEmpty(bs_person.getUserName())){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"用户名不能为空！");
            }
            if(ZmjUtil.isNullOrEmpty(bs_person.getPersonPassword())){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"密码不能为空！");
            }else {
                bs_person.setPersonPassword(MD5Util.encode(bs_person.getPersonPassword()));
            }
            if(ZmjUtil.isNullOrEmpty(bs_person.getNickName())){
                bs_person.setNickName(bs_person.getUserName());
            }
            bs_person.setRegTime(DateUtil.getNowTimestamp());
            bs_person.setMemberPoints(0L);
            bs_person.setGradeID(1L);
            String ClientID = bs_personService.registered(bs_person,null);
            //默认权限
            bs_role_personService.addPersonAsRoleName(ClientID,RoleCode.ROLE_USER.getCode());
            return RestfulResultUtils.success("注册成功！");
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException("注册失败,"+e.getMessage());
        }
    }


    /**
     * 微信注册
     * @return
     * @throws CommonException
     */
    @RequestMapping(value = "/WXRegister" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public RestfulResult WXRegister(Bs_person bs_person)throws CommonException {
        try {
            if(ZmjUtil.isNullOrEmpty(bs_person.getUserName())){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"用户名不能为空！");
            }
            if(ZmjUtil.isNullOrEmpty(bs_person.getWXOpenID())){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"微信OPENID不能为空！");
            }
            if(ZmjUtil.isNullOrEmpty(bs_person.getPersonPassword())){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"密码不能为空！");
            }else {
                bs_person.setPersonPassword(MD5Util.encode(bs_person.getPersonPassword()));
            }
            if(ZmjUtil.isNullOrEmpty(bs_person.getNickName())){
                bs_person.setNickName(bs_person.getUserName());
            }
            bs_person.setRegTime(DateUtil.getNowTimestamp());
            bs_person.setMemberPoints(0L);
            bs_person.setGradeID(1L);
            String ClientID = bs_personService.registered(bs_person,"WX");
            //默认权限
            bs_role_personService.addPersonAsRoleName(ClientID, RoleCode.ROLE_USER.getCode());
            return RestfulResultUtils.success("注册成功！");
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException("注册失败,"+e.getMessage());
        }
    }
}
