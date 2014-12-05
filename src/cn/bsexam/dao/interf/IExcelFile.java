package cn.bsexam.dao.interf;
import cn.bsexam.vo.ExcelFile;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.*;

public interface IExcelFile {
	List<ExcelFile> exportList(String start,String end);
	void setConnection(Connection conn);
}
