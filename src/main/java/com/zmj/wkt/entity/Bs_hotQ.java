package com.zmj.wkt.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zmj
 * @since 2018-03-14
 */
@TableName("bs_hotQ")
public class Bs_hotQ extends Model<Bs_hotQ> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Bs_hotQ{" +
			", id=" + id +
			", name=" + name +
			"}";
	}
}
