package cn.bsexam.util;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
public class HttpHeadHelper {
	private final static int crlf[] = {13,10,13,10};
	private  Map<String,String> head ;
	private byte head_data[];
	private HttpHeadHelper(){
		init();
	}
	public  HttpHeadHelper(InputStream input) throws IOException{
//		input = new BufferedInputStream(input);
		input.mark(0);
		head_data = getRe_Header(input).get();
		ByteArrayInputStream byte_input = new ByteArrayInputStream(head_data);
		Scanner scan = new Scanner(byte_input,"ASCII");
		head = new HashMap<String,String>();
		head.put(null, scan.nextLine());
		while(scan.hasNextLine()){
			String s = scan.nextLine();
			if(s.equals(""))break;
			int index = s.indexOf(":");
			String name = s.substring(0,index);
			String value = s.substring(index+1);
			head.put(name, value.trim());
		}
		input.reset();
	}
	private void init(){
		head = new HashMap<String ,String>();		
		head.put("Host", "www.bsuc.cn:8172\r\n");
		head.put("Connection","close\r\n");		
		head.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36\r\n");
		head.put("Referer", "http://www.bsuc.cn:8172/jwweb/_data/index_LOGIN.aspx\r\n");
		head.put("Origin", "http://www.bsuc.cn:8172\r\n");
		head.put("Accept-Encoding","gzip, deflate, sdch\r\n");
		head.put("Accept-language", "zh-CN,zh;q=0.8\r\n");
		
	}
	public void put(String key,String value){
		head.put(key, value);
	}
	public String get(String key){
		return head.get(key);
	}
	public Map<String,String> Map(){
		return head;
	}
	public long dataLength(){
		return head_data.length;
	}
	public static HttpHeadHelper getHeader(){
		HttpHeadHelper helper = new HttpHeadHelper();
		helper.head.put("Accept", "image/webp,*/8;q=0.8\r\n");
		return helper;
	}
	public static HttpHeadHelper postHeader(){
		HttpHeadHelper helper = new HttpHeadHelper();
		helper.head.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n");
		helper.head.put("Content-Type","application/x-www-form-urlencoded\r\n");
		return helper;
	}
	private  ArrayByte getRe_Header(InputStream input) throws IOException{
		//标记crlf数据的配匹长度
		int j = 0;
		//ArrayByte存储读取数组
		ArrayByte array = new ArrayByte();
		while(true){
			int t = input.read();
			if(t==13){
				int temp[] = new int[4];
				temp[j++]=t;
				for(;j<temp.length;j++){
					int k = input.read();
					if(k==crlf[j])
						temp[j]=k;
					else{
						temp[j]=k;
						break;
					}
				}
				if(j==temp.length){
					for(int i=0;i<j;i++){
						array.put((byte)temp[i]);
					}
					break;
				}
				else{
					for(int i=0;i<=j;i++){
						array.put((byte)temp[i]);
					}
					j=0;
				}
			}else{
				array.put((byte)t);
			}
		}
		return array;
	}
	
}
