package com.zmj.wkt.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户个人信息表
 * </p>
 *
 * @author zmj
 * @since 2018-05-22
 */
@TableName("bs_person")
public class Bs_person extends Model<Bs_person> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	private String ClientID;
    /**
     * 昵称
     */
	private String NickName;
    /**
     * 账号名
     */
	private String UserName;
    /**
     * 真实姓名
     */
	private String RealName;
    /**
     * 性别（0男 1女）
     */
	private Integer Sex;
	private String PersonPassword;
    /**
     * 邮箱
     */
	private String Email;
    /**
     * 联系电话
     */
	private String Phone;
    /**
     * 身份证号
     */
	private String CertID;
    /**
     * 密码问题
     */
	private String PswQuestion;
    /**
     * 密码答案
     */
	private String PswResult;
    /**
     * 注册时间
     */
	private Date RegTime;
    /**
     * 会员积分
     */
	private Long MemberPoints;
    /**
     * 会员等级
     */
	private Long GradeID;
    /**
     * 地址
     */
	private String Address;
    /**
     * 头像
     */
	private String Photo;
    /**
     * 微信唯一标识
     */
	private String WXOpenID;
    /**
     * 是否是7日试用
     */
	private Integer isTry;
    /**
     * 激活时间
     */
	private Date activationTime;
    /**
     * 淘客宝用PID(默认PID)
     */
	private String PID;
    /**
     * 邀请码
     */
	private String Invitation_code;
    /**
     * 邀请者ID
     */
	private String InviterID;


	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String ClientID) {
		this.ClientID = ClientID;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String NickName) {
		this.NickName = NickName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public String getRealName() {
		return RealName;
	}

	public void setRealName(String RealName) {
		this.RealName = RealName;
	}

	public Integer getSex() {
		return Sex;
	}

	public void setSex(Integer Sex) {
		this.Sex = Sex;
	}

	public String getPersonPassword() {
		return PersonPassword;
	}

	public void setPersonPassword(String PersonPassword) {
		this.PersonPassword = PersonPassword;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String Phone) {
		this.Phone = Phone;
	}

	public String getCertID() {
		return CertID;
	}

	public void setCertID(String CertID) {
		this.CertID = CertID;
	}

	public String getPswQuestion() {
		return PswQuestion;
	}

	public void setPswQuestion(String PswQuestion) {
		this.PswQuestion = PswQuestion;
	}

	public String getPswResult() {
		return PswResult;
	}

	public void setPswResult(String PswResult) {
		this.PswResult = PswResult;
	}

	public Date getRegTime() {
		return RegTime;
	}

	public void setRegTime(Date RegTime) {
		this.RegTime = RegTime;
	}

	public Long getMemberPoints() {
		return MemberPoints;
	}

	public void setMemberPoints(Long MemberPoints) {
		this.MemberPoints = MemberPoints;
	}

	public Long getGradeID() {
		return GradeID;
	}

	public void setGradeID(Long GradeID) {
		this.GradeID = GradeID;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String Photo) {
		this.Photo = Photo;
	}

	public String getWXOpenID() {
		return WXOpenID;
	}

	public void setWXOpenID(String WXOpenID) {
		this.WXOpenID = WXOpenID;
	}

	public Integer getIsTry() {
		return isTry;
	}

	public void setIsTry(Integer isTry) {
		this.isTry = isTry;
	}

	public Date getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String PID) {
		this.PID = PID;
	}

	public String getInvitation_code() {
		return Invitation_code;
	}

	public void setInvitation_code(String Invitation_code) {
		this.Invitation_code = Invitation_code;
	}

	public String getInviterID() {
		return InviterID;
	}

	public void setInviterID(String InviterID) {
		this.InviterID = InviterID;
	}

	@Override
	protected Serializable pkVal() {
		return this.ClientID;
	}

	@Override
	public String toString() {
		return "Bs_person{" +
			", ClientID=" + ClientID +
			", NickName=" + NickName +
			", UserName=" + UserName +
			", RealName=" + RealName +
			", Sex=" + Sex +
			", PersonPassword=" + PersonPassword +
			", Email=" + Email +
			", Phone=" + Phone +
			", CertID=" + CertID +
			", PswQuestion=" + PswQuestion +
			", PswResult=" + PswResult +
			", RegTime=" + RegTime +
			", MemberPoints=" + MemberPoints +
			", GradeID=" + GradeID +
			", Address=" + Address +
			", Photo=" + Photo +
			", WXOpenID=" + WXOpenID +
			", isTry=" + isTry +
			", activationTime=" + activationTime +
			", PID=" + PID +
			", Invitation_code=" + Invitation_code +
			", InviterID=" + InviterID +
			"}";
	}
}
