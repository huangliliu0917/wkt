package com.zmj.wkt.utils;

import com.taobao.api.ApiException;
import org.junit.Before;
import org.junit.Test;

import static com.zmj.wkt.utils.TbkUtil.superSearchGoods;
import static org.junit.Assert.*;

public class TbkUtilTest {

    private static final String PID = "mm_46667186_35066962_277674842";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getGoodsList() {
        TbkUtil.getGoodsList(PID,"女装",5L,10L);
    }



    @Test
    public void superSearchGoodsTest() throws ApiException {
        superSearchGoods(PID, "女装", 1L, 10L);
    }

    @Test
    public void createTbk(){
        TbkUtil.tpwdCreate(PID,"超过五个字","https://uland.taobao.com/coupon/edetail?e=jTmjlJu0yGEGQASttHIRqULsFNb3E5eTOXSOpj6L0gwYXB958FVfbJeiiGHnFiq0k%2FmVDemyVJhJEGIFvOvz0dxjhytSuPkU819cieM8MLYzX9XvQ%2B%2BZNtp2CXHQSjfhpQhdzDp3kuyjIKjt4WBlwn%2Ftp4YtWGnIgzek6E5ygAdIH07HK3v5wL5nHBJPjqnHHenmCXB7bXo%3D&traceId=0ab23b7615226498718506162","","");
    }

}