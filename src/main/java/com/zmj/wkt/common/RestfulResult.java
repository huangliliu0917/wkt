package com.zmj.wkt.common;

import net.sf.json.JSONObject;

/**
 * http返回restful对象
 * @author zmj
 */
public class RestfulResult<T> {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}
