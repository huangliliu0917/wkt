package com.zmj.wkt.utils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.request.WirelessShareTpwdCreateRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkItemGetResponse;
import com.taobao.api.response.TbkTpwdCreateResponse;
import com.taobao.api.response.WirelessShareTpwdCreateResponse;

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
public class TbkUtil {

    //正式环境
    private static final String URL = "https://eco.taobao.com/router/rest";
    //测试环境
    //private static final String URL = "https://gw.api.tbsandbox.com/router/rest";
    private static final String APPKEY = "24576611";
    private static final String SECRET = "20171995c8a67b8fecc47058c616704b";
    private static final String PID = "mm_46667186_33352377_118692248";

    /**
     * 生成淘口令
     * @param userId
     * @param text
     * @param url
     * @param logo
     * @param ext
     * @return
     */
    public static String tpwdCreate(String userId,String text,String url,String logo,String ext){
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setUserId(userId);
        req.setText(text);
        req.setUrl(url);
        req.setLogo(logo);
        req.setExt(ext);
        TbkTpwdCreateResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
        return  rsp.getBody();
    }

    /**
     * 获取商品列表
     * @param adzoneId
     * @param platform
     * @param pageSize
     * @param pageNo
     * @return
     */
    public static String getGoodsList(Long adzoneId,Long platform,Long pageNo ,Long pageSize){
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
        req.setAdzoneId(adzoneId);
        req.setPlatform(platform);
        //req.setCat("16,18");
        req.setPageSize(pageSize);
        //req.setQ("女装");
        req.setPageNo(pageNo);
        TbkDgItemCouponGetResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
        return rsp.getBody();
    }

    public static void test() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        WirelessShareTpwdCreateRequest req = new WirelessShareTpwdCreateRequest();
        WirelessShareTpwdCreateRequest.GenPwdIsvParamDto obj1 = new WirelessShareTpwdCreateRequest.GenPwdIsvParamDto();
        obj1.setExt("{\"xx\":\"xx\"}");
        obj1.setLogo("https://img.alicdn.com//tfscom//i3//1981819532//TB1siNISFXXXXaoXVXXXXXXXXXX_!!0-item_pic.jpg");
        obj1.setUrl("https://item.taobao.com//item.htm?id=551625849471");
        obj1.setText("超值活动，惊喜活动多多");
        obj1.setUserId(131267237L);
        req.setTpwdParam(obj1);
        WirelessShareTpwdCreateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());

    }


    public static void main(String[] args) throws ApiException {
        //测试生成淘口令
        tpwdCreate("131267237","长度大于5个字符","https://item.taobao.com//item.htm?id=551625849471","https://img.alicdn.com//tfscom//i3//1981819532//TB1siNISFXXXXaoXVXXXXXXXXXX_!!0-item_pic.jpg","{}");

        //测试商品列表获取
        Long adzoneId = Long.valueOf(PID.split("_")[3]);
        System.out.println(adzoneId);
        getGoodsList(adzoneId,1L,1L,10L);

        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkItemGetRequest req = new TbkItemGetRequest();
        req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick,tk_rate");
        req.setQ("女装");
        req.setCat("16,18");
        req.setItemloc("杭州");
        req.setSort("tk_rate_des");
        req.setIsTmall(false);
        req.setIsOverseas(false);
        req.setStartPrice(1L);
        req.setEndPrice(1000L);
        req.setStartTkRate(1000L);
        req.setEndTkRate(10L);
        req.setPlatform(1L);
        req.setPageNo(1L);
        req.setPageSize(20L);
        TbkItemGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());

        test();
    }
}
