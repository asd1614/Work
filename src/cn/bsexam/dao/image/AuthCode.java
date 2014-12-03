package cn.bsexam.dao.image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class AuthCode {
	public static final int AUTHCODE_LENGTH = 4;		//��֤�볤��
	public static final int SINGLECODE_WIDTH = 15;	//������֤����
	public static final int SINGLECODE_HEIGHT = 35;	//������֤��߶�
	public static final int SINGLECODE_GAP = 5;		//������֤��֮����
	public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP)+5;
	public static final int IMG_HEIGHT = SINGLECODE_HEIGHT+5;
	
	public static String getAuthCode() {
		String authCode = "";
		for(int i = 0; i < AUTHCODE_LENGTH; i++) {
			authCode += (new Random()).nextInt(10);
		}
		return authCode;
	}
	
	public static BufferedImage getAuthImg(String authCode) {
		//����ͼƬ�ĸߡ�������
		//RGB���룺red��green��blue
		BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
		//�õ�ͼƬ�ϵ�һ������
		Graphics g = img.getGraphics();
		//���û��ʵ���ɫ������������ɫ
		g.setColor(Color.WHITE);
		//�û��������һ�����Σ����ε����Ͻ����꣬����
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
		//��������ɫ����Ϊ��ɫ������д��
		g.setColor(Color.BLACK);
		//�������壺���塢������ʽ�ġ��ֺ�
		g.setFont(new Font("����", Font.PLAIN, SINGLECODE_HEIGHT));
		
		//�������
		char c;
		for(int i = 0; i < authCode.toCharArray().length; i++) {
			//ȡ����Ӧλ�õ��ַ�
			c = authCode.charAt(i);
			//����һ���ַ�����Ҫ�������ݣ���ʼ��λ�ã��߶�
			g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP)+ SINGLECODE_GAP / 2, IMG_HEIGHT-5);
		}
		Random random = new Random();
		//������
		for(int i = 0; i < 17; i++) {
			int x = random.nextInt(IMG_WIDTH);
			int y = random.nextInt(IMG_HEIGHT);
			int x2 = random.nextInt(IMG_WIDTH);
			int y2 = random.nextInt(IMG_HEIGHT);
			g.drawLine(x, y, x + x2, y + y2);
		}
		return img;
	}
	
	public void getImg() {
		String code = "";
		int intCode = (new Random()).nextInt(9999);
		if(intCode < 1000) {
			intCode +=1000;
		}
		code += intCode;
		
		//����ͼƬ�ĸߡ�������
		//RGB���룺red��green��blue
		BufferedImage image = new BufferedImage(35, 14, BufferedImage.TYPE_INT_BGR);
		//�õ�ͼƬ�ϵ�һ������
		Graphics g = image.getGraphics();
		//���û��ʵ���ɫ������������ɫ
		g.setColor(Color.YELLOW);
		//�û��������һ�����Σ����ε����Ͻ�����Ϊ��1,1������Ϊ33����Ϊ12
		g.fillRect(1, 1, 33, 12);
		//��������ɫ����Ϊ��ɫ������д��
		g.setColor(Color.BLACK);
		//�������壺���塢������ʽ�ġ��ֺ�Ϊ12
		g.setFont(new Font("����", Font.PLAIN, 12));
		
		//�������
		char c;
		for(int i = 0; i < code.toCharArray().length; i++) {
			//ȡ����Ӧλ�õ��ַ�
			c = code.charAt(i);
			//����һ���ַ�����Ҫ�������ݣ���ʼ��λ�ã��߶�
			g.drawString(c + "", i * 7 + 4, 11);	//7Ϊÿ���ֵĿ�ȣ�4Ϊ���
		}
		
		//��ʾ���߱���
		//����������ļ������ļ���Ϊ��c:\\123.jpg��
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File("c:\\" + code + ".jpg"));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//JPG������
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		try {
			//��������
			encoder.encode(image);
		}catch(ImageFormatException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
