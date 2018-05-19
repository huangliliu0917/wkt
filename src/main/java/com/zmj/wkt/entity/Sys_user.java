package com.zmj.wkt.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("sys_user")
public class Sys_user extends Model<Sys_user> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String username;
	private String password;
	private Long sys_role_user_id;
    /**
     * 电话号码
     */
	private String phoneNumber;
    /**
     * 位置
     */
	private String Address;
    /**
     * 真实姓名
     */
	private String realName;


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getSys_role_user_id() {
		return sys_role_user_id;
	}

	public void setSys_role_user_id(Long sys_role_user_id) {
		this.sys_role_user_id = sys_role_user_id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Sys_user{" +
			", id=" + id +
			", username=" + username +
			", password=" + password +
			", sys_role_user_id=" + sys_role_user_id +
			", phoneNumber=" + phoneNumber +
			", Address=" + Address +
			", realName=" + realName +
			"}";
	}
}
