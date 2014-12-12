package cn.bsexam.vo;

import java.sql.Timestamp;

public class ExamType {
	private String eno;
	private String ename;
	private Timestamp edate;
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Timestamp getEdate() {
		return edate;
	}
	public void setEdate(Timestamp edate) {
		this.edate = edate;
	}
}
