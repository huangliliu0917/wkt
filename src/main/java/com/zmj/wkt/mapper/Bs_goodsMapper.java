package com.zmj.wkt.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zmj.wkt.entity.Bs_goods;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 微信群（商品）表 Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2018-01-25
 */
public interface Bs_goodsMapper extends BaseMapper<Bs_goods> {
    List<Bs_goods> getBs_person_goodsByClientID(@Param("ClientID")String ClientID);
    List<Bs_goods> selectGoodsList(Pagination page,@Param("typeID") String typeID, @Param("addr")String addr);
    List<Bs_goods>selectGoodsListByClientID(@Param("ClientID")String ClientID);
}
