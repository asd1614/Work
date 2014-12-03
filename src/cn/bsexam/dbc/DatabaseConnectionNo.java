package cn.bsexam.dbc;
import java.sql.DriverManager;
import java.sql.Connection;
import javax.sql.DataSource;
public class DatabaseConnectionNo implements DBC{
	private static final String	DBURL="jdbc:mysql://localhost:3306/bsexam";
	private static final String DBUSER="root";
	private static final String DBPASSWORD="2366153";
	private static final String DBDRIVER="org.gjt.mm.mysql.Driver";
	private Connection conn=null;
	public DatabaseConnectionNo()throws Exception{
		try{
			Class.forName(DBDRIVER);
			this.conn=DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
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