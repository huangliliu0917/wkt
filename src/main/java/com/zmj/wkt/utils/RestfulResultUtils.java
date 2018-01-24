package com.zmj.wkt.utils;

import com.zmj.wkt.common.RestfulResult;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import com.zmj.wkt.utils.sysenum.SysCode;
import net.sf.json.JSONObject;

/**
 * RestfulResult对象工厂工具
 * @author zmj
 */
public class RestfulResultUtils {

    /**
     * 返回成功
     * @param object
     * @return
     */
    public static RestfulResult success(Object object){
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setStatus(SysCode.SYS_CODE_STATUS_SUCCESS.getCode());
        restfulResult.setMsg(SysCode.SYS_CODE_STATUS_SUCCESS.getDescription());
        restfulResult.setResult(object);
        return restfulResult;
    }

    /**
     * 返回成功，并且无返回实体
     * @return
     */
    public static RestfulResult success(){
        return success(null);
    }

    /**
     * 返回失败
     * @param code
     * @param msg
     * @return
     */
    public static RestfulResult error(Integer code,String msg){
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setStatus(code);
        restfulResult.setMsg(msg);
        restfulResult.setResult(null);
        return restfulResult;
    }

    /**
     * 返回失败
     * @param errorCode
     * @param msg
     * @return
     */
    public static RestfulResult error(ErrorCode errorCode, String msg){
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setStatus(errorCode.getCode());
        restfulResult.setMsg(errorCode.getDescription()+msg);
        restfulResult.setResult(null);
        return restfulResult;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}
