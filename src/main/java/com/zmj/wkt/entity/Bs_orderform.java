package com.zmj.wkt.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author zmj
 * @since 2018-01-25
 */
@TableName("bs_orderform")
public class Bs_orderform extends Model<Bs_orderform> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
	private String SubID;
    /**
     * 客户号
     */
	private String ClientID;
    /**
     * 提供者账号
     */
	private String ProductUserName;
    /**
     * 商品号
     */
	private String GoodsID;
    /**
     * 送货地址
     */
	private String RAddress;
    /**
     * 订购日期
     */
	private Date SpDate;
    /**
     * 订购数量
     */
	private Integer SpCount;
    /**
     * 单价
     */
	private Double GPrice;
    /**
     * 发票号
     */
	private Long ReceiptID;
    /**
     * 订单金额
     */
	private Double SpPrice;
    /**
     * 发货时间
     */
	private Date SendDate;
    /**
     * 付款方式
     */
	private String Payment;
    /**
     * 运送方式
     */
	private String SendType;
    /**
     * 邮费
     */
	private Double SendPrice;
    /**
     * 订单状态
     */
	private Integer State;


	public String getSubID() {
		return SubID;
	}

	public void setSubID(String SubID) {
		this.SubID = SubID;
	}

	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String ClientID) {
		this.ClientID = ClientID;
	}

	public String getProductUserName() {
		return ProductUserName;
	}

	public void setProductUserName(String ProductUserName) {
		this.ProductUserName = ProductUserName;
	}

	public String getGoodsID() {
		return GoodsID;
	}

	public void setGoodsID(String GoodsID) {
		this.GoodsID = GoodsID;
	}

	public String getRAddress() {
		return RAddress;
	}

	public void setRAddress(String RAddress) {
		this.RAddress = RAddress;
	}

	public Date getSpDate() {
		return SpDate;
	}

	public void setSpDate(Date SpDate) {
		this.SpDate = SpDate;
	}

	public Integer getSpCount() {
		return SpCount;
	}

	public void setSpCount(Integer SpCount) {
		this.SpCount = SpCount;
	}

	public Double getGPrice() {
		return GPrice;
	}

	public void setGPrice(Double GPrice) {
		this.GPrice = GPrice;
	}

	public Long getReceiptID() {
		return ReceiptID;
	}

	public void setReceiptID(Long ReceiptID) {
		this.ReceiptID = ReceiptID;
	}

	public Double getSpPrice() {
		return SpPrice;
	}

	public void setSpPrice(Double SpPrice) {
		this.SpPrice = SpPrice;
	}

	public Date getSendDate() {
		return SendDate;
	}

	public void setSendDate(Date SendDate) {
		this.SendDate = SendDate;
	}

	public String getPayment() {
		return Payment;
	}

	public void setPayment(String Payment) {
		this.Payment = Payment;
	}

	public String getSendType() {
		return SendType;
	}

	public void setSendType(String SendType) {
		this.SendType = SendType;
	}

	public Double getSendPrice() {
		return SendPrice;
	}

	public void setSendPrice(Double SendPrice) {
		this.SendPrice = SendPrice;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer State) {
		this.State = State;
	}

	@Override
	protected Serializable pkVal() {
		return this.SubID;
	}

	@Override
	public String toString() {
		return "Bs_orderform{" +
			", SubID=" + SubID +
			", ClientID=" + ClientID +
			", ProductUserName=" + ProductUserName +
			", GoodsID=" + GoodsID +
			", RAddress=" + RAddress +
			", SpDate=" + SpDate +
			", SpCount=" + SpCount +
			", GPrice=" + GPrice +
			", ReceiptID=" + ReceiptID +
			", SpPrice=" + SpPrice +
			", SendDate=" + SendDate +
			", Payment=" + Payment +
			", SendType=" + SendType +
			", SendPrice=" + SendPrice +
			", State=" + State +
			"}";
	}
}
