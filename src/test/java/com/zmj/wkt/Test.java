package com.zmj.wkt;

import com.baomidou.mybatisplus.annotations.TableId;
import com.taobao.api.ApiException;
import com.zmj.wkt.utils.HttpsClientUtil;
import com.zmj.wkt.utils.sysenum.SysConstant;

import java.math.BigDecimal;

import static com.zmj.wkt.utils.TbkUtil.superSearchGoods;
import static com.zmj.wkt.utils.TbkUtil.tpwdCreate;

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
public class Test {
    private static final String PID = "mm_46667186_35066962_277674842";
    @org.junit.Test
    public void main() throws ApiException {
        superSearchGoods(PID, "女装", 1L, 10L);
        /*if(mapData.get()) {
        }*/
        //flashToken();
    }

    @org.junit.Test
    public void tpwdCreateTest(){
        tpwdCreate(PID,"测试不少于5个字","https://item.taobao.com/item.htm?id=565256993523","https://img.alicdn.com/bao/uploaded/i2/2231111757/TB1JGGaX0cnBKNjSZR0XXcFqFXa_!!0-item_pic.jpg",null);
    }

    public static void change(int a, int b){
        a=2;
        b=2;
    }


    @org.junit.Test
    public void testHttps(){
        String out = null;
        String code = "011TGzj613mcUS1FrWj614ckj61TGzjn";
        String httpsResponse = HttpsClientUtil.httpsRequest("https://api.weixin.qq.com/sns/jscode2session?appid="
                + SysConstant.WX_APPID+"&secret="+SysConstant.WX_SECRET+"&js_code="+code+"&grant_type=authorization_code"
                , "GET", out);
        System.out.println(out);
        System.out.println(httpsResponse);
    }
    @org.junit.Test
    public void test4(){
        BigDecimal a= new BigDecimal("2.5");
        BigDecimal b= new BigDecimal("10");
        System.out.println(a.multiply(b));
    }
}
