package com.zmj.wkt.utils;

import com.zmj.wkt.utils.sysenum.SysConstant;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
public class WXUtil {
    public static String getWXcode(String code){
        Logger logger = LoggerFactory.getLogger(WXUtil.class);
        String ext = null;
        String httpsResponse = HttpsClientUtil.httpsRequest("https://api.weixin.qq.com/sns/jscode2session?appid="
                        + SysConstant.WX_APPID+"&secret="+SysConstant.WX_SECRET+"&js_code="+code+"&grant_type=authorization_code"
                , "GET", ext);
        JSONObject jsonObject = JSONObject.fromObject(httpsResponse);
        logger.info("getWXcode = {}",httpsResponse);
        return jsonObject.get("openid").toString();
    }
}
