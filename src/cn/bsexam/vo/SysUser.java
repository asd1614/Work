package cn.bsexam.vo;
public class SysUser{
	private String suser;
	private String password;
	public void setSuser(String suser){
		this.suser = suser.trim();
	}
	public void setPassword(String password){
		this.password = password.trim();
	}
	public String getSuser(){
		return this.suser;
	}
	public String getPassword(){
		return this.password;
	}
}