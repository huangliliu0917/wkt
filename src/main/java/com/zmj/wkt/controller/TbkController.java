package com.zmj.wkt.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.gson.JsonArray;
import com.taobao.api.ApiException;
import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.*;
import com.zmj.wkt.mapper.Bs_tbkCollectionsMapper;
import com.zmj.wkt.service.*;
import com.zmj.wkt.utils.DateUtil;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.TbkUtil;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import net.sf.json.JSONArray;
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

    @Autowired
    Bs_personService bs_personService;

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
     * @param num_iids
     * @return
     * @throws ApiException
     */
    @PostMapping("/tpwdCreate")
    public RestfulResult tpwdCreate(String[] num_iids,String ClientID) throws Exception {
        if(ZmjUtil.isNullOrEmpty(num_iids)){
            throw new CommonException(ErrorCode.NULL_ERROR,"num_iid不能为空!");
        }
        if(ClientID.isEmpty()){
            throw new CommonException(ErrorCode.NULL_ERROR,"ClientID不能为空!");
        }
        logger.info("num_iids = {}",JSONArray.fromObject(num_iids).toString());
        StringBuilder strNum_iid = new StringBuilder();
        for (String id : num_iids){
            strNum_iid.append(id);
            strNum_iid.append(",");
        }
        strNum_iid.deleteCharAt(strNum_iid.length()-1);
        logger.info(strNum_iid.toString());
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_tbkCollections());
        entityWrapper.where("ClientID = {0} ",ClientID);
        entityWrapper.and().in("num_iid",num_iids);
        List<Bs_tbkCollections> list = bs_tbkCollectionsService.selectList(entityWrapper);
        Bs_person client = bs_personService.findByClientID(ClientID);
        StringBuilder tpwd = new StringBuilder();
        int i=0;
        for (Bs_tbkCollections tbkCollections: list){
            i++;
            tpwd.append(i);
            tpwd.append(".");
            tpwd.append("\r\n");
            tpwd.append("商品:").append(tbkCollections.getTitle());
            tpwd.append("\r\n");
            tpwd.append("【一口价】:").append(tbkCollections.getZk_final_price()).append("元");
            tpwd.append("\r\n");
            tpwd.append("【优惠券】:").append(tbkCollections.getCouponInfo());
            tpwd.append("\r\n");
            tpwd.append("【劵后价】:一口价 — 优惠劵");
            tpwd.append("\r\n");
            tpwd.append("复制这条淘口令，[打开手机淘宝]即可领券下单，淘口令:");
            tpwd.append("\r\n");
            tpwd.append(TbkUtil.tpwdCreate(client.getPID(), tbkCollections.getTitle(), tbkCollections.getCoupon_click_url(), tbkCollections.getPict_url(), null));
            tpwd.append("\r\n");
            tpwd.append("★提示:本内容发送给朋友，同样可享受内部优惠");
            tpwd.append("\r\n");
            tpwd.append("======================");
            tpwd.append("\r\n");
        }
        return  RestfulResultUtils.success(tpwd.toString());
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
     * 获取热门分类商品
     * @return
     */
    @PostMapping("/favoritesItemGet")
    public RestfulResult favoritesItemGet(Long favoritesId , Long pageNo , Long pageSize) throws Exception {
        Bs_person thisUser = getThisUser();
        return RestfulResultUtils.success(TbkUtil.favoritesItemGet(thisUser.getPID(),favoritesId,pageNo,pageSize));
    }

    /**
     * 获取分类
     * @return
     */
    @GetMapping("/favoritesGet")
    public RestfulResult favoritesGet() throws ApiException {
        return RestfulResultUtils.success(TbkUtil.favoritesGet());
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
        StringBuilder num_iids = new StringBuilder();
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
        bs_orderform.setProductUserName(bs_goods.getGUserName());
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

    /**
     * 查询淘客宝商品
     * @param num_iids
     * @return
     */
    @PostMapping("/getTkbGoodsInfo")
    public RestfulResult getTkbGoodsInfo(String [] num_iids) throws Exception {
        if(ZmjUtil.isNullOrEmpty(num_iids)){
            throw new CommonException(ErrorCode.NULL_ERROR,"num_iids不能为空");
        }
        StringBuilder strNum_iid = new StringBuilder();
        for (String id : num_iids){
            strNum_iid.append(id);
            strNum_iid.append(",");
        }
        strNum_iid.deleteCharAt(strNum_iid.length()-1);
        return RestfulResultUtils.success(TbkUtil.getGoodInfo(strNum_iid.toString()));
    }


}
