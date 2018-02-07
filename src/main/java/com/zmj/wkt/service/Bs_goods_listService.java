package com.zmj.wkt.service;

import com.zmj.wkt.common.CommonManager;
import com.zmj.wkt.entity.Acc_person;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_person_goods_list;

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
public interface Bs_goods_listService extends CommonManager<Bs_person_goods_list> {
    /**
     * 添加到个人群列表
     * @param GoodsID
     * @param ClientID
     * @return
     */
    public boolean addToPersonGoodsList(String GoodsID,String ClientID);

    /**
     * 获取用户微信群列表
     * @param ClientID
     * @return
     */
    public List<Bs_goods> getPersonGoodsList(String ClientID);

    /**
     * 从用户群列表中删除
     * @param GoodsID
     * @param ClientID
     * @return
     */
    public boolean delPersonGoodsList(String GoodsID,String ClientID) ;
}
