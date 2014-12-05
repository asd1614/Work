package cn.bsexam.vo;
public class SUser{
	private String sno;
	private String password;
	//setXxx()
	public void setSno(String sno){
		this.sno = sno.trim();
	}
	public void setPassword(String password){
		this.password = password.trim();
	}
	//getXxx()
	public String getSno(){
		return this.sno;
	}
	public String getPassword(){
		return this.password;
	}
}