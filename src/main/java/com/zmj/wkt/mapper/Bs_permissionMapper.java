package com.zmj.wkt.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zmj.wkt.entity.Bs_permission;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2017-12-27
 */
public interface Bs_permissionMapper extends BaseMapper<Bs_permission> {
    /**
     * @param id
     * @return
     */
    List<Bs_permission> findAllByPersonId(String id);

    /**
     * @return
     */
    List<Bs_permission> findAll();
}
