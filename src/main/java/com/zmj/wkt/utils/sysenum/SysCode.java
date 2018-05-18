package com.zmj.wkt.utils.sysenum;

import com.zmj.wkt.utils.CodeInterface;

/**
 * @author zmj
 */

public enum SysCode implements CodeInterface {

    //系统返回代码
    SYS_CODE_STATUS_SUCCESS(200, "成功"),

    //性别区分
    MAN(0, "男"),
    WOMAN(1, "女"),
    SLIDESHOW_STATE_ON(0,"启用"),
    SLIDESHOW_STATE_OFF(1,"关闭"),

    //State
    STATE_T(0,"有效"),
    STATE_F(1,"无效"),
    STATE_TO_BE_SENT(2,"待发送"),
    STATE_TO_SUCCESS(3,"发送成功"),
    STATE_TO_FAILURE(4,"发送失败"),
    STATE_TO_PAY(5,"确认支付"),
    //Able
    IS_ABLE_YES(0,"已审核"),
    IS_ABLE_NO(1,"未通过"),
    IS_ABLE_WAIT(2,"待审核"),

    //是否为QQ群 0不是qq 1 是qq
    IS_QQ(1,"是QQ群"),
    IS_NOT_QQ(0,"不是QQ群"),

    //省市区
    PROVINCE(1,"省"),
    ;

    /**
     * 说明
     */
    String description;

    /**
     * 代码
     */
    int code;

    SysCode() {
    }

    SysCode(int code, String description) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public static SysCode codeOf(int code) {
        for (SysCode codes : values()) {
            if (codes.getCode() == code) {
                return codes;
            }
        }

        return null;
    }

}
