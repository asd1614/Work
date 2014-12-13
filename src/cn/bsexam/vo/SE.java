package cn.bsexam.vo;

import java.sql.Timestamp;

public class SE {
	private String sno;
	private String edate;
	private String eno;
	private double egrade;
	private Timestamp time;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno==null?"":sno.trim();
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate==null?"":edate.trim();
	}
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public double getEgrade() {
		return egrade;
	}
	public void setEgrade(double egrade) {
		this.egrade = egrade;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
