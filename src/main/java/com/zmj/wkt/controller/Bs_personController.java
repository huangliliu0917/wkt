package com.zmj.wkt.controller;

import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Acc_person;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.service.Bs_role_personService;
import com.zmj.wkt.utils.MD5Util;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
@Controller
@RestController
@RequestMapping("")
public class Bs_personController extends CommonController {
    @Autowired
    Bs_personService bs_personService;

    @Autowired
    Bs_role_personService bs_role_personService;

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/getUserInfo")
    public RestfulResult getUserInfo()throws CommonException{
        try {
            Bs_person bs_person = this.getThisUser();
            bs_person.setPersonPassword(null);
            return RestfulResultUtils.success(bs_person);
        }catch (CommonException ce){
            ce.printStackTrace();
            throw ce;
        }catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,e.getMessage());
        }
    }

    /**
     * 获取用户权限
     * @return
     * @throws CommonException
     */
    @GetMapping("/getThisUserRole")
    public RestfulResult getThisUserRole()throws CommonException{
        try {
            return RestfulResultUtils.success(bs_role_personService.getUserRole(getThisUser().getClientID()));
        }catch (CommonException ce){
            ce.printStackTrace();
            throw ce;
        }catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,e.getMessage());
        }
    }

    /**
     * 修改密码
     * @param password
     * @return
     * @throws CommonException
     */
    @PostMapping("/editPassword")
    public RestfulResult editPassword(String password) throws Exception {
        Bs_person bs_person = getThisUser();
        bs_person.setPersonPassword(MD5Util.encode(password));
        bs_personService.updatePersonInfo(bs_person);
        return RestfulResultUtils.success("修改成功!");
    }

    /**
     * 设置PID
     * @param PID
     * @return
     * @throws Exception
     */
    @PostMapping("/setTkbPID")
    public RestfulResult setTkbPID(String PID) throws Exception {
        if(ZmjUtil.isNullOrEmpty(PID)){
            throw new CommonException(ErrorCode.NULL_ERROR,"PID不能为空！");
        }
        if(!PID.startsWith(SysConstant.PID_START)){
            throw new CommonException(ErrorCode.VERIFY_ERROR,"请输入正确格式的PID！");
        }
        String [] pis = PID.split("_");
        if(ZmjUtil.isNullOrEmpty(pis)||pis.length!=SysConstant.PID_SIZE){
            throw new CommonException(ErrorCode.VERIFY_ERROR,"请输入正确格式的PID！");
        }
        Bs_person bs_person =  getThisUser();
        bs_person.setPID(PID);
        bs_personService.updatePersonInfo(bs_person);
        return RestfulResultUtils.success("新增/修改PID成功!");
    }

    /**
     * 上传头像
     * @param file
     * @return
     */
    @PostMapping("/uploadPersonPhoto")
    public RestfulResult uploadPersonPhoto(MultipartFile file) throws Exception {
        if(ZmjUtil.isNullOrEmpty(file)){
            throw new CommonException(ErrorCode.NULL_ERROR,"Photo不能为空！");
        }
        Bs_person thisUser = getThisUser();
        String url = "person-img/";
        try {
            String photoUrl = url + thisUser.getClientID() + "." + ZmjUtil.getExtensionName(file.getOriginalFilename());
            bs_personService.uploadfile(file,photoUrl);
            Bs_person newPerson = new Bs_person();
            newPerson.setClientID(thisUser.getClientID());
            newPerson.setPhoto(photoUrl);
            bs_personService.updatePersonInfo(newPerson);
            return RestfulResultUtils.success();
        }catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR,"文件上传失败！IOException:"+e.getMessage());
        }
    }
}
