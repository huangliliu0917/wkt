package com.zmj.wkt.socket.service;

import com.zmj.wkt.utils.RestfulResultUtils;
import com.zmj.wkt.utils.socket.IsoStrManager;
import org.springframework.stereotype.Component;

import java.net.Socket;

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
 * @description : 各种socket和json http请求的实现类
 * ---------------------------------
 */
@Component("IsoStrManagerImpl")
public class IsoStrManagerImpl implements IsoStrManager{
    /**
     * 处理9090端口接收到的socket请求
     * 处理/json接收到的http请求
     * 的方法
     * @param params 入参
     * @param socket
     * @return
     */
    @Override
    public String deal(String params, Socket socket) {
        return RestfulResultUtils.success(params).toString();
    }
}
