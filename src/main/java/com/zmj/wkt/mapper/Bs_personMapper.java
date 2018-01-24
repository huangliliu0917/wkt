package com.zmj.wkt.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zmj.wkt.entity.Bs_person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户个人信息表 Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2017-12-27
 */
public interface Bs_personMapper extends BaseMapper<Bs_person> {
    List<Bs_person> getAll();
    Bs_person findByName(@Param("name") String name);
    Bs_person findByWXOpenID(@Param("WXOpenID") String WXOpenID);
    Boolean uploadPhoto(@Param("photo") String photo, @Param("UserName") String UserName);

}
