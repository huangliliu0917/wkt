package com.zmj.wkt.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zmj.wkt.common.CommonManager;
import com.zmj.wkt.common.CommonManagerImpl;
import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.mapper.Bs_goodsMapper;
import com.zmj.wkt.service.Bs_goodsService;
import com.zmj.wkt.service.Bs_personService;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
@CacheConfig(cacheNames = "Bs_goods")
public class Bs_goodsServiceImpl extends CommonManagerImpl<Bs_goodsMapper,Bs_goods> implements Bs_goodsService {

    @Autowired
    Bs_goodsMapper bs_goodsMapper;

    @Override
    public void goodsApply(Bs_goods bs_goods, MultipartFile imgFile) {
        String url = "goods-img/";
        try {
            String photoUrl = url+bs_goods.getGoodsID()+"."+ ZmjUtil.getExtensionName(imgFile.getOriginalFilename());
            uploadfile(imgFile,photoUrl);
            bs_goods.setGImage(photoUrl);
            bs_goodsMapper.insert(bs_goods);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR,"文件上传失败！IOException:"+e.getMessage());
        } catch(Exception exc){
            exc.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,"Exception:"+exc.getMessage());
        }
    }

    /**
     * 获取公共页面微信群列表
     * @param page
     * @param typeID
     * @param addr
     * @return
     */
    @Override
    public Page<Bs_goods> showGoodsList(Page<Bs_goods> page, String typeID, String addr) {
        page.setRecords(bs_goodsMapper.selectGoodsList(page,typeID,addr));
        return page;
    }

    @Override
    public void uploadfileTest(MultipartFile imgFile){
        try {
            uploadfile(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @CacheEvict(key = "#root.caches[0].name + '.ClientID:'+ #ClientID")
    public List<Bs_goods> selectGoodsListByClientID(String ClientID) {
        return bs_goodsMapper.selectGoodsListByClientID(ClientID);
    }
}
