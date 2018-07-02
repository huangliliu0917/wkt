package com.zmj.wkt.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sun.org.apache.regexp.internal.RE;
import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_orderform;
import com.zmj.wkt.service.Bs_goodsService;
import com.zmj.wkt.service.Bs_goods_listService;
import com.zmj.wkt.service.Bs_orderformService;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @description :
 * ---------------------------------
 */
@RestController
public class MerchantsUserController extends CommonController{

    @Autowired
    Bs_goodsService bs_goodsService;

    @Autowired
    Bs_orderformService bs_orderformService;

    /**
     * 获取接单用户的群列表
     * @return
     */
    @GetMapping("/getMerchantsGoodsList")
    public RestfulResult getMerchantsGoodsList(){
        try {
            return RestfulResultUtils.success(bs_goodsService.selectGoodsListByClientID(this.getThisUser().getClientID()));
        }catch (CommonException ce){
            ce.printStackTrace();
            throw ce;
        }catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,e.getMessage());
        }
    }

    /**
     * 获取当前接单用户的订单列表
     * @return
     */
    @GetMapping("/getMyOrderFromList")
    public RestfulResult getMyOrderFromList() throws Exception {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_orderform());
        entityWrapper.where("ClientID ={0}",getThisUser().getClientID());
        return RestfulResultUtils.success(bs_goodsService.selectList(entityWrapper));
    }

    /**
     * 删除接单用户的接单群信息
     * @param goodsId
     * @return
     */
    @CacheEvict(value = "getGoods",allEntries = true)
    @PostMapping("/delMerchantsGoods")
    public RestfulResult delMerchantsGoods(String goodsId){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_goods());
        entityWrapper.where("GoodsID = {0} and State = {1} ",goodsId,SysCode.STATE_T.getCode());
        Bs_goods bs_goods = bs_goodsService.selectOne(entityWrapper);
        if(ZmjUtil.isNullOrEmpty(bs_goods)){
            throw new CommonException(ErrorCode.NOT_FIND_ERROR,"找不到对应群信息！");
        }
        //设置无效
        bs_goods.setState(SysCode.STATE_F.getCode());
        bs_goods.setIsShow(SysCode.STATE_F.getCode());
        bs_goodsService.updateGoodsByID(bs_goods);
        return RestfulResultUtils.success("删除成功!");
    }



}
