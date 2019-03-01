/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis.de.imagenes;

import GUI.JFrameImagen;
import io.ImageManager;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Dons
 */
public class cromaImage {
    public static void main(String args[]){
        Image imagen1 = ImageManager.openImage();
        JFrameImagen frame1 = new JFrameImagen(imagen1);
        BufferedImage bf = ImageManager.toBufferedImage(imagen1);
        
        Image imagen2 = ImageManager.openImage();
        JFrameImagen framel2= new JFrameImagen(imagen2);
        BufferedImage bf2= ImageManager.toBufferedImage(imagen2);
        
        int h=bf.getHeight();
        int w=bf.getWidth();
        Color verde=new Color(0,255,0);
        int e=0;
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
              Color pruebas=new Color(bf.getRGB(i, j)); 
                    if(pruebas.getBlue()< 100 && pruebas.getGreen()>180 && pruebas.getRed()<100){
                           bf.setRGB(i, j,bf2.getRGB(i, j));
                        }
            }
        }
        
        
        Image nueva = ImageManager.toImage(bf);
        JFrameImagen frame2 = new JFrameImagen(nueva);
        
    }
    
}
