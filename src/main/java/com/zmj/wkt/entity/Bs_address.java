package com.zmj.wkt.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 地址表
 * </p>
 *
 * @author zmj
 * @since 2018-01-25
 */
@TableName("bs_address")
public class Bs_address extends Model<Bs_address> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户ID
     */
	private String ClientID;
    /**
     * 省
     */
	private String Province;
    /**
     * 市
     */
	private String City;
    /**
     * 区县
     */
	private String Area;
    /**
     * 街道
     */
	private String Street;
    /**
     * 详细地址
     */
	private String Address;
    /**
     * 状态
     */
	private Integer State;
    /**
     * 是否默认
     */
	private Integer IsDefault;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String ClientID) {
		this.ClientID = ClientID;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String Province) {
		this.Province = Province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String City) {
		this.City = City;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String Area) {
		this.Area = Area;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String Street) {
		this.Street = Street;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer State) {
		this.State = State;
	}

	public Integer getIsDefault() {
		return IsDefault;
	}

	public void setIsDefault(Integer IsDefault) {
		this.IsDefault = IsDefault;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Bs_address{" +
			", id=" + id +
			", ClientID=" + ClientID +
			", Province=" + Province +
			", City=" + City +
			", Area=" + Area +
			", Street=" + Street +
			", Address=" + Address +
			", State=" + State +
			", IsDefault=" + IsDefault +
			"}";
	}
}
