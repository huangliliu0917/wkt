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
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public RestfulResult getUserInfo(){
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


}
