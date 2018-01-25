package com.zmj.wkt.controller;

import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/noRoot")
public class Bs_personController extends CommonController {
    @Autowired
    Bs_personService bs_personService;

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
}
