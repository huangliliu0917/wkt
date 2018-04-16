package com.zmj.wkt.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zmj
 * @since 2018-04-16
 */
@TableName("Bs_tbkCollections")
public class Bs_tbkCollections extends Model<Bs_tbkCollections> {

    private static final long serialVersionUID = 1L;

    /**
     * 淘宝客ID
     */
	private String tbkID;
	private String item_url;
	private String nick;
	private String num_iid;
	private String pict_url;
	private String provcity;
	private Double reserve_price;
	private String seller_id;
	private String title;
	private String user_type;
	private String volume;
	private Double zk_final_price;
    /**
     * 用户ID
     */
	private String ClientID;
    /**
     * 推广链接
     */
	private String coupon_click_url;


	public String getTbkID() {
		return tbkID;
	}

	public void setTbkID(String tbkID) {
		this.tbkID = tbkID;
	}

	public String getItem_url() {
		return item_url;
	}

	public void setItem_url(String item_url) {
		this.item_url = item_url;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNum_iid() {
		return num_iid;
	}

	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}

	public String getPict_url() {
		return pict_url;
	}

	public void setPict_url(String pict_url) {
		this.pict_url = pict_url;
	}

	public String getProvcity() {
		return provcity;
	}

	public void setProvcity(String provcity) {
		this.provcity = provcity;
	}

	public Double getReserve_price() {
		return reserve_price;
	}

	public void setReserve_price(Double reserve_price) {
		this.reserve_price = reserve_price;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Double getZk_final_price() {
		return zk_final_price;
	}

	public void setZk_final_price(Double zk_final_price) {
		this.zk_final_price = zk_final_price;
	}

	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String ClientID) {
		this.ClientID = ClientID;
	}

	public String getCoupon_click_url() {
		return coupon_click_url;
	}

	public void setCoupon_click_url(String coupon_click_url) {
		this.coupon_click_url = coupon_click_url;
	}

	@Override
	protected Serializable pkVal() {
		return this.tbkID;
	}

	@Override
	public String toString() {
		return "Bs_tbkCollections{" +
			", tbkID=" + tbkID +
			", item_url=" + item_url +
			", nick=" + nick +
			", num_iid=" + num_iid +
			", pict_url=" + pict_url +
			", provcity=" + provcity +
			", reserve_price=" + reserve_price +
			", seller_id=" + seller_id +
			", title=" + title +
			", user_type=" + user_type +
			", volume=" + volume +
			", zk_final_price=" + zk_final_price +
			", ClientID=" + ClientID +
			", coupon_click_url=" + coupon_click_url +
			"}";
	}
}
