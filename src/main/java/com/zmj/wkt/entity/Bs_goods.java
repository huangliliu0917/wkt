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
 * @since 2018-01-25
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
     * 商品名称
     */
	private String GName;
    /**
     * 商品价格
     */
	private Double GPrice;
    /**
     * 商品活动价格
     */
	private Double GActivePrice;
    /**
     * 产品简介
     */
	private String GInfo;
    /**
     * 商品类别
     */
	private Long GTypeID;
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

	public Long getGTypeID() {
		return GTypeID;
	}

	public void setGTypeID(Long GTypeID) {
		this.GTypeID = GTypeID;
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

	@Override
	protected Serializable pkVal() {
		return this.GoodsID;
	}

	@Override
	public String toString() {
		return "Bs_goods{" +
			", GoodsID=" + GoodsID +
			", GClientID=" + GClientID +
			", GName=" + GName +
			", GPrice=" + GPrice +
			", GActivePrice=" + GActivePrice +
			", GInfo=" + GInfo +
			", GTypeID=" + GTypeID +
			", GIntro=" + GIntro +
			", GImage=" + GImage +
			", GCount=" + GCount +
			", GSail=" + GSail +
			", GWeight=" + GWeight +
			", GDateTime=" + GDateTime +
			", GSize=" + GSize +
			", GAddress=" + GAddress +
			"}";
	}
}
