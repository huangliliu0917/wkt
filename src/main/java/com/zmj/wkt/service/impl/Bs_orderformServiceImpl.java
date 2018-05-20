package com.zmj.wkt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.*;
import com.zmj.wkt.mapper.Acc_daybookMapper;
import com.zmj.wkt.mapper.Acc_personMapper;
import com.zmj.wkt.mapper.Bs_goodsMapper;
import com.zmj.wkt.mapper.Bs_orderformMapper;
import com.zmj.wkt.service.Acc_personService;
import com.zmj.wkt.service.Bs_goodsService;
import com.zmj.wkt.service.Bs_orderformService;
import com.zmj.wkt.common.CommonManagerImpl;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.utils.DateUtil;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import com.zmj.wkt.utils.sysenum.TrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2018-02-14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class Bs_orderformServiceImpl extends CommonManagerImpl<Bs_orderformMapper, Bs_orderform> implements Bs_orderformService {

    @Autowired
    Bs_orderformMapper bs_orderformMapper;

    @Autowired
    Acc_personMapper acc_personMapper;

    @Autowired
    Acc_personService acc_personService;

    @Autowired
    Acc_daybookMapper acc_daybookMapper;

    @Autowired
    Bs_personService bs_personService;

    @Autowired
    Bs_goodsService bs_goodsService;

    @Override
    public void orderFormApply(Bs_orderform bs_orderform, MultipartFile imgFile) {
        String url = "order-img/";
        try {
            //记录照片信息
            String photoUrl = url+bs_orderform.getSubID()+"."+ ZmjUtil.getExtensionName(imgFile.getOriginalFilename());
            uploadfile(imgFile,photoUrl);
            bs_orderform.setItemPic(photoUrl);
            orderFormApply(bs_orderform);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR,"文件上传失败！IOException:"+e.getMessage());
        }
    }

    @Override
    public void orderFormApply(Bs_orderform bs_orderform) {
        //插入订单信息表
        bs_orderformMapper.insert(bs_orderform);

        //记录交易前金额
        Acc_daybook acc_daybook = new Acc_daybook();
        Acc_person userBalance = acc_personService.getUserBalance(bs_orderform.getClientID());
        acc_daybook.setBeforeBalance(userBalance.getBalance());

        //扣款
        userBalance.setBalance(userBalance.getBalance().subtract(bs_orderform.getSpPrice()));
        if (userBalance.getBalance().doubleValue()<0){
            throw new CommonException(ErrorCode.INSUFFICIENT_BALANCE);
        }
        EntityWrapper entityWrapper1 = new EntityWrapper();
        entityWrapper1.setEntity(new Acc_person());
        entityWrapper1.where("ClientID = {0}",bs_orderform.getClientID());
        acc_personMapper.update(userBalance,entityWrapper1);

        //记流水日志
        acc_daybook.setAble_date(DateUtil.getNowTimestamp());
        acc_daybook.setAcc_date(DateUtil.getNowTimestamp());
        //记录订单编号
        acc_daybook.setSubID(bs_orderform.getSubID());
        //生成流水号
        acc_daybook.setAction_no(ZmjUtil.getOrderIdByUUId());
        acc_daybook.setAmt(bs_orderform.getSpPrice());
        acc_daybook.setState(SysCode.STATE_T.getCode());
        acc_daybook.setTr_code(TrCode.WITHHOLDING.getCode());

        //借方ID
        acc_daybook.setDebit(bs_orderform.getClientID());
        String userName = bs_personService.findByClientID(bs_orderform.getClientID()).getUserName();
        acc_daybook.setNote("代扣流水，用户名："+userName+"，代扣金额："+bs_orderform.getSpPrice());

        //记录交易后金额
        acc_daybook.setAfterBalance(userBalance.getBalance());
        acc_daybookMapper.insert(acc_daybook);
    }

    @Override
    public void orderPaySuccess(Bs_orderform bs_orderform, Bs_person bs_person,Acc_daybook oldAcc_daybook) {
        //记录交易前金额
        Acc_daybook newAcc_daybook = new Acc_daybook();
        Acc_person userBalance = acc_personService.getUserBalance(bs_orderform.getClientID());
        newAcc_daybook.setBeforeBalance(userBalance.getBalance());

        //转账
        userBalance.setBalance(userBalance.getBalance().add(oldAcc_daybook.getAmt()));
        EntityWrapper entityWrapper1 = new EntityWrapper();
        entityWrapper1.setEntity(new Acc_person());
        entityWrapper1.where("ClientID = {0}",bs_orderform.getClientID());
        acc_personMapper.update(userBalance,entityWrapper1);

        //记流水日志
        newAcc_daybook.setAble_date(DateUtil.getNowTimestamp());
        newAcc_daybook.setAcc_date(DateUtil.getNowTimestamp());
        //记录订单编号
        newAcc_daybook.setSubID(bs_orderform.getSubID());
        //生成流水号
        newAcc_daybook.setAction_no(ZmjUtil.getOrderIdByUUId());
        newAcc_daybook.setAmt(oldAcc_daybook.getAmt());
        newAcc_daybook.setState(SysCode.STATE_T.getCode());
        newAcc_daybook.setTr_code(TrCode.TRANSFER.getCode());

        //借方ID
        Bs_person debit_person = bs_personService.findByName(bs_orderform.getProductUserName());
        newAcc_daybook.setDebit(debit_person.getClientID());
        newAcc_daybook.setNote("代扣转账，用户名："+debit_person.getUserName()+"，转账金额："+oldAcc_daybook.getAmt());

        //记录交易后金额
        newAcc_daybook.setAfterBalance(userBalance.getBalance());
        acc_daybookMapper.insert(newAcc_daybook);

        //代扣记录无效化
        oldAcc_daybook.setState(SysCode.STATE_F.getCode());
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Acc_daybook());
        entityWrapper.where("Action_no = {0}",oldAcc_daybook.getAction_no());
        acc_daybookMapper.update(oldAcc_daybook,entityWrapper);

        //订单设置支付成功状态
        Bs_orderform newOrderform = new Bs_orderform();
        newOrderform.setState(SysCode.STATE_TO_SUCCESS.getCode());
        EntityWrapper bs_orderformWrapper = new EntityWrapper();
        bs_orderformWrapper.setEntity(new Bs_orderform());
        bs_orderformWrapper.where("SubID = {0}",bs_orderform.getSubID());
        bs_orderformMapper.update(newOrderform,bs_orderformWrapper);

        //群状态更改
        EntityWrapper bs_goodsWrapper = new EntityWrapper();
        bs_goodsWrapper.setEntity(new Bs_goods());
        bs_goodsWrapper.where("GoodsID = {0} " ,bs_orderform.getGoodsID());
        Bs_goods bs_goods = bs_goodsService.selectOne(bs_goodsWrapper);
        bs_goods.setGCount(bs_goods.getGCount()+bs_orderform.getSpCount());
        bs_goods.setGSail(bs_goods.getGSail()+bs_orderform.getSpCount());
        bs_goodsService.updateGoodsByID(bs_goods);
    }

    @Override
    public void orderSuccess(Bs_orderform bs_orderform, Bs_person bs_person, Acc_daybook oldAcc_daybook) {
        //订单设置成功状态
        Bs_orderform newOrderform = new Bs_orderform();
        newOrderform.setState(SysCode.STATE_TO_BE_SENT.getCode());
        EntityWrapper bs_orderformWrapper = new EntityWrapper();
        bs_orderformWrapper.setEntity(new Bs_orderform());
        bs_orderformWrapper.where("SubID = {0}",bs_orderform.getSubID());
        bs_orderformMapper.update(newOrderform,bs_orderformWrapper);
    }

    /**
     * 获取用户订单列表
     *
     * @param ClientID
     * @param state
     */
    @Override
    public List findUserOrderFormList(String ClientID, SysCode state) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_orderform());
        entityWrapper.where("ClientID = {0} and State = {1} and IsAble = {2}",ClientID,state.getCode(),SysCode.STATE_T.getCode()).orderBy("SpDate",false);
        return selectList(entityWrapper);
    }

    /**
     * 获取接单用户订单列表
     *
     * @param username
     * @param state
     */
    @Override
    public List findJdOrderFormList(String username, SysCode state) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_orderform());
        entityWrapper.where("ProductUserName = {0} and State = {1} and IsAble = {2}",username,state.getCode(),SysCode.STATE_T.getCode()).orderBy("SpDate",false);
        return selectList(entityWrapper);
    }
}
