package com.zmj.wkt.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zmj.wkt.entity.Bs_role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2017-12-27
 */
public interface Bs_roleMapper extends BaseMapper<Bs_role> {
    List<Bs_role> getRoleByClientID(String ClientID);
}
