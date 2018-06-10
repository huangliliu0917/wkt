package com.zmj.wkt.entity.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@Data
public class TpwdItem {
    /**
     * 标题
     */
    private String title;
    /**
     * 一口价
     */
    private String final_price;
    /**
     * 折扣信息
     */
    private String couponInfo;
    private String PID;
    /**
     * 推广连接
     */
    private String coupon_click_url;
    /**
     * 图片地址
     */
    private String pict_url;
}
