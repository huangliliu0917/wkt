package com.zmj.wkt.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.taobao.api.ApiException;
import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_hotQ;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.entity.Bs_tbkCollections;
import com.zmj.wkt.mapper.Bs_tbkCollectionsMapper;
import com.zmj.wkt.service.Bs_hotQService;
import com.zmj.wkt.service.Bs_tbkCollectionsService;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.TbkUtil;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.POST;
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
 * @description :
 * ---------------------------------
 */
@RestController
public class TbkController extends CommonController {

    @Autowired
    Bs_hotQService bs_hotQService;

    @Autowired
    Bs_tbkCollectionsService bs_tbkCollectionsService;

    @Autowired
    Bs_tbkCollectionsMapper bs_tbkCollectionsMapper;
    /**
     * 获取淘宝客商品列表
     * @param Itemloc   商品归属地
     * @param Q         搜索关键字
     * @param pageNo    页码
     * @param pageSize  单页数量
     * @return
     */
    @PostMapping("/getTbkGoodsList")
    public RestfulResult getTbkGoodsList(String Itemloc , String Q ,Long pageNo ,Long pageSize) throws ApiException {
        return RestfulResultUtils.success(TbkUtil.getGoodsList2(Itemloc,Q,pageNo,pageSize));
    }

    /**
     * 生成淘口令
     * @param url
     * @param PID
     * @param text
     * @param logo
     * @return
     * @throws ApiException
     */
    @PostMapping("/tpwdCreate")
    public RestfulResult tpwdCreate(String url,String PID ,String text,String logo) throws ApiException {
        Long adzoneId = Long.valueOf(PID.split("_")[3]);
        System.out.println(adzoneId);
        return  RestfulResultUtils.success(TbkUtil.tpwdCreate2(adzoneId,text,url,logo,null));
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
}
