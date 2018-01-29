package com.zmj.wkt.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 交易日志表
 * </p>
 *
 * @author zmj
 * @since 2018-01-29
 */
@TableName("acc_daybook")
public class Acc_daybook extends Model<Acc_daybook> {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
	private String Action_no;
    /**
     * 交易代码(1001 充值 1002提现)
     */
	private String tr_code;
    /**
     * 备注
     */
	private String Note;
    /**
     * 交易币数量
     */
	private Integer Amt;
    /**
     * 支付宝订单号
     */
	private String Ali_order_number;
    /**
     * 支付宝账户
     */
	private String Ali_account;
    /**
     * 支付宝姓名
     */
	private String Ali_name;
    /**
     * 申请时间
     */
	private Date Apply_date;
    /**
     * 审核时间
     */
	private Date Able_date;
    /**
     * 时间
     */
	private Date Acc_date;
    /**
     * 是否有效（0有效 1无效 9灰记录）
     */
	private Integer State;


	public String getAction_no() {
		return Action_no;
	}

	public void setAction_no(String Action_no) {
		this.Action_no = Action_no;
	}

	public String getTr_code() {
		return tr_code;
	}

	public void setTr_code(String tr_code) {
		this.tr_code = tr_code;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String Note) {
		this.Note = Note;
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

	public Date getAcc_date() {
		return Acc_date;
	}

	public void setAcc_date(Date Acc_date) {
		this.Acc_date = Acc_date;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer State) {
		this.State = State;
	}

	@Override
	protected Serializable pkVal() {
		return this.Action_no;
	}

	@Override
	public String toString() {
		return "Acc_daybook{" +
			", Action_no=" + Action_no +
			", tr_code=" + tr_code +
			", Note=" + Note +
			", Amt=" + Amt +
			", Ali_order_number=" + Ali_order_number +
			", Ali_account=" + Ali_account +
			", Ali_name=" + Ali_name +
			", Apply_date=" + Apply_date +
			", Able_date=" + Able_date +
			", Acc_date=" + Acc_date +
			", State=" + State +
			"}";
	}
}
