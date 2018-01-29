package com.zmj.wkt.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 提现申请表
 * </p>
 *
 * @author zmj
 * @since 2018-01-29
 */
@TableName("reflect_apply")
public class Reflect_apply extends Model<Reflect_apply> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
	private String Action_no;
    /**
     * 交易币数量
     */
	private Integer Amt;
    /**
     * 支付宝账户
     */
	private String Ali_account;
    /**
     * 支付宝姓名
     */
	private String Ali_name;
    /**
     * 状态（有效0，无效1）
     */
	private Integer State;
    /**
     * 审核状态 审核通过(0)  未通过（1） 待审核（2）
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

	public Integer getAmt() {
		return Amt;
	}

	public void setAmt(Integer Amt) {
		this.Amt = Amt;
	}

	public String getAli_account() {
		return Ali_account;
	}

	public void setAli_account(String Ali_account) {
		this.Ali_account = Ali_account;
	}

	public String getAli_name() {
		return Ali_name;
	}

	public void setAli_name(String Ali_name) {
		this.Ali_name = Ali_name;
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
		return "Reflect_apply{" +
			", Action_no=" + Action_no +
			", Amt=" + Amt +
			", Ali_account=" + Ali_account +
			", Ali_name=" + Ali_name +
			", State=" + State +
			", IsAble=" + IsAble +
			", Apply_date=" + Apply_date +
			", Able_date=" + Able_date +
			"}";
	}
}
