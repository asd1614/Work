package cn.bsexam.dao.image;
import com.jspsmart.upload.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
public class ImageCheck {
	private static String fileExt_1 = "jpg";		/*只接收jpg格式的图片*/
	private static String fileExt_2 = "jpeg";		/*只接收jpg格式的图片*/
	private static int fileSize = 40*1024;			/*文件最大不超不过100KB*/
	private static int width = 614;					/*图片宽度不超赤614像素*/
	private static int height = 819;				/*图片高度不超过819像素*/	
	private static float H_MIN = 190F; 				/*图片的背景色HSB的值域*/
	private static float H_MAX = 260F;
	private static float S_MIN = 60F;
	private static float S_MAX = 100F;
	private static float B_MIN = 70F;
	private static float B_MAX = 100F;					/*图片的背景色HSB的值域*/
	
	public static boolean[] imageCheck(SmartFile file,BufferedImage image) {
		boolean[] flag = new boolean[7] ;				/*判断结果记录数组:0-type,1-size,2-width,3-height,4-H,5-S,6-B*/
		for(int i=0;i<7;i++){
			flag[i]	= true;
		}
		try{
			if(file==null||image==null)
				throw new ImageCheckExcetion("file or image is null");
		}catch(ImageCheckExcetion e){
			e.printStackTrace();
		}
		if(!file.getFileExt().equalsIgnoreCase(fileExt_1)){
			if(!file.getFileExt().equalsIgnoreCase(fileExt_2))
				flag[0] = false;
		}
		if(file.getSize()>fileSize){
			flag[1] = false;
		}
		int image_width = image.getWidth();
		if(image_width==0||image_width>width){
			flag[2] = false;
		}
		if(image.getHeight()==0||image.getHeight()>height){
			flag[3] = false;	
		}
		int count = 10*image_width;
		int [] RGB_P = new int[count];					/*存放检测图像部分像素值数组*/
		image.getRGB(0, 0, image_width, 10, RGB_P, 0, image_width);
		float[][] HSB_values = new float[RGB_P.length][3];
		int i = 0;
		while(i<count){
			RGBtoHsb((RGB_P[i]&0x00FF0000)>>16,(RGB_P[i]&0x0000FF00)>>8,RGB_P[i]&0x000000FF, HSB_values[i]);
			i++;
		}
		for(int j=0;j<i;j++){
			if(HSB_values[j][0]<H_MIN||HSB_values[j][0]>H_MAX)
			{
				flag[4] = false;
				break;
			}
			if(HSB_values[j][1]<S_MIN||HSB_values[j][1]>S_MAX)
			{
				flag[5] = false;
				break;
			}
			if(HSB_values[j][2]<B_MIN||HSB_values[j][2]>B_MAX)
			{
				flag[6] = false;
				break;
			}
		}
		return flag;
	}
	private static float[] RGBtoHsb(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax){
                hue = (bluec - greenc)*60f;
                if (hue < 0)
                    hue = hue + 360f;
            }
            else if (g == cmax)
                hue = 120f + (redc - bluec)*60f;
            else
                hue = 240f + (greenc - redc)*60f;
            
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation*100;
        hsbvals[2] = brightness*100;
        return hsbvals;
    }	
}
