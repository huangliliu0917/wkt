package com.zmj.wkt.entity.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@Data
public class FavoritesItem {

    /**
     * category : 16
     * clickUrl : https://s.click.taobao.com/t?e=m%3D2%26s%3Ddm4dfE6OhfYcQipKwQzePOeEDrYVVa64yK8Cckff7TW3bLqV5UHdqWCa7rkxLtwWdgpT%2Fnt4ZAiwbIhoyhy8kyl%2Bvqv79GEPtv3tKGkOB2R8jy0qlxGJDnU1h4xTXu4OPTItY8iltKNZ4n0e03qrklW8hb0G%2BvE1JpTeMbkYqgCT%2BapxR478HIUyQ%2FAO9%2BEio7OhP%2FAQMcsMFsnj4TyTCR8VX9EcNvRYqCuV3sXoiburxUaQ5%2FG9Wuif5wNYfj84IeuJTrU35XhjuWy8qOiYOyGFCzYOOqAQ
     * commissionRate : null
     * couponClickUrl : https://uland.taobao.com/coupon/edetail?e=ScpK%2FymirMoGQASttHIRqUBKCJIW%2BkcAvjbcefPpOsFYkDPSXp14z%2BP4ZQYn3vRC9ZuGR23f67MP4f3DzektvpQ5wfGz%2Fu%2BNjWdexgJqXaR%2B%2BRpHXGj9HQ%3D%3D
     * couponEndTime : 2018-06-12
     * couponInfo : 满20元减5元
     * couponRemainCount : null
     * couponStartTime : null
     * couponTotalCount : 10000
     * eventEndTime : 1528819199000
     * eventStartTime : 1526227200000
     * itemUrl : http://item.taobao.com/item.htm?id=566240131958
     * nick : yearpeach岳桃旗舰店
     * numIid : 566240131958
     * pictUrl : https://img.alicdn.com/tfscom/i4/3695567017/TB27WKYhWSWBuNjSsrbXXa0mVXa_!!3695567017-0-item_pic.jpg
     * provcity : 上海
     * reservePrice : 99.00
     * sellerId : 3695567017
     * shopTitle : yearpeach岳桃旗舰店
     * smallImages : ["https://img.alicdn.com/tfscom/i1/3695567017/TB18AcJd3mTBuNjy1XbXXaMrVXa_!!0-item_pic.jpg","https://img.alicdn.com/tfscom/i1/3695567017/TB2wAOFh79WBuNjSspeXXaz5VXa_!!3695567017.jpg","https://img.alicdn.com/tfscom/i3/3695567017/TB2awYuh4GYBuNjy0FnXXX5lpXa_!!3695567017.jpg","https://img.alicdn.com/tfscom/i4/3695567017/TB2M.eYpKSSBuNjy0FlXXbBpVXa_!!3695567017.jpg"]
     * status : 1
     * title : ins超火短袖t恤女学生韩版ulzzang百搭宽松夏情侣装新款半袖上衣
     * tkRate : 30.50
     * type : 2
     * userType : 1
     * volume : 8315
     * zkFinalPrice : 27.80
     * zkFinalPriceWap : 27.80
     */

    private String category;
    private String clickUrl;
    private String commissionRate;
    private String couponClickUrl;
    private String couponEndTime;
    private String couponInfo;
    private String couponRemainCount;
    private String couponStartTime;
    private String couponTotalCount;
    private long eventEndTime;
    private long eventStartTime;
    private String itemUrl;
    private String nick;
    private long numIid;
    private String pictUrl;
    private String provcity;
    private String reservePrice;
    private long sellerId;
    private String shopTitle;
    private int status;
    private String title;
    private String tkRate;
    private int type;
    private int userType;
    private int volume;
    private String zkFinalPrice;
    private String zkFinalPriceWap;
    private List<String> smallImages;
}
