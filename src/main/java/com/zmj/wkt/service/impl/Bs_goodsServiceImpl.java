package com.zmj.wkt.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
