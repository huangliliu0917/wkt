package com.zmj.wkt.common;

import com.baomidou.mybatisplus.service.IService;
import com.zmj.wkt.common.exception.CommonException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 所有service继承CommonManager
 * @author zmj
 */
public interface CommonManager<T> extends IService<T> {

    /**
     * @param file
     * @param fileName
     * @throws CommonException
     * @throws IOException
     */
    public void uploadfile(MultipartFile file, String fileName) throws CommonException, IOException;

    /**
     * @param file
     * @throws CommonException
     * @throws IOException
     */
    public void uploadfile(MultipartFile file) throws CommonException, IOException;
}
