package com.zmj.wkt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Acc_daybook;
import com.zmj.wkt.entity.Acc_person;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_orderform;
import com.zmj.wkt.mapper.Acc_daybookMapper;
import com.zmj.wkt.mapper.Acc_personMapper;
import com.zmj.wkt.mapper.Bs_goodsMapper;
import com.zmj.wkt.mapper.Bs_orderformMapper;
import com.zmj.wkt.service.Acc_personService;
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
    @Override
    public void orderFormApply(Bs_orderform bs_orderform, MultipartFile imgFile) {
        String url = "order-img/";
        try {
            //记录照片信息
            String photoUrl = url+bs_orderform.getSubID()+"."+ ZmjUtil.getExtensionName(imgFile.getOriginalFilename());
            uploadfile(imgFile,photoUrl);
            bs_orderform.setItemPic(photoUrl);

            //插入订单信息表
            bs_orderformMapper.insert(bs_orderform);

            //记录交易前金额
            Acc_daybook acc_daybook = new Acc_daybook();
            Acc_person userBalance = acc_personService.getUserBalance(bs_orderform.getClientID());
            acc_daybook.setBeforeBalance(userBalance.getBalance());

            //扣款
            userBalance.setBalance(userBalance.getBalance()-bs_orderform.getSpPrice());
            if (userBalance.getBalance()<0){
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
            acc_daybook.setAmt((bs_orderform.getSpPrice()));
            acc_daybook.setState(SysCode.STATE_T.getCode());
            acc_daybook.setTr_code(TrCode.WITHHOLDING.getCode());
            //借方ID
            acc_daybook.setDebit(bs_orderform.getClientID());
            String userName = bs_personService.findByClientID(bs_orderform.getClientID()).getUserName();
            acc_daybook.setNote("代扣流水，用户名："+userName+"，代扣金额："+bs_orderform.getSpPrice());

            //记录交易后金额
            acc_daybook.setAfterBalance(userBalance.getBalance());
            acc_daybookMapper.insert(acc_daybook);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR,"文件上传失败！IOException:"+e.getMessage());
        }
    }
}
