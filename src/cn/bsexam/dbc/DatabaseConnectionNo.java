package cn.bsexam.dbc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.sql.DataSource;
public class DatabaseConnectionNo implements DBC{
	private static String DBURL;
	private static String DBUSER;
	private static String DBPASSWORD;
	private static String DBDRIVER="org.gjt.mm.mysql.Driver";
	private static int byteLength = 200;
	private Connection conn=null;
	public DatabaseConnectionNo()throws Exception{
		init();
		try{
			Class.forName(DBDRIVER);
			this.conn=DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
		}catch(Exception e){
			throw e;
		}
	}
	private void init(){
		String path = System.getProperty("user.dir");
		path = path+File.separator+"WebContent"+File.separator+"META-INF"+File.separator+"MySQLSetting.ini";
		File ini = new File(path);
		if(!ini.exists())
			return;
		if(DBURL!=null)
			return;
		FileInputStream input = null;
		try {
			input = new FileInputStream(ini);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		byte data[] = new byte[byteLength];
		int length = 0;
		try {
			length = input.read(data);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ByteBuffer buffer = ByteBuffer.wrap(data,0,length);
		CharBuffer charbuffer = Charset.forName("ASCII").decode(buffer);
		String str = charbuffer.toString();
		Scanner scan = new Scanner(str);
		Map<String,String> map = new HashMap<String,String>();
		while(scan.hasNextLine()){
			String s = scan.nextLine();
			int index = s.indexOf("=");
			String name = s.substring(0, index);
			String value = s.substring(index+1);
			map.put(name, value);
		}
		setDB(map.get("DBURL"),map.get("DBUSER"),map.get("DBPASSWORD"),map.get("DBDRIVER"));
	}
	private synchronized void setDB(String dburl,String dbuser,String dbpassword,String dbdriver){
		DBURL = dburl;
		DBUSER = dbuser;
		DBPASSWORD = dbpassword;
		DBDRIVER = dbdriver;
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