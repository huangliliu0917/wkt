package com.zmj.wkt.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.base.Strings;
import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_goods_type;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.service.Bs_goodsService;
import com.zmj.wkt.service.Bs_goods_listService;
import com.zmj.wkt.service.Bs_goods_typeService;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.utils.DateUtil;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.GET;
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
 * @description :微信群控制器
 * ---------------------------------
 */
@RestController
@CacheConfig(cacheNames = "Bs_goodsController")
public class Bs_goodsController extends CommonController{

    @Autowired
    Bs_personService bs_personService;

    @Autowired
    Bs_goods_typeService bs_goods_typeService;

    @Autowired
    Bs_goodsService bs_goodsService;

    @Autowired
    Bs_goods_listService bs_goods_listService;

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
     * 根据类型和地区获取微信群列表
     * @param typeID
     * @param addr
     * @return
     */
    @Cacheable(value = "getGoods",key = "#root.caches[0].name +'WX'+ ':' + #typeID +','+ #addr+ ','+#pages +','+ #amount")
    @RequestMapping("/getGoods")
    @ResponseBody
    public RestfulResult getGoods(String typeID,String addr,int pages,int amount){
        if(Strings.isNullOrEmpty(typeID)){
            throw new CommonException(ErrorCode.NULL_ERROR,"类型ID不能为空！");
        }
        if(Strings.isNullOrEmpty(typeID)){
            throw new CommonException(ErrorCode.NULL_ERROR,"地区类型不能为空！");
        }
        if(ZmjUtil.isNullOrEmpty(pages)){
            throw new CommonException(ErrorCode.NULL_ERROR,"页码不能为空！");
        }
        if(ZmjUtil.isNullOrEmpty(amount)){
            throw new CommonException(ErrorCode.NULL_ERROR,"单页数量不能为空！");
        }
        Page page = new Page(pages,amount);
        Page page1 = bs_goodsService.showGoodsList(page, typeID, addr,SysCode.IS_NOT_QQ.getCode());
        return RestfulResultUtils.success(page1);
    }

    /**
     * 根据类别获取QQ群列表
     * @param typeID
     * @param addr
     * @return
     */
    @Cacheable(value = "getQQGoods",key = "#root.caches[0].name +'QQ'+ ':' + #typeID +','+ #addr+ ','+#pages +','+ #amount")
    @RequestMapping("/getQQGoods")
    @ResponseBody
    public RestfulResult getQQGoods(String typeID,String addr,int pages,int amount){
        if(Strings.isNullOrEmpty(typeID)){
            throw new CommonException(ErrorCode.NULL_ERROR,"类型ID不能为空！");
        }
        if(Strings.isNullOrEmpty(typeID)){
            throw new CommonException(ErrorCode.NULL_ERROR,"地区类型不能为空！");
        }
        if(ZmjUtil.isNullOrEmpty(pages)){
            throw new CommonException(ErrorCode.NULL_ERROR,"页码不能为空！");
        }
        if(ZmjUtil.isNullOrEmpty(amount)){
            throw new CommonException(ErrorCode.NULL_ERROR,"单页数量不能为空！");
        }
        Page page = new Page(pages,amount);
        Page page1 = bs_goodsService.showGoodsList(page, typeID, addr,SysCode.IS_QQ.getCode());
        return RestfulResultUtils.success(page1);
    }

    /**
     * 微信群上传申请
     * @param bs_goods
     * @param imgFile
     * @return
     */
    @PostMapping("/goodsApply")
    public RestfulResult goodsApply(Bs_goods bs_goods, @RequestParam("imgFile") MultipartFile imgFile){
        try {
            Bs_person bs_person = this.getThisUser();
            if (imgFile.isEmpty()) {
                throw new CommonException(ErrorCode.NULL_ERROR,"上传图片文件为空！");
            }
            if(ZmjUtil.isNullOrEmpty(bs_goods.getIsQQ())){
                throw new CommonException(ErrorCode.NULL_ERROR,"是否为qq判断为空！");
            }
            bs_goods.setGoodsID("G_"+UUID.randomUUID().toString().toUpperCase());
            bs_goods.setState(SysCode.STATE_T.getCode());
            bs_goods.setIsAble(SysCode.IS_ABLE_WAIT.getCode());
            bs_goods.setGDateTime(DateUtil.getNowTimestamp());
            bs_goods.setGAddedTime(DateUtil.getNowTimestamp());
            bs_goods.setGClientID(bs_person.getClientID());
            bs_goods.setGUserName(bs_person.getUserName());
            bs_goods.setGCount(0L);
            bs_goodsService.goodsApply(bs_goods,imgFile);
            return RestfulResultUtils.success("上传成功！等待审核...");
        }catch (Exception e){
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 根据微信群ID获取微信群详细信息
     * @param goodsID
     * @return
     */
    @RequestMapping("/getGoodsByID")
    @ResponseBody
    public RestfulResult getGoodsByID(String goodsID){
        if (ZmjUtil.isNullOrEmpty(goodsID)) {
            throw new CommonException(ErrorCode.NULL_ERROR,"goodsID不能为空！");
        }
        EntityWrapper entityWrapper =  new EntityWrapper();
        entityWrapper.setEntity(new Bs_goods());
        entityWrapper.where("GoodsID = {0}",goodsID);
        Bs_goods bs_goods = bs_goodsService.selectOne(entityWrapper);
        return RestfulResultUtils.success(bs_goods);
    }

    /**
     * 微信群更新信息
     * @param bs_goods
     * @return
     */
    @CacheEvict(value = "getGoods",allEntries = true)
    @PostMapping("/goodsUpdateInfo")
    public RestfulResult goodsUpdateInfo(Bs_goods bs_goods){
        if (ZmjUtil.isNullOrEmpty(bs_goods)) {
            throw new CommonException(ErrorCode.NULL_ERROR,"goods不能为空！");
        }
        if (ZmjUtil.isNullOrEmpty(bs_goods.getGoodsID())) {
            throw new CommonException(ErrorCode.NULL_ERROR,"goodsID不能为空！");
        }
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_goods());
        entityWrapper.where("GoodsID = {0}",bs_goods.getGoodsID());
        bs_goods.setIsAble(SysCode.IS_ABLE_WAIT.getCode());
        bs_goodsService.update(bs_goods,entityWrapper);
        return RestfulResultUtils.success("更新成功！");
    }

    /**
     * 微信群更新二维码
     * @param goodsID
     * @param imgFile
     * @return
     */
    @CacheEvict(value = "getGoods",allEntries = true)
    @PostMapping("/goodsUpdatePic")
    public RestfulResult goodsUpdatePic(String goodsID, @RequestParam("imgFile") MultipartFile imgFile){
        if (imgFile.isEmpty()) {
            throw new CommonException(ErrorCode.NULL_ERROR,"上传图片文件为空！");
        }
        if (ZmjUtil.isNullOrEmpty(goodsID)) {
            throw new CommonException(ErrorCode.NULL_ERROR,"goodsID不能为空！");
        }
        EntityWrapper entityWrapper =  new EntityWrapper();
        entityWrapper.setEntity(new Bs_goods());
        entityWrapper.where("GoodsID = {0}",goodsID);
        Bs_goods bs_goods = bs_goodsService.selectOne(entityWrapper);
        bs_goodsService.goodsUpdatePic(bs_goods,imgFile);
        return RestfulResultUtils.success("上传二维码成功！");
    }

    @CacheEvict(value = "getGoods",allEntries = true)
    @GetMapping("/clearGoods")
    public RestfulResult clearGoods(){
        return RestfulResultUtils.success();
    }
}
