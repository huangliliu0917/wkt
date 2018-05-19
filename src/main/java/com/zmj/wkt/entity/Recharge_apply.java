package com.zmj.wkt.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 充值订单申请表
 * </p>
 *
 * @author zmj
 * @since 2018-05-19
 */
@TableName("recharge_apply")
public class Recharge_apply extends Model<Recharge_apply> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
	private String Action_no;
    /**
     * 用户ID
     */
	private String ClientID;
    /**
     * 用户名
     */
	private String Username;
    /**
     * 交易币数量
     */
	private BigDecimal Amt;
    /**
     * 支付前金额
     */
	private BigDecimal BeforeBalance;
    /**
     * 支付后金额
     */
	private BigDecimal AfterBalance;
    /**
     * 支付宝订单号
     */
	private String Ali_order_number;
    /**
     * 状态（有效0，无效1）
     */
	private Integer State;
    /**
     * 审核状态 审核通过(0) 未通过（1） 待审核（2） 
     */
	private Integer IsAble;
    /**
     * 申请时间
     */
	private Date Apply_date;
    /**
     * 审核时间
     */
	private Date Able_date;


	public String getAction_no() {
		return Action_no;
	}

	public void setAction_no(String Action_no) {
		this.Action_no = Action_no;
	}

	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String ClientID) {
		this.ClientID = ClientID;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}

	public BigDecimal getAmt() {
		return Amt;
	}

	public void setAmt(BigDecimal Amt) {
		this.Amt = Amt;
	}

	public BigDecimal getBeforeBalance() {
		return BeforeBalance;
	}

	public void setBeforeBalance(BigDecimal BeforeBalance) {
		this.BeforeBalance = BeforeBalance;
	}

	public BigDecimal getAfterBalance() {
		return AfterBalance;
	}

	public void setAfterBalance(BigDecimal AfterBalance) {
		this.AfterBalance = AfterBalance;
	}

	public String getAli_order_number() {
		return Ali_order_number;
	}

	public void setAli_order_number(String Ali_order_number) {
		this.Ali_order_number = Ali_order_number;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer State) {
		this.State = State;
	}

	public Integer getIsAble() {
		return IsAble;
	}

	public void setIsAble(Integer IsAble) {
		this.IsAble = IsAble;
	}

	public Date getApply_date() {
		return Apply_date;
	}

	public void setApply_date(Date Apply_date) {
		this.Apply_date = Apply_date;
	}

	public Date getAble_date() {
		return Able_date;
	}

	public void setAble_date(Date Able_date) {
		this.Able_date = Able_date;
	}

	@Override
	protected Serializable pkVal() {
		return this.Action_no;
	}

	@Override
	public String toString() {
		return "Recharge_apply{" +
			", Action_no=" + Action_no +
			", ClientID=" + ClientID +
			", Username=" + Username +
			", Amt=" + Amt +
			", BeforeBalance=" + BeforeBalance +
			", AfterBalance=" + AfterBalance +
			", Ali_order_number=" + Ali_order_number +
			", State=" + State +
			", IsAble=" + IsAble +
			", Apply_date=" + Apply_date +
			", Able_date=" + Able_date +
			"}";
	}
}
