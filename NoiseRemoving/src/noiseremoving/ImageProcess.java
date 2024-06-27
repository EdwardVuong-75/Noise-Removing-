package noiseremoving;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author vuong
 */
public class ImageProcess {
    
    BufferedImage buffered_image = null;
    
    
    public ImageProcess(String image)
    {
        try {
            buffered_image = ImageIO.read(new File(image));
        } catch (IOException ex) {
            Logger.getLogger(ImageProcess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    public void reverseColour()
    {
        int width = buffered_image.getWidth();
        int hight = buffered_image.getHeight();
        WritableRaster writeable_raster = buffered_image.getRaster();
        for(int i = 0; i < hight; i++)
        {
            for(int j = 0; j < width; j++)
            {
                int colour = buffered_image.getRGB(j, i);
                
                int[] pixel = new int[3];
                pixel[2] = colour & 0xff;               //blue
                pixel[1] = (colour & 0xff00) >> 8;      //green
                pixel[0] = (colour & 0xff0000) >> 16;   //red
                
                pixel[0] = 255 - pixel[0];
                pixel[1] = 255 - pixel[1];
                pixel[2] = 255 - pixel[2];
                
                writeable_raster.setPixel(j, i, pixel);
            }
        }
                
    }
    
    public void cubicFunction()
    {
        int width = buffered_image.getWidth();
        int hight = buffered_image.getHeight();
        WritableRaster writeable_raster = buffered_image.getRaster();
        for(int i = 0; i < hight; i++)
        {
            for(int j = 0; j < width; j++)
            {
                int colour = buffered_image.getRGB(j, i);
                
                int[] pixel = new int[3];
                pixel[2] = colour & 0xff;               //blue
                pixel[1] = (colour & 0xff00) >> 8;      //green
                pixel[0] = (colour & 0xff0000) >> 16;   //red
                
                int intensity = (pixel[0]+pixel[1]+pixel[2])/3;
                
                double value = intensity/255.0;
                double rate = (Math.pow(2*value-1, 3.0)+1)/2;
                
                pixel[0] = (int)(pixel[0]*rate/value);
                pixel[1] = (int)(pixel[1]*rate/value);
                pixel[2] = (int)(pixel[2]*rate/value);
                
                writeable_raster.setPixel(j, i, pixel);
            }
        }
        
    }
    
    public void cosFunction()
    {
        int width = buffered_image.getWidth();
        int hight = buffered_image.getHeight();
        WritableRaster writeable_raster = buffered_image.getRaster();
        for(int i = 0; i < hight; i++)
        {
            for(int j = 0; j < width; j++)
            {
                int colour = buffered_image.getRGB(j, i);
                
                int[] pixel = new int[3];
                pixel[2] = colour & 0xff;               //blue
                pixel[1] = (colour & 0xff00) >> 8;      //green
                pixel[0] = (colour & 0xff0000) >> 16;   //red
                
                //int intensity = (pixel[0]+pixel[1]+pixel[2])/3;
                
                
                double rate = (1-Math.cos(pixel[0]/255.0*Math.PI))/2;                
                pixel[0] = (int)(255.0*rate);
                
                rate = (1-Math.cos(pixel[1]/255.0*Math.PI))/2;
                pixel[1] = (int)(255.0*rate);
                
                rate = (1-Math.cos(pixel[2]/255.0*Math.PI))/2;
                pixel[2] = (int)(255.0*rate);
//                System.out.println(pixel[2]);
                writeable_raster.setPixel(j, i, pixel);
            }
        }
    }
    
    
    public void save(String imageName)
    {
        int i = imageName.indexOf(".");
        String type = imageName.substring(i+1);
        try {ImageIO.write(buffered_image, type, new File(imageName));}
              catch (IOException e) {System.err.println("image not saved.");}
    }
    
    public void addNoise(float density)
    {
        int width = buffered_image.getWidth();
        int hight = buffered_image.getHeight();
        WritableRaster writeable_raster = buffered_image.getRaster();
        for(int i = 0; i < hight; i++)
        {
            for(int j = 0; j < width; j++)
            {
                int colour = buffered_image.getRGB(j, i);
                
                int[] pixel = new int[3];
                pixel[2] = colour & 0xff;               //blue
                pixel[1] = (colour & 0xff00) >> 8;      //green
                pixel[0] = (colour & 0xff0000) >> 16;   //red
                
                if(Math.random()<density)
                {
                    int noise = (int)(255*Math.round(Math.random()));
                    pixel[0] = noise;
                    pixel[1] = noise;
                    pixel[2] = noise;
                }
                
                writeable_raster.setPixel(j, i, pixel);
            }
        }
    }
    
    public void cleanNoise()
    {
        int width = buffered_image.getWidth();
        int hight = buffered_image.getHeight();
        WritableRaster writeable_raster = buffered_image.getRaster();
        for(int i = 1; i < hight-1; i++)
        {
            for(int j = 1; j < width-1; j++)
            {
                Integer[] intensity_r = new Integer[9];
                Integer[] intensity_g = new Integer[9];
                Integer[] intensity_b = new Integer[9];
                int index = 0;
                int[] pixel = new int[3];
                
                for(int k = -1; k < 2; k++)
                    for(int l = -1; l < 2; l++)
                    {
                        int colour = buffered_image.getRGB(j+k, i+l);
                
                        
                        pixel[2] = colour & 0xff;               //blue
                        pixel[1] = (colour & 0xff00) >> 8;      //green
                        pixel[0] = (colour & 0xff0000) >> 16;   //red
                        intensity_b[index] = pixel[2];
                        intensity_g[index] = pixel[1];
                        intensity_r[index] = pixel[0];
                        index ++;
               
                    }
                
                // please add your code here
                // 1. create SortArray object
                // 2. pass intensity_r[] to the SortArray object
                // 3. call quickSort() from SortArray object
                // 4. do steps 2 and 3 for intensity_g and intensity_b
                
                // start of your code (take as many lines as you need)
                //  create SortArray object
                SortArray b = new SortArray(intensity_b);
                SortArray g = new SortArray(intensity_g);
                SortArray r = new SortArray(intensity_r);
                
                // pass intensity_r[] to the SortArray object
                b.quickSort();
                g.quickSort();
                r.quickSort();
                // end of your code

                //index 4 hold median value of R, G and B
                pixel[2] = intensity_b[4];      
                pixel[1] = intensity_g[4];      
                pixel[0] = intensity_r[4];
                
                writeable_raster.setPixel(j, i, pixel);
            }
        }
    }       
}

