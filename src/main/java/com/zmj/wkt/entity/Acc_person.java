package com.zmj.wkt.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户账户表
 * </p>
 *
 * @author zmj
 * @since 2018-01-29
 */
@TableName("acc_person")
public class Acc_person extends Model<Acc_person> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	private String ClientID;
    /**
     * 余额
     */
	private Integer Balance;


	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String ClientID) {
		this.ClientID = ClientID;
	}

	public Integer getBalance() {
		return Balance;
	}

	public void setBalance(Integer Balance) {
		this.Balance = Balance;
	}

	@Override
	protected Serializable pkVal() {
		return this.ClientID;
	}

	@Override
	public String toString() {
		return "Acc_person{" +
			", ClientID=" + ClientID +
			", Balance=" + Balance +
			"}";
	}
}
