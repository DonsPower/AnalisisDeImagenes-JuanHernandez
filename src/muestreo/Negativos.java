/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Negativos {
      public static Image generarImagenEnNegativo(Image original){
        BufferedImage bi = ImageManager.toBufferedImage(original);
     // recorrido por pixel para hacer el tono gris
     for(int x=0; x<bi.getWidth();x++){
         for(int y=0;y<bi.getHeight();y++){
         //extrer el tono del pixel en RGB
         Color color = new Color(bi.getRGB(x, y));
         // calculamos la reducción por promedio
         int negativoR = (255-color.getRed());
         int negativoB= (255-color.getBlue());
         int negativoG =(255-color.getGreen());
         // generamos el color con el nuevo tono promedio(gris)
         color = new Color(negativoR,negativoG,negativoB);
         // se modifica el RGB del pixel en base al nuevo tono
         bi.setRGB(x, y, color.getRGB());
         }
     }
     return ImageManager.toImage(bi);
    }
     
}
