package com.zgw.core.auth.domain;

import java.util.Date;
import java.util.Set;

import com.zgw.orm.BaseEntity;
import com.zgw.web.auth.SessionUser;
import org.springframework.stereotype.Component;

/**
 * 系统用户
 */
@Component
public class AuthUser extends BaseEntity implements SessionUser{

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	*登录用户名
	**/
	private String realname;

	/**
	*真实姓名
	**/
	private String username;

	/**
	*密码
	**/
	private String password;

	/**
	*性别
	**/
	private Integer gender;

	/**
	*头像
	**/
	private String header;

	/**
	*手机号码
	**/
	private String mobile;
	/**
	 * 用户角色
	 */
	private Integer role;

	/**
	*状态：待审核（0），审核通过（1），默认（2），审核未通过（3），禁用（5）
	**/
	private Integer status;

	/**
	*生日
	**/
	private Date birthday;

	/**
	*学历：大专、本科、硕士、博士、博士后
	**/
	private String education;
	
	/**
	 * 大学编号
	 */
	private String collegeCode;
	
	/**
	 * 大学名称
	 */
	private String collegeName;
	
	/**
	*资格证书编号
	**/
	private String certNo;

	/**
	*头衔
	**/
	private String title;

	/**
	*签名
	**/
	private String sign;

	/**
	*微信公众号openid
	**/
	private String openId;

	/**
	*微信号
	**/
	private String wechatId;

	/**
	*qq号
	**/
	private String qq;

	/**
	*最后一次登录时间
	**/
	private Date loginTime;

	/**
	*最后一次登录IP
	**/
	private String ip;

	/**
	*所在省份
	**/
	private String province;

	/**
	*所在城市
	**/
	private String city;

	/**
	*所在地区
	**/
	private String district;
	
	/**
	 * 推荐权重
	 */
	private Integer weight;

	/**
	 * 扩展字段：微信昵称
	 */
	private String nickname;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRealname(){
		return realname;
	}
	public void setRealname(String realname){
		this.realname = realname;
	}

	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public Integer getGender(){
		return gender;
	}
	public void setGender(Integer gender){
		this.gender = gender;
	}

	public String getHeader(){
		return header;
	}
	public void setHeader(String header){
		this.header = header;
	}

	public String getMobile(){
		return mobile;
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}

	public Date getBirthday(){
		return birthday;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public String getEducation(){
		return education;
	}
	public void setEducation(String education){
		this.education = education;
	}
	public String getCollegeCode() {
		return collegeCode;
	}
	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getCertNo(){
		return certNo;
	}
	public void setCertNo(String certNo){
		this.certNo = certNo;
	}

	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}

	public String getSign(){
		return sign;
	}
	public void setSign(String sign){
		this.sign = sign;
	}

	public String getOpenId(){
		return openId;
	}
	public void setOpenId(String openId){
		this.openId = openId;
	}

	public String getWechatId(){
		return wechatId;
	}
	public void setWechatId(String wechatId){
		this.wechatId = wechatId;
	}

	public String getQq(){
		return qq;
	}
	public void setQq(String qq){
		this.qq = qq;
	}

	public Date getLoginTime(){
		return loginTime;
	}
	public void setLoginTime(Date loginTime){
		this.loginTime = loginTime;
	}

	public String getIp(){
		return ip;
	}
	public void setIp(String ip){
		this.ip = ip;
	}

	public String getProvince(){
		return province;
	}
	public void setProvince(String province){
		this.province = province;
	}

	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}

	public String getDistrict(){
		return district;
	}
	public void setDistrict(String district){
		this.district = district;
	}
	
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Long getUserId() {
		return this.getId();
	}

	public Set<String> getPermissions(){
		return null;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "AuthUser{" +
				"realname='" + realname + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", gender=" + gender +
				", header='" + header + '\'' +
				", mobile='" + mobile + '\'' +
				", role=" + role +
				", status=" + status +
				", birthday=" + birthday +
				", education='" + education + '\'' +
				", collegeCode='" + collegeCode + '\'' +
				", collegeName='" + collegeName + '\'' +
				", certNo='" + certNo + '\'' +
				", title='" + title + '\'' +
				", sign='" + sign + '\'' +
				", openId='" + openId + '\'' +
				", wechatId='" + wechatId + '\'' +
				", qq='" + qq + '\'' +
				", loginTime=" + loginTime +
				", ip='" + ip + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", district='" + district + '\'' +
				", weight=" + weight +
				", nickname='" + nickname + '\'' +
				'}';
	}
}

