package com.zmj.wkt.controller;

import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Acc_person;
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

    /**
     * 获取用户余额
     * @return
     */
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
            if (ZmjUtil.isNullOrEmpty(recharge_apply.getAmt())){
                throw new CommonException(ErrorCode.NULL_ERROR,"金额不能为空！");
            }
            Bs_person thisUser = getThisUser();
            recharge_apply.setApply_date(DateUtil.getNowTimestamp());
            //生成唯一流水号
            recharge_apply.setAction_no(ZmjUtil.getOrderIdByUUId());
            recharge_apply.setState(SysCode.STATE_T.getCode());
            recharge_apply.setIsAble(SysCode.IS_ABLE_WAIT.getCode());
            //获取用户当前账户金额
            Acc_person userBalance = acc_personService.getUserBalance(getThisUser().getClientID());
            Integer beforeBalance = userBalance.getBalance();
            recharge_apply.setBeforeBalance(beforeBalance);
            recharge_apply.setAfterBalance(beforeBalance+recharge_apply.getAmt());
            recharge_apply.setClientID(thisUser.getClientID());
            recharge_apply.setUsername(thisUser.getUserName());
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
    public RestfulResult reflectApply(Reflect_apply reflect_apply) throws Exception {
            if (ZmjUtil.isNullOrEmpty(reflect_apply.getAmt())){
                throw new CommonException(ErrorCode.NULL_ERROR,"金额不能为空！");
            }
            Bs_person thisUser = getThisUser();
            //获取系统时间
            reflect_apply.setApply_date(DateUtil.getNowTimestamp());
            //生成唯一流水号
            reflect_apply.setAction_no(ZmjUtil.getOrderIdByUUId());
            //设置未审核状态
            reflect_apply.setIsAble(SysCode.IS_ABLE_WAIT.getCode());
            //设置正常状态
            reflect_apply.setState(SysCode.STATE_T.getCode());
            //获取用户当前账户金额
            Acc_person userBalance = acc_personService.getUserBalance(thisUser.getClientID());
            Integer beforeBalance = userBalance.getBalance();
            reflect_apply.setBeforeBalance(beforeBalance);
            reflect_apply.setAfterBalance(beforeBalance-reflect_apply.getAmt());
            if(reflect_apply.getAfterBalance()<0){
                throw new CommonException(ErrorCode.INSUFFICIENT_BALANCE.getCode(),"账户余额不足！");
            }
            reflect_apply.setClientID(thisUser.getClientID());
            reflect_apply.setUsername(thisUser.getUserName());
            return RestfulResultUtils.success(acc_personService.reflectApply(reflect_apply));
    }
}
