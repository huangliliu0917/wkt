package com.zmj.wkt.controller;

import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.service.Bs_goods_listService;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @description :普通用户控制器
 * ---------------------------------
 */
@RestController
public class CommonUserController extends CommonController{


    @Autowired
    Bs_goods_listService bs_goods_listService;

    /**
     * 添加群到用户群列表
     * @param goodsID
     * @return
     */
    @PostMapping("/addToPersonGoodsList")
    public RestfulResult addToPersonGoodsList(String goodsID){
        try {
            bs_goods_listService.addToPersonGoodsList(goodsID,this.getThisUser().getClientID());
            return RestfulResultUtils.success("添加成功！");
        }catch (CommonException ce){
            ce.printStackTrace();
            throw ce;
        }catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,e.getMessage());
        }
    }

    /**
     * 获取用户群列表
     * @return
     */
    @GetMapping("/getPersonGoodsList")
    public RestfulResult getPersonGoodsList(){
        try {
            return RestfulResultUtils.success(bs_goods_listService.getPersonGoodsList(this.getThisUser().getClientID()));
        }catch (CommonException ce){
            ce.printStackTrace();
            throw ce;
        }catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,e.getMessage());
        }
    }

    /**
     * 删除群从用户群列表
     * @param goodsID
     * @return
     */
    @PostMapping("/delPersonGoodsList")
    public RestfulResult delPersonGoodsList(String goodsID){
        try {
            bs_goods_listService.delPersonGoodsList(goodsID,this.getThisUser().getClientID());
            return RestfulResultUtils.success("删除成功！");
        }catch (CommonException ce){
            ce.printStackTrace();
            throw ce;
        }catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,e.getMessage());
        }
    }

}
