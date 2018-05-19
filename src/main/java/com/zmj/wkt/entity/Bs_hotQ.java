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
@TableName("bs_hotQ")
public class Bs_hotQ extends Model<Bs_hotQ> {

    private static final long serialVersionUID = 1L;

    /**
     * 父节点ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String name;
    /**
     * 父节点ID
     */
	private Integer parentId;
    /**
     * 等级
     */
	private Integer level;


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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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
			", parentId=" + parentId +
			", level=" + level +
			"}";
	}
}
