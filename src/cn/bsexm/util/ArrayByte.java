package cn.bsexm.util;
import java.io.IOException;
import java.io.InputStream;


public class ArrayByte {
	private final static int crlf[] = {13,10,13,10}; 
	private byte array[];
	private int length=0;
	private int growSpeed = 100;
	public ArrayByte(){
		array = new byte[300];
	}
	private boolean isFull(){
		return array.length-length>1?false:true;
	}
	private void growup(){
		byte old[] = array;
		array = new byte[array.length+growSpeed];
		for(int i = 0;i<old.length;i++){
			array[i] = old[i];
		}
	}
	public void put(byte value){
		if(isFull())
			growup();
		array[length++] = value;
	}
	public byte[] get(){
		byte newByte[] = new byte[length];
		for(int i = 0;i<length;i++){
			newByte[i] = array[i];
		}
		return newByte;
	}
	public static ArrayByte getHeader(InputStream input) throws IOException{
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
