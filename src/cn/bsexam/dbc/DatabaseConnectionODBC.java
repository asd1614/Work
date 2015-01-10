package cn.bsexam.dbc;

import java.sql.Connection;

import cn.bsexam.dbc.*;
public class DatabaseConnectionODBC implements DBC {
	private DBC dbc;
	private Connection conn;
	public DatabaseConnectionODBC(){
		try{
			this.initMySQLSourse();
		}catch(Exception e){
			try{
				this.initMySQL();
			}catch(Exception e1){
				try {
					this.initMS();
				} catch (Exception e2) {
					System.out.println("Database Connection can not connect");
				}
			}
		}
	}
	private void initMySQLSourse() throws Exception{
		this.dbc = new DatabaseConnection();
		this.conn = this.dbc.getConnection();
	}
	private void initMySQL() throws Exception{
		this.dbc = new DatabaseConnectionNo();
		this.conn = this.dbc.getConnection();
	}
	private void initMS() throws Exception{
		this.dbc = new DatabaseConnectionMS();
		this.conn = this.dbc.getConnection();
	}
	public void close() throws Exception {
		if(this.conn!=null){
			try{
				this.conn.close();
			}catch(Exception e){
				throw e;
			}
		}
	}
	public Connection getConnection() {
		return this.conn;
	}

}
