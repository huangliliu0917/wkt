package com.zmj.wkt.common;

import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zmj.wkt.common.aliOSS.HelloOSS;
import com.zmj.wkt.common.exception.CommonException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
public class CommonManagerImpl  <M extends BaseMapper<T>, T>  extends ServiceImpl<M,T> implements CommonManager <T> {
    static Logger logger = Logger.getLogger(CommonManagerImpl.class);

    /**
     * 文件上传方法
     * @param file
     * @param fileName
     * @throws CommonException
     * @throws IOException
     */
    public void uploadfile(MultipartFile file, String fileName) throws CommonException, IOException {
        PropertyConfigurator.configure("conf/log4j-AliOSS.properties");
        OSSClient ossClient = new OSSClient(HelloOSS.ENDPOINT, HelloOSS.ACCESS_KEY_ID, HelloOSS.ACCESS_KEY_SECRET);
        ossClient.putObject(HelloOSS.BUCKET_NAME, fileName, file.getInputStream());
        logger.info("Object：" + fileName + "存入OSS成功。");
        ossClient.shutdown();
    }

    /**
     * 文件上传方法
     * @param file
     * @throws CommonException
     * @throws IOException
     */
    public void uploadfile(MultipartFile file) throws CommonException, IOException {
        PropertyConfigurator.configure("conf/log4j-AliOSS.properties");
        OSSClient ossClient = new OSSClient(HelloOSS.ENDPOINT, HelloOSS.ACCESS_KEY_ID, HelloOSS.ACCESS_KEY_SECRET);
        ossClient.putObject(HelloOSS.BUCKET_NAME, file.getOriginalFilename(), file.getInputStream());
        logger.info("Object：" + file.getOriginalFilename() + "存入OSS成功。");
        ossClient.shutdown();
    }
}
