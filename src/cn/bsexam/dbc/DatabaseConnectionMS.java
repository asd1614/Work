package cn.bsexam.dbc;
import java.sql.DriverManager;
import cn.bsexam.dbc.DBC;
import java.sql.Connection;
import java.sql.SQLException;
public class DatabaseConnectionODBC implements DBC{
	private String URL = "jdbc:odbc:bsexam" ;
	Connection conn = null;
	public DatabaseConnectionODBC(){		
		this.init();
	}
	private void init(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection(URL);
		}catch(SQLException e){
			System.err.print("Error" + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		if(conn==null){
			this.init();
		}
		return conn;
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
