package com.zmj.wkt.service.impl;

import com.zmj.wkt.entity.Acc_person;
import com.zmj.wkt.entity.Recharge_apply;
import com.zmj.wkt.entity.Reflect_apply;
import com.zmj.wkt.mapper.Acc_personMapper;
import com.zmj.wkt.mapper.Recharge_applyMapper;
import com.zmj.wkt.mapper.Reflect_applyMapper;
import com.zmj.wkt.service.Acc_personService;
import com.zmj.wkt.common.CommonManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户表 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2018-01-29
 */
@Service
public class Acc_personServiceImpl extends CommonManagerImpl<Acc_personMapper, Acc_person> implements Acc_personService {

    @Autowired
    Acc_personMapper acc_personMapper;

    @Autowired
    Recharge_applyMapper recharge_applyMapper;

    @Autowired
    Reflect_applyMapper reflect_applyMapper;
    /**
     * 获取用户余额
     * @param ClientID
     * @return
     */
    @Override
    public Acc_person getUserBalance(String ClientID) {
        Acc_person acc_person = new Acc_person();
        acc_person.setClientID(ClientID);
        return acc_personMapper.selectOne(acc_person);
    }

    /**
     * 插入充值表
     * @param recharge_apply
     * @return
     */
    @Override
    public boolean rechargeApply(Recharge_apply recharge_apply){
        recharge_applyMapper.insert(recharge_apply);
        return true;
    }

    /**
     * 插入提现表
     * @param reflect_apply
     * @return
     */
    @Override
    public boolean reflectApply(Reflect_apply reflect_apply) {
        reflect_applyMapper.insert(reflect_apply);
        return true;
    }
}
