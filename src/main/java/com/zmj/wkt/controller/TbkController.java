package com.zmj.wkt.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.taobao.api.ApiException;
import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.*;
import com.zmj.wkt.mapper.Bs_tbkCollectionsMapper;
import com.zmj.wkt.service.Bs_goodsService;
import com.zmj.wkt.service.Bs_hotQService;
import com.zmj.wkt.service.Bs_orderformService;
import com.zmj.wkt.service.Bs_tbkCollectionsService;
import com.zmj.wkt.utils.DateUtil;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.TbkUtil;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.POST;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
public class TbkController extends CommonController {

    @Autowired
    Bs_orderformService bs_orderformService;

    @Autowired
    Bs_hotQService bs_hotQService;

    @Autowired
    Bs_tbkCollectionsService bs_tbkCollectionsService;

    @Autowired
    Bs_tbkCollectionsMapper bs_tbkCollectionsMapper;

    @Autowired
    Bs_goodsService bs_goodsService;
    /**
     * 获取淘宝客商品列表
     * @param Q         搜索关键字
     * @param pageNo    页码
     * @param pageSize  单页数量
     * @return
     */
    @PostMapping("/getTbkGoodsList")
    public RestfulResult getTbkGoodsList(String Q ,Long pageNo ,Long pageSize) throws Exception {
        Bs_person bs_person = getThisUser();
        if(org.apache.commons.lang.StringUtils.isBlank(bs_person.getPID())){
            throw new CommonException(ErrorCode.NULL_ERROR,"PID不能为空!");
        }
        return RestfulResultUtils.success(TbkUtil.getGoodsList(bs_person.getPID(),Q,pageNo,pageSize));
    }

    /**
     * 生成淘口令
     * @param url
     * @param text
     * @param logo
     * @return
     * @throws ApiException
     */
    @PostMapping("/tpwdCreate")
    public RestfulResult tpwdCreate(String url,String text,String logo) throws Exception {
        Bs_person thisUser = getThisUser();
        return  RestfulResultUtils.success(TbkUtil.tpwdCreate(thisUser.getPID(),text,url,logo,null));
    }


    /**
     * 获取热词
     * @return
     */
    @GetMapping("/getHotQ")
    public RestfulResult getHotQ(){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_hotQ());
        return RestfulResultUtils.success(bs_hotQService.selectList(entityWrapper));
    }

    /**
     * 添加淘客选品收藏
     * @param bs_tbkCollections
     * @return
     */
    @PostMapping("/addToTbkCollections")
    public RestfulResult addToTbkCollections(Bs_tbkCollections bs_tbkCollections) throws Exception {
        String clientID = getThisUser().getClientID();
        if (ZmjUtil.isNullOrEmpty(bs_tbkCollections)){
            throw new CommonException(ErrorCode.NULL_ERROR,"不能为空！");
        }
        if (ZmjUtil.isNullOrEmpty(bs_tbkCollections.getNum_iid())){
            throw new CommonException(ErrorCode.NULL_ERROR,"num_iid不能为空！");
        }
        EntityWrapper entityWrapper =  new EntityWrapper();
        entityWrapper.setEntity(new Bs_tbkCollections());
        entityWrapper.where("ClientID = {0} and num_iid = {1}" , clientID,bs_tbkCollections.getNum_iid());
        List selectList = bs_tbkCollectionsMapper.selectList(entityWrapper);
        if(!ZmjUtil.isNullOrEmpty(selectList)){
            throw new CommonException(ErrorCode.ISHAVEN_ERROR,"已经添加！");
        }
        bs_tbkCollections.setClientID(getThisUser().getClientID());
        bs_tbkCollectionsService.addTbkCollection(bs_tbkCollections);
        return RestfulResultUtils.success();
    }

    /**
     * 获取淘客选品收藏列表
     * @return
     * @throws Exception
     */
    @GetMapping("/getTbkCollenctions")
    public RestfulResult getTbkCollenctions() throws Exception {
        Bs_person bs_person = getThisUser();
        List<String> numList = bs_tbkCollectionsMapper.getNum_iidByClientID(bs_person.getClientID());
        if(ZmjUtil.isNullOrEmpty(numList)){
            return RestfulResultUtils.success();
        }
        StringBuffer num_iids = new StringBuffer();
        for (String s:numList ) {
            num_iids.append(s);
            num_iids.append(",");
        }
        num_iids.delete(num_iids.length()-1,num_iids.length()-1);
        return RestfulResultUtils.success(TbkUtil.getGoodInfo(num_iids.toString()));
    }

    /**
     * 获取淘客选品收藏条数
     * @return
     * @throws Exception
     */
    @GetMapping("/getTbkCollenctionsCount")
    public RestfulResult getTbkCollenctionsCount() throws Exception {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_tbkCollections());
        entityWrapper.where("ClientID = {0}",getThisUser().getClientID());
        Integer selectCount = bs_tbkCollectionsMapper.selectCount(entityWrapper);
        return  RestfulResultUtils.success(selectCount);
    }

    /**
     * 生成订单
     * @param goodsID
     * @param num_iids
     * @return
     */
    @PostMapping("/createOrder")
    public RestfulResult createOrder(String goodsID,String[] num_iids) throws Exception {
        Bs_person bs_person = getThisUser();
        if(ZmjUtil.isNullOrEmpty(goodsID)){
            throw new CommonException(ErrorCode.NULL_ERROR,"goodsID不能为空");
        }
        if(ZmjUtil.isNullOrEmpty(num_iids)){
            throw new CommonException(ErrorCode.NULL_ERROR,"num_iids不能为空");
        }
        //获取微信群对象
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_goods());
        entityWrapper.where("GoodsID={0}",goodsID);
        Bs_goods bs_goods = bs_goodsService.selectOne(entityWrapper);
        //构造订单对象
        Bs_orderform bs_orderform = new Bs_orderform();
        bs_orderform.setGoodsID(bs_goods.getGoodsID());
        bs_orderform.setGName(bs_goods.getGName());
        bs_orderform.setClientID(bs_person.getClientID());
        bs_orderform.setConsumerUserName(bs_person.getUserName());
        bs_orderform.setProductUserName(bs_goods.getGClientID());
        bs_orderform.setSubID("Tbk_"+ UUID.randomUUID().toString().toUpperCase());
        bs_orderform.setState(SysCode.STATE_TO_BE_SENT.getCode());
        bs_orderform.setIsAble(SysCode.IS_ABLE_YES.getCode());
        bs_orderform.setGPrice(bs_goods.getGTbkPrice());
        bs_orderform.setSpCount(num_iids.length);
        bs_orderform.setSpPrice(bs_goods.getGTbkPrice()*num_iids.length);
        bs_orderform.setItemTitle("淘客商品订单");
        bs_orderform.setItemDescription(Arrays.toString(num_iids));
        //申请时间
        bs_orderform.setSpDate(DateUtil.getNowTimestamp());
        bs_orderformService.orderFormApply(bs_orderform);
        return RestfulResultUtils.success("上传成功!等待是商户确认!");
    }

    /**
     * 删除收藏
     * @param num_iids
     * @return
     */
    @PostMapping("/delTbkCollenctions")
    public RestfulResult delTbkCollenctions(String [] num_iids) throws Exception {
        if(ZmjUtil.isNullOrEmpty(num_iids)){
            throw new CommonException(ErrorCode.NULL_ERROR,"num_iids不能为空");
        }
        bs_tbkCollectionsService.delTbkCollenctions(num_iids,getThisUser().getClientID());
        return RestfulResultUtils.success();
    }
}