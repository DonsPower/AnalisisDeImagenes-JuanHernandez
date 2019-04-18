/*
 me gusta que me digan programador jeje XD

 */
package muestreo;

import io.ImageManager;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Dons
 */
public class centroDeMasa {
    
     public static Image umbralizacionMasa(int u, Image imagenOriginal){
        BufferedImage bi = ImageManager.toBufferedImage(imagenOriginal);
        // recorremos el buffer
        for(int x=0; x<bi.getWidth();x++){
            for(int y=0;y<bi.getHeight();y++){
                Color color = new Color(bi.getRGB(x, y));
                int prom = (color.getRed()+color.getGreen()+color.getBlue())/3;
                if(prom<u){color = new Color(0, 0, 0);}
                else{
                color = new Color(255, 255, 255);
                }
                bi.setRGB(x, y,color.getRGB());
                
                
            
            }
        }
         int contador=1;
         int conx=0;
         int cony=0;
        // recorremos el buffer
        Color verde = new Color(255, 0, 0);
        for(int x=0; x<bi.getWidth();x++){
            for(int y=0;y<bi.getHeight();y++){
                Color color = new Color(bi.getRGB(x, y));
                if(color.getRed()==0 && color.getBlue()==0 && color.getGreen()==0){
                    contador++;
                    conx+=x;
                    cony+=y;
                }
  
            }
        }
        int resux=conx/contador;
        int resuy=cony/contador;
        for(int ii=resux-3;ii<resux+4;ii++){
            for(int jj=resuy-3;jj<resuy+4;jj++){
                bi.setRGB(ii, jj,verde.getRGB());
            }
        }
       return ImageManager.toImage(bi);
    }
}
