package cn.bsexam.vo;
public class Student{
	private String sno;
	private String sname;
	private String ssex;
	private String sid;
	private String IDNO;
	private String cname;
	private boolean image_f;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno==null?"":sno.trim();
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname==null?"":sname.trim();
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex==null?"":ssex.trim();
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid==null?"":sid.trim();
	}
	public String getIDNO() {
		return IDNO;
	}
	public void setIDNO(String iDNO) {
		IDNO = iDNO;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname==null?"":cname;
	}
	public boolean getImage_f() {
		return image_f;
	}
	public void setImage_f(boolean image_f) {
		this.image_f = image_f;
	}
	
}