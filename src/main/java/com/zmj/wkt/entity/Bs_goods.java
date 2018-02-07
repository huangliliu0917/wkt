package com.zmj.wkt.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 微信群（商品）表
 * </p>
 *
 * @author zmj
 * @since 2018-02-02
 */
@TableName("bs_goods")
public class Bs_goods extends Model<Bs_goods> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
	private String GoodsID;
    /**
     * 提供者ID
     */
	private String GClientID;
    /**
     * 群人数
     */
	private Integer GWXPersonNumber;
    /**
     * 群主微信号
     */
	private String GWXUserNumber;
    /**
     * 提供者账号
     */
	private String GUserName;
    /**
     * 群名称
     */
	private String GName;
    /**
     * 群单价
     */
	private Double GPrice;
    /**
     * 群活动价格
     */
	private Double GActivePrice;
    /**
     * 产品简介
     */
	private String GInfo;
    /**
     * 商品类别
     */
	private Long GTypeID1;
    /**
     * 商品类别
     */
	private Long GTypeID2;
    /**
     * 商品类别
     */
	private Long GTypeID3;
    /**
     * 邮箱
     */
	private String GIntro;
    /**
     * 二维码图片路径
     */
	private String GImage;
    /**
     * 计数
     */
	private Long GCount;
    /**
     * 商品销量
     */
	private Long GSail;
    /**
     * 商品重量
     */
	private Long GWeight;
    /**
     * 上架日期
     */
	private Date GDateTime;
    /**
     * 商品规格
     */
	private String GSize;
    /**
     * 位置
     */
	private String GAddress;
    /**
     * 状态(1有效，0无效)
     */
	private Integer State;
    /**
     * 是否通过审核（0通过，1未通过，2待审核）
     */
	private Integer IsAble;

	/**
	 * 是否显示（0显示，1不显示）
	 */
	private Integer IsShow;

	public String getGoodsID() {
		return GoodsID;
	}

	public void setGoodsID(String GoodsID) {
		this.GoodsID = GoodsID;
	}

	public String getGClientID() {
		return GClientID;
	}

	public void setGClientID(String GClientID) {
		this.GClientID = GClientID;
	}

	public Integer getGWXPersonNumber() {
		return GWXPersonNumber;
	}

	public void setGWXPersonNumber(Integer GWXPersonNumber) {
		this.GWXPersonNumber = GWXPersonNumber;
	}

	public String getGWXUserNumber() {
		return GWXUserNumber;
	}

	public void setGWXUserNumber(String GWXUserNumber) {
		this.GWXUserNumber = GWXUserNumber;
	}

	public String getGUserName() {
		return GUserName;
	}

	public void setGUserName(String GUserName) {
		this.GUserName = GUserName;
	}

	public String getGName() {
		return GName;
	}

	public void setGName(String GName) {
		this.GName = GName;
	}

	public Double getGPrice() {
		return GPrice;
	}

	public void setGPrice(Double GPrice) {
		this.GPrice = GPrice;
	}

	public Double getGActivePrice() {
		return GActivePrice;
	}

	public void setGActivePrice(Double GActivePrice) {
		this.GActivePrice = GActivePrice;
	}

	public String getGInfo() {
		return GInfo;
	}

	public void setGInfo(String GInfo) {
		this.GInfo = GInfo;
	}

	public Long getGTypeID1() {
		return GTypeID1;
	}

	public void setGTypeID1(Long GTypeID1) {
		this.GTypeID1 = GTypeID1;
	}

	public Long getGTypeID2() {
		return GTypeID2;
	}

	public void setGTypeID2(Long GTypeID2) {
		this.GTypeID2 = GTypeID2;
	}

	public Long getGTypeID3() {
		return GTypeID3;
	}

	public void setGTypeID3(Long GTypeID3) {
		this.GTypeID3 = GTypeID3;
	}

	public String getGIntro() {
		return GIntro;
	}

	public void setGIntro(String GIntro) {
		this.GIntro = GIntro;
	}

	public String getGImage() {
		return GImage;
	}

	public void setGImage(String GImage) {
		this.GImage = GImage;
	}

	public Long getGCount() {
		return GCount;
	}

	public void setGCount(Long GCount) {
		this.GCount = GCount;
	}

	public Long getGSail() {
		return GSail;
	}

	public void setGSail(Long GSail) {
		this.GSail = GSail;
	}

	public Long getGWeight() {
		return GWeight;
	}

	public void setGWeight(Long GWeight) {
		this.GWeight = GWeight;
	}

	public Date getGDateTime() {
		return GDateTime;
	}

	public void setGDateTime(Date GDateTime) {
		this.GDateTime = GDateTime;
	}

	public String getGSize() {
		return GSize;
	}

	public void setGSize(String GSize) {
		this.GSize = GSize;
	}

	public String getGAddress() {
		return GAddress;
	}

	public void setGAddress(String GAddress) {
		this.GAddress = GAddress;
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

	public Integer getIsShow() {
		return IsShow;
	}

	public void setIsShow(Integer isShow) {
		IsShow = isShow;
	}

	@Override
	protected Serializable pkVal() {
		return this.GoodsID;
	}

	@Override
	public String toString() {
		return "Bs_goods{" +
			", GoodsID=" + GoodsID +
			", GClientID=" + GClientID +
			", GWXPersonNumber=" + GWXPersonNumber +
			", GWXUserNumber=" + GWXUserNumber +
			", GUserName=" + GUserName +
			", GName=" + GName +
			", GPrice=" + GPrice +
			", GActivePrice=" + GActivePrice +
			", GInfo=" + GInfo +
			", GTypeID1=" + GTypeID1 +
			", GTypeID2=" + GTypeID2 +
			", GTypeID3=" + GTypeID3 +
			", GIntro=" + GIntro +
			", GImage=" + GImage +
			", GCount=" + GCount +
			", GSail=" + GSail +
			", GWeight=" + GWeight +
			", GDateTime=" + GDateTime +
			", GSize=" + GSize +
			", GAddress=" + GAddress +
			", State=" + State +
			", IsAble=" + IsAble +
			", IsShow=" + IsShow +
			"}";
	}
}
