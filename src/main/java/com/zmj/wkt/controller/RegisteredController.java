package com.zmj.wkt.controller;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
import com.zmj.wkt.utils.sysenum.SysCode;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

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
            return RestfulResultUtils.success("注册成功！");
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException(e.getMessage());
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
    public RestfulResult WXRegister(Bs_person bs_person,String code,String bizId,String WXcode)throws CommonException {
        try {
            if(ZmjUtil.isNullOrEmpty(bs_person.getPhone())){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"手机号不能为空！");
            }
            RestfulResult restfulResult = verifyCode(bs_person.getPhone(),code, bizId);
            if(restfulResult.getStatus()!= SysCode.SYS_CODE_STATUS_SUCCESS.getCode()){
                return restfulResult;
            }
            if(ZmjUtil.isNullOrEmpty(bs_person.getUserName())){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"用户名不能为空！");
            }
            if(ZmjUtil.isNullOrEmpty(WXcode)){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"WXcode不能为空！");
            }
            String openID = WXUtil.getWXcode(WXcode);
            bs_person.setWXOpenID(openID);
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
            bs_personService.registered(bs_person,"WX");
            return RestfulResultUtils.success("注册成功！");
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException("注册失败,"+e.getMessage());
        }
    }

    /**
     * 手机注册
     * @return
     * @throws CommonException
     */
    @RequestMapping(value = "/mobileRegister" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public RestfulResult mobileRegister(String password,String mobile,String code,String bizId,String invitation_code,String username)throws CommonException {
        try {
            RestfulResult restfulResult = verifyCode(mobile, code, bizId);
            if(restfulResult.getStatus()!=200){
                return restfulResult;
            }
            Bs_person bs_person = new Bs_person();
            if(ZmjUtil.isNullOrEmpty(username)){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"用户名不能为空！");
            }
            if(ZmjUtil.isNullOrEmpty(password)){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"密码不能为空！");
            }else {
                bs_person.setPersonPassword(MD5Util.encode(password));
            }
            //邀请码判断
            if(!ZmjUtil.isNullOrEmpty(invitation_code)){
                Optional<Bs_person> bs_personOptional = Optional.ofNullable(
                        bs_personService.selectOne(new EntityWrapper<Bs_person>().where("Invitation_code = {0}", invitation_code)
                        ));
                if(bs_personOptional.isPresent()){
                    bs_person.setInviterID(bs_personOptional.get().getClientID());
                }else {
                    throw new CommonException(ErrorCode.VERIFY_ERROR,"邀请码无效");
                }

            }
            //生成用户邀请码
            bs_person.setInvitation_code(ZmjUtil.getInvitation_code());
            bs_person.setUserName(username);
            bs_person.setNickName("mobile_"+mobile);
            bs_person.setPhone(mobile);
            //获取系统时间
            bs_person.setRegTime(DateUtil.getNowTimestamp());
            bs_person.setMemberPoints(0L);
            bs_person.setGradeID(1L);

            String ClientID = bs_personService.registered(bs_person,"mobile");
            return RestfulResultUtils.success("注册成功！");
        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException("注册失败,"+e.getMessage());
        }
    }

    /**
     * 发送验证码请求
     * @param mobile
     * @return
     * @throws CommonException
     */
    @RequestMapping(value = "/sendSmsCode" ,method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public RestfulResult sendSmsCode(String mobile)throws CommonException{
        if(ZmjUtil.isNullOrEmpty(mobile)){
            throw new CommonException("手机号不能为空！");
        }
        SendSmsResponse sendSmsResponse = AliSmsUtil.sendSms(mobile);
        //返回代码
        String reqCode = sendSmsResponse.getCode();
        JSONObject jsonObject = JSONObject.fromObject(sendSmsResponse);
        if(Objects.equals(reqCode,"OK")) {
            return RestfulResultUtils.success(jsonObject);
        }else {
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,"验证码发送异常！"+sendSmsResponse.getMessage());
        }
    }

    /**
     * 验证验证码
     * @param mobile
     * @return
     * @throws CommonException
     */
    @RequestMapping(value = "/verifyCode" ,method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    @RestfulAnnotation
    public RestfulResult verifyCode(String mobile,String code,String bizId)throws CommonException{
        try {
            if(ZmjUtil.isNullOrEmpty(mobile)){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"电话号码不能为空！");
            }
            if(ZmjUtil.isNullOrEmpty(bizId)){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"bizId不能为空！");
            }

            QuerySendDetailsResponse querySendDetailsResponse = AliSmsUtil.querySendDetails(mobile,bizId);
            if(!querySendDetailsResponse.getCode().equals("OK")){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"验证码异常!"+querySendDetailsResponse.getMessage());
            }
            //返回代码
            int i = 0;
            if(querySendDetailsResponse.getSmsSendDetailDTOs()==null||querySendDetailsResponse.getSmsSendDetailDTOs().size()==0){
                throw new CommonException(ErrorCode.VERIFY_ERROR,"验证码异常，未找到已发送的验证码!");
            }
            QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO = querySendDetailsResponse.getSmsSendDetailDTOs().get(i);
            System.out.println("OutId=" + smsSendDetailDTO.getOutId());
            if(code .equals( smsSendDetailDTO.getOutId())){
                return RestfulResultUtils.success("验证码验证成功!");
            }else {
                return RestfulResultUtils.error(ErrorCode.VERIFY_ERROR,"验证码错误!");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.VERIFY_ERROR,"验证码异常!");
        }
    }

    /**
     * 根据经纬度获取用户地理位置
     * @param log
     * @param lat
     * @return
     * @throws CommonException
     */
    @PostMapping("/getUserAddr")
    @ResponseBody
    public RestfulResult getUserAddr(String log, String lat)throws CommonException{
        if(ZmjUtil.isNullOrEmpty(log)||ZmjUtil.isNullOrEmpty(lat)){
            throw new CommonException(ErrorCode.VERIFY_ERROR,"经纬度不能为空！");
        }
        return RestfulResultUtils.success(LocationUtil.getAddrArrary(log,lat));
    }
}
