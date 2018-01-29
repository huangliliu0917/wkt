package com.zmj.wkt.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 充值订单申请表
 * </p>
 *
 * @author zmj
 * @since 2018-01-29
 */
@TableName("recharge_apply")
public class Recharge_apply extends Model<Recharge_apply> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
	@TableId(value="Action_no", type= IdType.AUTO)
	private String Action_no;
    /**
     * 交易币数量
     */
	private Integer Amt;
    /**
     * 支付宝订单号
     */
	private String Ali_order_number;
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
			", Amt=" + Amt +
			", Ali_order_number=" + Ali_order_number +
			", State=" + State +
			", IsAble=" + IsAble +
			", Apply_date=" + Apply_date +
			", Able_date=" + Able_date +
			"}";
	}
}
