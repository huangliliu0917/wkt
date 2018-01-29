package com.zmj.wkt.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商家信息表
 * </p>
 *
 * @author zmj
 * @since 2018-01-29
 */
@TableName("bs_producer")
public class Bs_producer extends Model<Bs_producer> {

    private static final long serialVersionUID = 1L;

    /**
     * 商家号
     */
	private String ProducerID;
    /**
     * 商家名
     */
	private String ProName;
    /**
     * 商家地址
     */
	private String ProAddress;
    /**
     * 商家电话
     */
	private String ProPhone;


	public String getProducerID() {
		return ProducerID;
	}

	public void setProducerID(String ProducerID) {
		this.ProducerID = ProducerID;
	}

	public String getProName() {
		return ProName;
	}

	public void setProName(String ProName) {
		this.ProName = ProName;
	}

	public String getProAddress() {
		return ProAddress;
	}

	public void setProAddress(String ProAddress) {
		this.ProAddress = ProAddress;
	}

	public String getProPhone() {
		return ProPhone;
	}

	public void setProPhone(String ProPhone) {
		this.ProPhone = ProPhone;
	}

	@Override
	protected Serializable pkVal() {
		return this.ProducerID;
	}

	@Override
	public String toString() {
		return "Bs_producer{" +
			", ProducerID=" + ProducerID +
			", ProName=" + ProName +
			", ProAddress=" + ProAddress +
			", ProPhone=" + ProPhone +
			"}";
	}
}
