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
 * @since 2018-05-19
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
     * 客户用户名
     */
	private String ConsumerUserName;
    /**
     * 提供者账号
     */
	private String ProductUserName;
    /**
     * 微信群ID
     */
	private String GoodsID;
    /**
     * 微信群名称
     */
	private String GName;
    /**
     * 微信群类别
     */
	private String GTypeID;
    /**
     * 地区
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
     * 项目标题
     */
	private String ItemTitle;
    /**
     * 项目原价
     */
	private Double ItemPrice;
    /**
     * 项目活动价
     */
	private Double ItemActivePrice;
    /**
     * 项目描述
     */
	private String ItemDescription;
    /**
     * 项目图片
     */
	private String ItemPic;
    /**
     * 订单状态
(0有效 1无效 2待发送 3发送成功 4发送失败 5确实支付)
     */
	private Integer State;
    /**
     * 审核状态
(0通过 1未通过 2待审核)
     */
	private Integer IsAble;


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

	public String getConsumerUserName() {
		return ConsumerUserName;
	}

	public void setConsumerUserName(String ConsumerUserName) {
		this.ConsumerUserName = ConsumerUserName;
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

	public String getGName() {
		return GName;
	}

	public void setGName(String GName) {
		this.GName = GName;
	}

	public String getGTypeID() {
		return GTypeID;
	}

	public void setGTypeID(String GTypeID) {
		this.GTypeID = GTypeID;
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

	public String getItemTitle() {
		return ItemTitle;
	}

	public void setItemTitle(String ItemTitle) {
		this.ItemTitle = ItemTitle;
	}

	public Double getItemPrice() {
		return ItemPrice;
	}

	public void setItemPrice(Double ItemPrice) {
		this.ItemPrice = ItemPrice;
	}

	public Double getItemActivePrice() {
		return ItemActivePrice;
	}

	public void setItemActivePrice(Double ItemActivePrice) {
		this.ItemActivePrice = ItemActivePrice;
	}

	public String getItemDescription() {
		return ItemDescription;
	}

	public void setItemDescription(String ItemDescription) {
		this.ItemDescription = ItemDescription;
	}

	public String getItemPic() {
		return ItemPic;
	}

	public void setItemPic(String ItemPic) {
		this.ItemPic = ItemPic;
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

	@Override
	protected Serializable pkVal() {
		return this.SubID;
	}

	@Override
	public String toString() {
		return "Bs_orderform{" +
			", SubID=" + SubID +
			", ClientID=" + ClientID +
			", ConsumerUserName=" + ConsumerUserName +
			", ProductUserName=" + ProductUserName +
			", GoodsID=" + GoodsID +
			", GName=" + GName +
			", GTypeID=" + GTypeID +
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
			", ItemTitle=" + ItemTitle +
			", ItemPrice=" + ItemPrice +
			", ItemActivePrice=" + ItemActivePrice +
			", ItemDescription=" + ItemDescription +
			", ItemPic=" + ItemPic +
			", State=" + State +
			", IsAble=" + IsAble +
			"}";
	}
}
