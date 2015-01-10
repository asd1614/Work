package cn.bsexam.dbc;
import java.sql.DriverManager;

import cn.bsexam.dbc.DBC;

import java.sql.Connection;
import java.sql.SQLException;
public class DatabaseConnectionMS implements DBC{
	private String URL = "jdbc:odbc:bsexam" ;
	private Connection conn = null;
	public DatabaseConnectionMS() throws Exception{		
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection(URL);
		}catch(Exception e){
			throw e;
		}
	}
	public Connection getConnection(){
		return this.conn;
	}
	public void close() throws Exception{
		if(this.conn!=null){
			try{
				this.conn.close();
			}catch(Exception e){
				throw e;
			}
		}
	}
}
