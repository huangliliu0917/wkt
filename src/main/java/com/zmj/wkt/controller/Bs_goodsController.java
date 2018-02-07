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
    @Cacheable(value = "getGoods",key = "#root.caches[0].name + ':' + #typeID +','+ #addr+ ','+#pages +','+ #amount")
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
        Page page1 = bs_goodsService.showGoodsList(page, typeID, addr);
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
            bs_goods.setGoodsID("G_"+UUID.randomUUID().toString().toUpperCase());
            bs_goods.setState(SysCode.STATE_T.getCode());
            bs_goods.setIsAble(SysCode.IS_ABLE_WAIT.getCode());
            bs_goods.setGDateTime(DateUtil.getNowTimestamp());
            bs_goods.setGClientID(bs_person.getClientID());
            bs_goods.setGUserName(bs_person.getUserName());
            bs_goods.setGCount(0L);
            bs_goodsService.goodsApply(bs_goods,imgFile);
            return RestfulResultUtils.success("上传成功！等待审核...");
        }catch (Exception e){
            throw new CommonException(e.getMessage());
        }
    }



}
