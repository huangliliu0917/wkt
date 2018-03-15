package com.zmj.wkt.mapper;

import com.zmj.wkt.entity.Bs_tbkCollections;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2018-03-15
 */
public interface Bs_tbkCollectionsMapper extends BaseMapper<Bs_tbkCollections> {
    List<String> getNum_iidByClientID(@Param("ClientID") String ClientID);
}
