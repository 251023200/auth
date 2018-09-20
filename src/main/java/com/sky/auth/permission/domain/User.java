package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 用户
 * @author yangfan
 *
 */
public class User extends BaseEntity{
   
	private static final long serialVersionUID = -9128057248489134443L;
    
    private String userNo;		//用户编号

    private String userName;	//用户名	
    
    private String password;	//密码

    private String name;		//用户姓名
    
    private String salt;		//盐

    private String email="";		//电话
    
    private String telephone="";	//固定电话
    
    private String mobileTelephone="";	//移动电话
    
    private String companyId="";	//公司id
    
    private String deptId="";	//部门id
    
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword(){
		return password;
	}
    
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getSalt(){
		return salt;
	}
	
	public void setSalt(String salt){
		this.salt = salt;
	}
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobileTelephone() {
		return mobileTelephone;
	}

	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String toString(){
		String str = "{";
		str += "userNo:" + userNo + ",";// 编号
		str += "userName:" + userName + ",";// 登录名
		str += "password:" + password + "," ;// 登录密码
		str += "name:" + name + ","; // 姓名
		str += "salt:" + salt + "}";// 盐
		return str;
	}
}