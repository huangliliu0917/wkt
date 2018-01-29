package com.zmj.wkt.controller;

import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.entity.Recharge_apply;
import com.zmj.wkt.entity.Reflect_apply;
import com.zmj.wkt.mapper.Acc_personMapper;
import com.zmj.wkt.service.Acc_personService;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.utils.DateUtil;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
@RestController
@RequestMapping("")
public class AccController extends CommonController{
    @Autowired
    Acc_personService acc_personService;

    @GetMapping("/getUserBalance")
    @ResponseBody
    public RestfulResult getUserBalance(){
        try {
            Bs_person bs_person = this.getThisUser();
            return RestfulResultUtils.success(acc_personService.getUserBalance(bs_person.getClientID()));
        }catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,e.getMessage());
        }
    }

    /**
     * 充值审核
     * @param recharge_apply
     * @return
     */
    @PostMapping("/rechargeApply")
    @ResponseBody
    public RestfulResult rechargeApply(Recharge_apply recharge_apply){
        try {
            recharge_apply.setApply_date(DateUtil.getNowTimestamp());
            recharge_apply.setAction_no(ZmjUtil.getOrderIdByUUId());
            recharge_apply.setState(SysCode.STATE_T.getCode());
            recharge_apply.setIsAble(SysCode.IS_ABLE_WAIT.getCode());
            return RestfulResultUtils.success( acc_personService.rechargeApply(recharge_apply));
        }catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,e.getMessage());
        }
    }

    /**
     * 提现审核
     * @param reflect_apply
     * @return
     */
    @PostMapping("/reflectApply")
    @ResponseBody
    public RestfulResult reflectApply(Reflect_apply reflect_apply){
        try {
            //获取系统时间
            reflect_apply.setApply_date(DateUtil.getNowTimestamp());
            //生成唯一流水号
            reflect_apply.setAction_no(ZmjUtil.getOrderIdByUUId());
            //设置未审核状态
            reflect_apply.setIsAble(SysCode.IS_ABLE_WAIT.getCode());
            //设置正常状态
            reflect_apply.setState(SysCode.STATE_T.getCode());
            return RestfulResultUtils.success(acc_personService.reflectApply(reflect_apply));
        }catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,e.getMessage());
        }
    }
}
