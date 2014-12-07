package cn.bsexam.vo;

public class ShowStu {
	private String sno;
	private String sname;
	private String cname;
	private String cdepat;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname==null?"":cname.trim();
	}
	public String getCdepat() {
		return cdepat;
	}
	public void setCdepat(String cdepat) {
		this.cdepat = cdepat==null?"":cdepat.trim();
	}
	
}
