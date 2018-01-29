package com.zmj.wkt.service.impl;

import com.zmj.wkt.entity.Bs_permission;
import com.zmj.wkt.mapper.Bs_permissionMapper;
import com.zmj.wkt.service.Bs_permissionService;
import com.zmj.wkt.common.CommonManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zmj
 * @since 2018-01-23
 */
@Service
@CacheConfig(cacheNames = "Bs_permission")
public class Bs_permissionServiceImpl extends CommonManagerImpl<Bs_permissionMapper, Bs_permission> implements Bs_permissionService {

    @Autowired
    Bs_permissionMapper bs_permissionMapper;

    @Override
    @Cacheable(key = "#root.caches[0].name + '.ClientID:'+ #ClientID")
    public List<Bs_permission> findAllByPersonId(String ClientID) {
        return bs_permissionMapper.findAllByPersonId(ClientID);
    }

}
