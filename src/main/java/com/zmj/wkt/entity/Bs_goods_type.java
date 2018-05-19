package com.zmj.wkt.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 微信群（商品）类别表
 * </p>
 *
 * @author zmj
 * @since 2018-05-19
 */
@TableName("bs_goods_type")
public class Bs_goods_type extends Model<Bs_goods_type> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品类别号
     */
	private Long TypeID;
    /**
     * 类别描述
     */
	private String TypeName;


	public Long getTypeID() {
		return TypeID;
	}

	public void setTypeID(Long TypeID) {
		this.TypeID = TypeID;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String TypeName) {
		this.TypeName = TypeName;
	}

	@Override
	protected Serializable pkVal() {
		return this.TypeID;
	}

	@Override
	public String toString() {
		return "Bs_goods_type{" +
			", TypeID=" + TypeID +
			", TypeName=" + TypeName +
			"}";
	}
}
