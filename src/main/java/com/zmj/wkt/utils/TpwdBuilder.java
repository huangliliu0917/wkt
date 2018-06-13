package com.zmj.wkt.utils;

import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.entity.Bs_tbkCollections;
import com.zmj.wkt.entity.domain.FavoritesItem;
import com.zmj.wkt.entity.domain.TpwdItem;

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
public class TpwdBuilder {

    public TpwdBuilder() {

    }

    /**
     * 构造淘口令
     */
    public static void buildTpwd(StringBuilder tpwd , int i , TpwdItem item) {
        tpwd.append(i);
        tpwd.append(".");
        tpwd.append("\r\n");
        tpwd.append("商品:").append(item.getTitle());
        tpwd.append("\r\n");
        tpwd.append("【一口价】:").append(item.getFinal_price()).append("元");
        tpwd.append("\r\n");
        tpwd.append("【优惠券】:").append(item.getCouponInfo());
        tpwd.append("\r\n");
        tpwd.append("【劵后价】:一口价 — 优惠劵");
        tpwd.append("\r\n");
        tpwd.append("复制这条淘口令，[打开手机淘宝]即可领券下单，淘口令:");
        tpwd.append("\r\n");
        tpwd.append(TbkUtil.tpwdCreate(item.getPID(), item.getTitle(), item.getCoupon_click_url(), item.getPict_url(), null));
        tpwd.append("\r\n");
        tpwd.append("★提示:本内容发送给朋友，同样可享受内部优惠");
        tpwd.append("\r\n");
        tpwd.append("======================");
        tpwd.append("\r\n");
    }

    public static TpwdItem convertBs_tbkCollections2Tpwd(Bs_tbkCollections bs_tbkCollections, Bs_person bs_person){
        TpwdItem tpwdItem = new TpwdItem();
        if(ZmjUtil.isNullOrEmpty(bs_tbkCollections)){
            throw new CommonException("转化出错！");
        }else if(ZmjUtil.isNullOrEmpty(bs_tbkCollections.getCoupon_click_url())){
            throw new CommonException("该商品没有推广链接！");
        }
        tpwdItem.setCoupon_click_url(bs_tbkCollections.getCoupon_click_url());
        tpwdItem.setCouponInfo(bs_tbkCollections.getCouponInfo());
        tpwdItem.setPID(bs_person.getPID());
        tpwdItem.setPict_url(bs_tbkCollections.getPict_url());
        tpwdItem.setTitle(bs_tbkCollections.getTitle());
        tpwdItem.setFinal_price(String.valueOf(bs_tbkCollections.getZk_final_price()));
        return tpwdItem;
    }

    public static TpwdItem convertFavoritesItem2Tpwd(FavoritesItem favoritesItem){
        TpwdItem tpwdItem = new TpwdItem();
        if(ZmjUtil.isNullOrEmpty(favoritesItem)){
            throw new CommonException("转化出错！");
        }else if(ZmjUtil.isNullOrEmpty(favoritesItem.getCouponClickUrl())){
            throw new CommonException("该商品没有推广链接！");
        }
        tpwdItem.setCoupon_click_url(favoritesItem.getCouponClickUrl());
        tpwdItem.setCouponInfo(favoritesItem.getCouponInfo());
        tpwdItem.setPID(TbkUtil.DEFULT_PID);
        tpwdItem.setPict_url(favoritesItem.getPictUrl());
        tpwdItem.setTitle(favoritesItem.getTitle());
        tpwdItem.setFinal_price(String.valueOf(favoritesItem.getZkFinalPrice()));
        return tpwdItem;
    }
}
