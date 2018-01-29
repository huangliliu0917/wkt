package com.zmj.wkt.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zmj.wkt.entity.Bs_permission;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2017-12-27
 */
@CacheConfig(cacheNames = "Bs_permission")
public interface Bs_permissionMapper extends BaseMapper<Bs_permission> {
    /**
     * @param id
     * @return
     */
    List<Bs_permission> findAllByPersonId(String id);

    /**
     * @return
     */
    @Cacheable(key = "'AllPermissions'")
    List<Bs_permission> findAll();
}
