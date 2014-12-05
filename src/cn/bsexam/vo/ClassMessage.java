package cn.bsexam.vo;
public class ClassMessage{
	
	private String cname;
	private int syear;
	private int clength;
	private int dno;
	private String cdepat;
	private String cspecial;
	//setXxx()
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname.trim();
	}
	public int getSyear() {
		return syear;
	}
	public void setSyear(int syear) {
		this.syear = syear;
	}
	public int getClength() {
		return clength;
	}
	public void setClength(int clength) {
		this.clength = clength;
	}
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public String getCdepat() {
		return cdepat;
	}
	public void setCdepat(String cdepat) {
		this.cdepat = cdepat.trim();
	}
	public String getCspecial() {
		return cspecial;
	}
	public void setCspecial(String cspecial) {
		this.cspecial = cspecial.trim();
	}
}