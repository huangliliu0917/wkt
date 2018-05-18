package com.zmj.wkt.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.service.RegionService;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.sysenum.SysCode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zmj
 * @since 2018-04-27
 */
@RestController
@RequestMapping("/noRoot/region")
public class RegionController extends CommonController {
    @Resource
    RegionService regionService;

    @RequestMapping("/getProvince")
    @Cacheable(cacheNames = "region",key= "'Province'")
    public RestfulResult getProvince(){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.where("parent_id = {0}", SysCode.PROVINCE.getCode());
        return RestfulResultUtils.success(regionService.selectList(entityWrapper));
    }

    @PostMapping("/getCity")
    @Cacheable(cacheNames = "region",key= "'getCity-PID:'+#provinceID")
    public RestfulResult getCity(String provinceID){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.where("parent_id = {0}", provinceID);
        return RestfulResultUtils.success(regionService.selectList(entityWrapper));
    }

    @PostMapping("/getRegion")
    @Cacheable(cacheNames = "region",key= "'getRegion-PID:'+#cityID")
    public RestfulResult getRegion(String cityID){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.where("parent_id = {0}", cityID);
        return RestfulResultUtils.success(regionService.selectList(entityWrapper));
    }

}

