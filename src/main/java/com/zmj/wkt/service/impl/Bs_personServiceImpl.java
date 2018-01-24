package com.zmj.wkt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zmj.wkt.common.CommonManagerImpl;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.mapper.Bs_personMapper;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 客户个人信息表 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2017-12-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class Bs_personServiceImpl extends CommonManagerImpl<Bs_personMapper, Bs_person> implements Bs_personService {
    @Autowired
    Bs_personMapper bs_personMapper;

    @Override
    public Bs_person findByName(String name) {
        Bs_person bs_person = new Bs_person();
        bs_person.setUserName(name);
        return bs_personMapper.selectOne(bs_person);
    }

    /**
     * 注册功能
     * @param bs_person
     * @param registerWay
     * @return
     */
    @Override
    public String registered(Bs_person bs_person,String registerWay) {
        bs_person.setClientID(registerWay+"_"+UUID.randomUUID().toString().replaceAll("-", ""));
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_person());
        entityWrapper.where("UserName ={0}",bs_person.getUserName());
        List selectList = bs_personMapper.selectList(entityWrapper);
        if(selectList!=null&&selectList.size()>0){
            throw new CommonException(ErrorCode.VERIFY_ERROR,"用户名已存在！");
        }
        bs_personMapper.insert(bs_person);
        return bs_person.getClientID();
    }

    /**
     * 更新用户信息
     * @param bs_person
     * @return
     */
    @Override
    public Boolean updatePersonInfo(Bs_person bs_person) {
        try {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.setEntity(new Bs_person());
            entityWrapper.where("ClientID ={0}",bs_person.getClientID());
            bs_personMapper.update(bs_person,entityWrapper);
            return true;
        }catch (Exception e){
            throw new CommonException(ErrorCode.DATEBASE_ERRPR,e.getMessage());
        }
    }

    @Override
    public Bs_person findByWXOpenID(String WXOpenID) {
        return bs_personMapper.findByWXOpenID(WXOpenID);
    }

}
