package com.zmj.wkt.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zmj
 * @since 2018-01-23
 */
@TableName("sys_userinfo")
public class Sys_userinfo extends Model<Sys_userinfo> {

    private static final long serialVersionUID = 1L;

	private Integer usrId;
    /**
     * 昵称
     */
	private String nickname;
    /**
     * 头像key
     */
	private String avatar_key;
    /**
     * 个人签名
     */
	private String signature;
    /**
     * 所在地一级
     */
	private String area;
    /**
     * 所在地二级
     */
	private String region;
    /**
     * 性别（男0，女1）
     */
	private String sex;
    /**
     * 生日
     */
	private Date birthday;


	public Integer getUsrId() {
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar_key() {
		return avatar_key;
	}

	public void setAvatar_key(String avatar_key) {
		this.avatar_key = avatar_key;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	protected Serializable pkVal() {
		return this.usrId;
	}

	@Override
	public String toString() {
		return "Sys_userinfo{" +
			", usrId=" + usrId +
			", nickname=" + nickname +
			", avatar_key=" + avatar_key +
			", signature=" + signature +
			", area=" + area +
			", region=" + region +
			", sex=" + sex +
			", birthday=" + birthday +
			"}";
	}
}
