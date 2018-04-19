package com.zmj.wkt.utils;

import com.taobao.api.ApiException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.access.method.P;

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
        TbkUtil.tpwdCreate(PID,"测试佣金测试佣金","https://uland.taobao.com/coupon/edetail?e=2VozdOtTjW0GQASttHIRqTzDB0Z%2BuefLFLL9jvAjpwxYkDPSXp14z%2BP4ZQYn3vRC9ZuGR23f67MP4f3DzektvpQ5wfGz%2Fu%2BNjWdexgJqXaR%2B%2BRpHXGj9HQ%3D%3D","https://img.alicdn.com/tfscom/i4/3016738851/TB1N1AGgBfH8KJjy1XbXXbLdXXa_!!0-item_pic.jpg","");
    }

    @Test
    public void favoritesGet() throws ApiException {
        TbkUtil.favoritesGet();
    }

    @Test
    public void favoritesItemGet() throws ApiException {
        TbkUtil.favoritesItemGet(PID,17006808L,1L,10L);
    }

    @Test
    public void getGoodInfo() throws ApiException {
        String numIids = "544695836279";
        TbkUtil.getGoodInfo(numIids);
    }
}