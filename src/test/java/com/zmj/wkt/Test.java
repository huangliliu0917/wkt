package com.zmj.wkt;

import com.taobao.api.ApiException;
import com.taobao.api.response.TbkScMaterialOptionalResponse;

import java.util.List;

import static com.zmj.wkt.utils.TbkUtil.flashToken;
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
    public void testTpwdCreate(){
        tpwdCreate(PID,"测试不少于5个字","https://item.taobao.com/item.htm?id=565256993523","https://img.alicdn.com/bao/uploaded/i2/2231111757/TB1JGGaX0cnBKNjSZR0XXcFqFXa_!!0-item_pic.jpg",null);
    }

    public static void change(int a, int b){
        a=2;
        b=2;
    }

}
