package com.zmj.wkt.controller;

import com.taobao.api.ApiException;
import com.zmj.wkt.common.CommonController;
import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.TbkUtil;
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
public class TbkController extends CommonController {

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


}
