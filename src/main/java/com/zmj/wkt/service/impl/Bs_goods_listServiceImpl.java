package com.zmj.wkt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zmj.wkt.common.CommonManagerImpl;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_person_goods_list;
import com.zmj.wkt.mapper.Bs_goodsMapper;
import com.zmj.wkt.mapper.Bs_person_goods_listMapper;
import com.zmj.wkt.service.Bs_goods_listService;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
@CacheConfig(cacheNames = "Bs_person")
@Transactional(rollbackFor = Exception.class)
public class Bs_goods_listServiceImpl extends CommonManagerImpl<Bs_person_goods_listMapper, Bs_person_goods_list> implements Bs_goods_listService {
    @Autowired
    Bs_person_goods_listMapper bs_person_goods_listMapper;
    @Autowired
    Bs_goodsMapper bs_goodsMapper;
    /**
     * 添加群到用户群列表
     * @param GoodsID
     * @param ClientID
     * @return
     */
    @CacheEvict(key = "#root.caches[0].name + 'GoodsList.ClientID:'+ #ClientID")
    @Override
    public boolean addToPersonGoodsList(String GoodsID,String ClientID) {
        Bs_person_goods_list bs_person_goods_list = new Bs_person_goods_list();
        bs_person_goods_list.setGoodsID(GoodsID);
        bs_person_goods_list.setClientID(ClientID);
        bs_person_goods_list.setState(SysCode.STATE_T.getCode());
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_person_goods_list());
        entityWrapper.where("ClientID = {0} ",ClientID).and(" GoodsID = {0}",GoodsID);
        List selectList = bs_person_goods_listMapper.selectList(entityWrapper);
        if(ZmjUtil.isNullOrEmpty(selectList)){
            throw new CommonException(ErrorCode.ISHAVEN_ERROR,"该群已经存在于用户群列表中！");
        }
        bs_person_goods_listMapper.insert(bs_person_goods_list);
        return true;
    }

    /**
     * 获取用户群列表
     * @param ClientID
     * @return
     */
    @Cacheable(key = "#root.caches[0].name + 'GoodsList.ClientID:'+ #ClientID")
    @Override
    public List<Bs_goods> getPersonGoodsList(String ClientID) {
        return bs_goodsMapper.getBs_person_goodsByClientID(ClientID);
    }

    /**
     * 从用户群列表中删除
     * @param GoodsID
     * @param ClientID
     * @return
     */
    @Override
    @CacheEvict(key = "#root.caches[0].name + 'GoodsList.ClientID:'+ #ClientID")
    public boolean delPersonGoodsList(String GoodsID, String ClientID) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.setEntity(new Bs_person_goods_list());
        entityWrapper.where("ClientID = {0} ",ClientID).and(" GoodsID = {0}",GoodsID);
        bs_person_goods_listMapper.delete(entityWrapper);
        return true;
    }
}
