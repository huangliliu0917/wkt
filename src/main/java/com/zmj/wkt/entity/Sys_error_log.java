package com.zmj.wkt.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zmj
 * @since 2018-01-30
 */
@TableName("sys_error_log")
public class Sys_error_log extends Model<Sys_error_log> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 错误码
     */
	private String errorCode;
    /**
     * 错误信息
     */
	private String message;
    /**
     * 发送时间
     */
	private Date datetime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Sys_error_log{" +
			", id=" + id +
			", errorCode=" + errorCode +
			", message=" + message +
			", datetime=" + datetime +
			"}";
	}
}
