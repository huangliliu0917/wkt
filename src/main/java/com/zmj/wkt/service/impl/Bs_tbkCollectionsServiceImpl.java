package com.zmj.wkt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zmj.wkt.entity.Bs_tbkCollections;
import com.zmj.wkt.mapper.Bs_tbkCollectionsMapper;
import com.zmj.wkt.service.Bs_tbkCollectionsService;
import com.zmj.wkt.common.CommonManagerImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zmj
 * @since 2018-03-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class Bs_tbkCollectionsServiceImpl extends CommonManagerImpl<Bs_tbkCollectionsMapper, Bs_tbkCollections> implements Bs_tbkCollectionsService {

    @Override
    public boolean addTbkCollection(Bs_tbkCollections bs_tbkCollections) {
        bs_tbkCollections.setTbkID("TbkC_"+ UUID.randomUUID().toString().replaceAll("-", ""));
        this.insert(bs_tbkCollections);
        return true;
    }

    @Override
    public boolean delTbkCollenctions(String[] num_iids,String ClientID) {
        for (String num_idd:num_iids) {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.setEntity(new Bs_tbkCollections());
            entityWrapper.where("num_iid = {0} and ClientID = {1}" ,num_idd,ClientID);
            delete(entityWrapper);
        }
        return true;
    }
}
