package com.zmj.wkt.service.impl;

import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_goods;
import com.zmj.wkt.entity.Bs_orderform;
import com.zmj.wkt.mapper.Bs_goodsMapper;
import com.zmj.wkt.mapper.Bs_orderformMapper;
import com.zmj.wkt.service.Bs_orderformService;
import com.zmj.wkt.common.CommonManagerImpl;
import com.zmj.wkt.utils.ZmjUtil;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2018-02-14
 */
@Service
public class Bs_orderformServiceImpl extends CommonManagerImpl<Bs_orderformMapper, Bs_orderform> implements Bs_orderformService {

    @Autowired
    Bs_orderformMapper bs_orderformMapper;

    @Override
    public void orderFormApply(Bs_orderform bs_orderform, MultipartFile imgFile) {
        String url = "order-img/";
        try {
            String photoUrl = url+bs_orderform.getSubID()+"."+ ZmjUtil.getExtensionName(imgFile.getOriginalFilename());
            uploadfile(imgFile,photoUrl);
            bs_orderform.setItemPic(photoUrl);
            bs_orderformMapper.insert(bs_orderform);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR,"文件上传失败！IOException:"+e.getMessage());
        } catch(Exception exc){
            exc.printStackTrace();
            throw new CommonException(ErrorCode.UNKNOWNS_ERROR,"Exception:"+exc.getMessage());
        }
    }
}
