package cn.bsexam.dao.interf;
import java.sql.Connection;

import cn.bsexam.vo.ShowStu;
public interface IShowStu {
	public ShowStu findShowStu(String SNO);
	void setConnection(Connection conn);
}
