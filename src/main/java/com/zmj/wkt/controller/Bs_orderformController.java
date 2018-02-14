package com.zmj.wkt.controller;

import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_orderform;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.service.Bs_orderformService;
import com.zmj.wkt.utils.DateUtil;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
public class Bs_orderformController  extends CommonController{

    @Autowired
    Bs_orderformService bs_orderformService;

    @PostMapping("/applyOrderForm")
    public RestfulResult applyOrderForm(Bs_orderform bs_orderform, @RequestParam("imgFile") MultipartFile imgFile){
        try {
            Bs_person bs_person = this.getThisUser();
            if (imgFile.isEmpty()) {
                throw new CommonException(ErrorCode.NULL_ERROR,"上传图片文件为空！");
            }
            bs_orderform.setSubID("Sub_"+ UUID.randomUUID().toString().toUpperCase());
            bs_orderform.setState(SysCode.IS_ABLE_WAIT.getCode());
            bs_orderformService.orderFormApply(bs_orderform,imgFile);
            return RestfulResultUtils.success("上传成功！等待审核...");
        }catch (Exception e){
            throw new CommonException(e.getMessage());
        }
    }
}
