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
 * @since 2018-05-19
 */
@TableName("region")
public class Region extends Model<Region> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer parent_id;
	private String region_name;
	private Integer region_type;
	private Integer agency_id;
	private String areaid;
	private String zip;
	private String code;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public Integer getRegion_type() {
		return region_type;
	}

	public void setRegion_type(Integer region_type) {
		this.region_type = region_type;
	}

	public Integer getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(Integer agency_id) {
		this.agency_id = agency_id;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Region{" +
			", id=" + id +
			", parent_id=" + parent_id +
			", region_name=" + region_name +
			", region_type=" + region_type +
			", agency_id=" + agency_id +
			", areaid=" + areaid +
			", zip=" + zip +
			", code=" + code +
			"}";
	}
}
