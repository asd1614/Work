package cn.bsexam.vo;

public class Department {
	private String cdepat;
	private String depatname;
	public String getCdepat() {
		return cdepat;
	}
	public void setCdepat(String cdepat) {
		this.cdepat = cdepat.trim();
	}
	public String getDepatname() {
		return depatname;
	}
	public void setDepatname(String depatname) {
		this.depatname = depatname.trim();
	}
}
