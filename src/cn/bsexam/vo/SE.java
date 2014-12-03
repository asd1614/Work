package cn.bsexam.vo;

import java.sql.Timestamp;

public class SE {
	private String sno;
	private Timestamp edate;
	private int eno;
	private double egrade;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public Timestamp getEdate() {
		return edate;
	}
	public void setEdate(Timestamp edate) {
		this.edate = edate;
	}
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public double getEgrade() {
		return egrade;
	}
	public void setEgrade(double egrade) {
		this.egrade = egrade;
	}
	
}
