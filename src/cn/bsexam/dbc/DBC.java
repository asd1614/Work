package cn.bsexam.dbc;
import java.sql.Connection;
public interface DBC {
	void close()throws Exception;
	Connection getConnection();
}
