package cn.pb.smbms.pojo;

import java.util.Date;

public class User {

	private Integer id;// 主键ID
	private String userCode;// 用户编码
	private String userName;// 用户名称
	private String userPassword;// 用户密码
	private int gender;// 性别(1 男 2 女)
	private Date birthday;// 出生日期
	private String phone;// 手机
	private String address;// 地址
	private Integer userType;// 用户类型(1 系统管理员 2 经理 3 员工)
	private Integer createdBy;// 创建者（userID）
	private Date createDate;// 创建时间
	private Integer modifyBy;// 更新者(userID)
	private Date modifyDate;// 更新时间

	private Integer age;// 年龄

	public Integer getAge() {
		Date date = new Date();
		// 获取年龄的值
		Integer age = date.getYear() - birthday.getYear();
		return age;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String userCode, String userName,
			String userPassword, int gender, Date birthday, String phone,
			String address, Integer userType, Integer createdBy,
			Date createDate, Integer modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.userPassword = userPassword;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.userType = userType;
		this.createdBy = createdBy;
		this.createDate = createDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userCode=" + userCode + ", userName="
				+ userName + ", userPassword=" + userPassword + ", gender="
				+ gender + ", birthday=" + birthday + ", phone=" + phone
				+ ", address=" + address + ", userType=" + userType
				+ ", createdBy=" + createdBy + ", createDate=" + createDate
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + "]";
	}

}
