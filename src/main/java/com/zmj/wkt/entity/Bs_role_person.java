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
@TableName("bs_role_person")
public class Bs_role_person extends Model<Bs_role_person> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String Person_id;
	private Integer Role_id;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPerson_id() {
		return Person_id;
	}

	public void setPerson_id(String Person_id) {
		this.Person_id = Person_id;
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
		return "Bs_role_person{" +
			", id=" + id +
			", Person_id=" + Person_id +
			", Role_id=" + Role_id +
			"}";
	}
}
