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
 * @since 2018-05-19
 */
@TableName("sys_log")
public class Sys_log extends Model<Sys_log> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户名
     */
	private String username;
	private String url;
	private String method;
	private String ip;
	private String class_method;
	private String args;
    /**
     * 处理时间(ms)
     */
	private Long runTime;
    /**
     * 返回
     */
	private String response;
    /**
     * 返回时间
     */
	private Date endTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getClass_method() {
		return class_method;
	}

	public void setClass_method(String class_method) {
		this.class_method = class_method;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public Long getRunTime() {
		return runTime;
	}

	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Sys_log{" +
			", id=" + id +
			", username=" + username +
			", url=" + url +
			", method=" + method +
			", ip=" + ip +
			", class_method=" + class_method +
			", args=" + args +
			", runTime=" + runTime +
			", response=" + response +
			", endTime=" + endTime +
			"}";
	}
}
