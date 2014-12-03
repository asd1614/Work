package cn.bsexam.check;
import java.awt.image.*;
import java.awt.Color;
public class BlueCheck {
	/*±³¾°É«Ô¤ÉèRGBÖµ*/
	private static double MIN_H = 0.53;
	private static double MAX_H = 0.72;
	private static double MIN_S = 0.6;
	private static double MAX_S = 1;
	private static double MIN_B = 0.7;
	private static double MAX_B = 1;
	public static boolean colorCheck(BufferedImage image){
		boolean flag = true;
		int width = image.getWidth();
		int heright = image.getHeight();
		double [][] HSBData= new double[300][3]; 
		for(int i = 0;i<10;i++){
			for(int j = 0;j<10;j++){
				//HSBData
			}
		}
		return flag;
	}
}
