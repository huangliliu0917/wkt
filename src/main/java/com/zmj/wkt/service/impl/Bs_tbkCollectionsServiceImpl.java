package com.zmj.wkt.service.impl;

import com.zmj.wkt.entity.Bs_tbkCollections;
import com.zmj.wkt.mapper.Bs_tbkCollectionsMapper;
import com.zmj.wkt.service.Bs_tbkCollectionsService;
import com.zmj.wkt.common.CommonManagerImpl;
import org.springframework.stereotype.Service;

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
public class Bs_tbkCollectionsServiceImpl extends CommonManagerImpl<Bs_tbkCollectionsMapper, Bs_tbkCollections> implements Bs_tbkCollectionsService {

    @Override
    public boolean addTbkCollection(Bs_tbkCollections bs_tbkCollections) {
        bs_tbkCollections.setTbkID("TbkC_"+ UUID.randomUUID().toString().replaceAll("-", ""));
        this.insert(bs_tbkCollections);
        return true;
    }
}
