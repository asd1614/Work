package cn.bsexam.dbc;
import java.sql.DriverManager;
import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class DatabaseConnection implements DBC{
	private static final String	DSNAME="java:comp/env/jdbc/bsexam";
	private Connection conn=null;
	public DatabaseConnection()throws Exception{
		try{
			Context ctx = new InitialContext();
			DataSource ds=(DataSource)ctx.lookup(DSNAME);
			this.conn=ds.getConnection();
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