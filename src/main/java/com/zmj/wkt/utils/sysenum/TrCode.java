package com.zmj.wkt.utils.sysenum;

import com.zmj.wkt.utils.CodeInterface;

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
public enum TrCode implements CodeInterface {

    RACHARGE("10001","充值"),
    REFLECT("10002","提现"),
    WITHHOLDING("10003","代扣"),
    REFUND("10004","退款"),
    ;

    /**
     * 说明
     */
    String description;

    /**
     * 代码
     */
    String code;

    TrCode() {
    }

    TrCode(String code, String description) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getInfo() {
        return this.description;
    }

    @Override
    public void print() {
        System.out.println(this.code + ":" + this.description);
    }

    public static TrCode codeOf(String code) {
        for (TrCode codes : values()) {
            if (codes.getCode() == code) {
                return codes;
            }
        }

        return null;
    }

    }
