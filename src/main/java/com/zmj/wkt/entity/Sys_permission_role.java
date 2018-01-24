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
 * @since 2018-01-23
 */
@TableName("sys_permission_role")
public class Sys_permission_role extends Model<Sys_permission_role> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer role_id;
	private Integer permissino_id;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getPermissino_id() {
		return permissino_id;
	}

	public void setPermissino_id(Integer permissino_id) {
		this.permissino_id = permissino_id;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Sys_permission_role{" +
			", id=" + id +
			", role_id=" + role_id +
			", permissino_id=" + permissino_id +
			"}";
	}
}
