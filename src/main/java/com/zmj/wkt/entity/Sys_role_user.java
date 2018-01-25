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
 * @since 2018-01-25
 */
@TableName("sys_role_user")
public class Sys_role_user extends Model<Sys_role_user> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer User_id;
	private Integer Role_id;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return User_id;
	}

	public void setUser_id(Integer User_id) {
		this.User_id = User_id;
	}

	public Integer getRole_id() {
		return Role_id;
	}

	public void setRole_id(Integer Role_id) {
		this.Role_id = Role_id;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Sys_role_user{" +
			", id=" + id +
			", User_id=" + User_id +
			", Role_id=" + Role_id +
			"}";
	}
}
