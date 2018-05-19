package com.zmj.wkt.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_orderform;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.entity.Person_center;
import com.zmj.wkt.service.Bs_goodsService;
import com.zmj.wkt.service.Bs_orderformService;
import com.zmj.wkt.service.Person_centerService;
import com.zmj.wkt.utils.DateUtil;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.zmj.wkt.common.CommonController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *  用户中心
 * @author zmj
 * @since 2018-05-19
 */
@RestController
public class Person_centerController extends CommonController {
    @Autowired
    Bs_orderformService bs_orderformService;

    @Autowired
    Bs_goodsService bs_goodsService;

    @Autowired
    Person_centerService person_centerService;
    /**
     * 用户中心获取一周订单
     * @return
     */
    @GetMapping("/getAWeekOrder")
    public RestfulResult getAWeekOrder() throws Exception {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        date = calendar.getTime();
        List<Bs_orderform> bs_orderforms = bs_orderformService.selectList(
                new EntityWrapper<Bs_orderform>()
                        .where("ClientID = {0} and SpDate>={1} ", getThisUser().getClientID(), date)
                        .and().where("IsAble = {0}", SysCode.IS_ABLE_YES.getCode())
                        .and().like("SubID","Sub_%")
        );
        return RestfulResultUtils.success(bs_orderforms);
    }

    /**
     * 添加到用户中心
     * @return
     * @throws Exception
     */
    @GetMapping("/addPersonCenter")
    public RestfulResult addPersonCenter(String SubID) throws Exception {
        if(ZmjUtil.isNullOrEmpty(SubID)){
            throw new CommonException(ErrorCode.VERIFY_ERROR,"SubID不能为空");
        }
        Person_center person_center = new Person_center();
        person_center.setClientID(getThisUser().getClientID());
        person_center.setSubID(SubID);
        return  RestfulResultUtils.success(person_centerService.insert(person_center));
    }

    /**
     * 查询用户中心
     * @return
     * @throws Exception
     */
    @GetMapping("/showPersonCenter")
    public RestfulResult showPersonCenter() throws Exception {
        List<Person_center> person_centers = person_centerService.selectList(new EntityWrapper<Person_center>().
                where("ClientID = {0}", getThisUser().getClientID()));
        List<String> collect = person_centers
                .stream()
                .map(person_center -> person_center.getSubID())
                .collect(Collectors.toList());
        List<Bs_orderform> bs_orderformList = bs_orderformService.selectList(new EntityWrapper<Bs_orderform>()
                .in("SubID", collect));
        return  RestfulResultUtils.success(bs_orderformList);
    }

    /**
     * 用户中心生成订单
     * @return
     * @throws Exception
     */
    @PostMapping("/createCenterOrder")
    public RestfulResult createCenterOrder(@NotNull(message = "订单不能为空") Bs_orderform newOrder) throws Exception {
        Bs_orderform bs_orderform = bs_orderformService.selectOne(new EntityWrapper<Bs_orderform>()
                .where("SubID = {0}", newOrder.getSubID())
        );
        Bs_person bs_person = this.getThisUser();
        bs_orderform.setClientID(bs_person.getClientID());
        bs_orderform.setConsumerUserName(bs_person.getUserName());
        bs_orderform.setSubID("SubC_"+ UUID.randomUUID().toString().toUpperCase());
        //不需要审核
        bs_orderform.setState(SysCode.STATE_TO_BE_SENT.getCode());
        bs_orderform.setIsAble(SysCode.IS_ABLE_YES.getCode());
        //获取最新单价
        Bs_goods bs_goods = bs_goodsService.selectOne(new EntityWrapper<Bs_goods>().where("GoodsID = {0}", bs_orderform.getGoodsID()));
        bs_orderform.setSpPrice(bs_goods.getGPrice()*newOrder.getSpCount());
        //申请时间
        bs_orderform.setSpDate(DateUtil.getNowTimestamp());
        if(bs_goods.getGCount()+newOrder.getSpCount()>bs_goods.getGMaxCount()){
            throw new CommonException("超出最大接单数！");
        }
        bs_orderformService.orderFormApply(bs_orderform);
        return RestfulResultUtils.success("上传成功！");
    }
}

