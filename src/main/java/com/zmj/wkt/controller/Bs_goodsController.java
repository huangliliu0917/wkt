package com.zmj.wkt.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_goods_type;
import com.zmj.wkt.service.Bs_goodsService;
import com.zmj.wkt.service.Bs_goods_typeService;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author : zmj
 * @description :微信群控制器
 * ---------------------------------
 */
@RestController
@RequestMapping("/noRoot")
@CacheConfig(cacheNames = "Bs_goodsController")
public class Bs_goodsController extends CommonController{

    @Autowired
    Bs_goods_typeService bs_goods_typeService;

    @Autowired
    Bs_goodsService bs_goodsService;

    /**
     * 获取微信群类型列表
     * @return
     */
    @Cacheable(key = "'getGoodsType'")
    @GetMapping("/getGoodsType")
    @ResponseBody
    public RestfulResult getGoodsType(){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_goods_type());
        List list = bs_goods_typeService.selectList(entityWrapper);
        return RestfulResultUtils.success(list);
    }

    /**
     * 根据类型获取微信群列表
     * @param typeID
     * @return
     */
    @Cacheable(value = "getGoods",key = "#root.caches[0].name + ':' + #typeID")
    @GetMapping("/getGoods")
    @ResponseBody
    public RestfulResult getGoods(String typeID){
        if(ZmjUtil.isNullOrEmpty(typeID)){
            throw new CommonException(ErrorCode.NULL_ERROR,"类型ID不能为空！");
        }
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_goods());
        entityWrapper.where("GTypeID ={0}",typeID);
        List list = bs_goodsService.selectList(entityWrapper);
        return RestfulResultUtils.success(list);
    }
}
