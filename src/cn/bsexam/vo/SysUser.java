package cn.bsexam.vo;
public class SysUser{
	private String user;
	private String password;
	public void setUser(String user){
		this.user = user.trim();
	}
	public void setPassword(String password){
		this.password = password.trim();
	}
	public String getUser(){
		return this.user;
	}
	public String getPassword(){
		return this.password;
	}
}