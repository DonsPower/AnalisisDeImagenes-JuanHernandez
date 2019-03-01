/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis.de.imagenes;

import static Expansion.expansionImage.expansionExponencial;
import GUI.JFrameImagen;
import io.ImageManager;
import java.awt.Image;
import muestreo.EscalaGrises;
import muestreo.HistogramaFrecuencias;
import static muestreo.Negativos.generarImagenEnNegativo;
import muestreo.Umbralizacion;

/**
 *
 * @author Roberto Cruz Leija
 */
public class AnalisisDeImagenes {

    
    public static void main(String[] args) {
    //Histograma e imagnees con expancion se ven mas brillantes -----------------
         Image imagen = ImageManager.openImage();
        JFrameImagen frame1 = new JFrameImagen(imagen);
        Image grises = EscalaGrises.generarImagenEnGrises(imagen);
        JFrameImagen frame2 = new JFrameImagen(grises);
        HistogramaFrecuencias histo = new HistogramaFrecuencias(grises);
        histo.graficarHistogramasRGB();
       
        Image contraste = expansionExponencial(0.01,grises);
        JFrameImagen frame3 = new JFrameImagen(contraste);
        HistogramaFrecuencias histo2 = new HistogramaFrecuencias(contraste);
        histo2.graficarHistogramasRGB();
        
        System.out.println();
        
       // ---------Imagen a negativo--------------
//        Image imagen = ImageManager.openImage();p
//        JFrameImagen frame1 = new JFrameImagen(imagen);
//        Image negativo = generarImagenEnNegativo(imagen);
//        JFrameImagen frame3 = new JFrameImagen(negativo);
//        Image positivo = generarImagenEnNegativo(negativo);
//        JFrameImagen frame4 = new JFrameImagen(positivo);
//        
        
        
//        //------------------Imagen Binarizada----------------
//               Image imagen = ImageManager.openImage();
//        JFrameImagen frame1 = new JFrameImagen(imagen);
//        Image grises = EscalaGrises.generarImagenEnGrises(imagen);
//        JFrameImagen frame2 = new JFrameImagen(grises);
////        HistogramaFrecuencias histo = new HistogramaFrecuencias(grises);
////        histo.graficarHistogramasRGB();
//        Image binaria = Umbralizacion.umbralizacionSimple(85,grises);
//        JFrameImagen frame3 = new JFrameImagen(binaria);
//        
//        
//System.out.println();

//---------------Imagen compuesta solo por un color---------------
//        Image imagen = ImageManager.openImage();
//        JFrameImagen frame1 = new JFrameImagen(imagen);
//        BufferedImage bf = ImageManager.toBufferedImage(imagen);
//        Color verde = new Color(255, 255, 0);
//        int h=bf.getHeight();
//        int w=bf.getWidth();
//        for(int i=0;i<w;i++){
//            for(int j=0;j<h;j++){
//                Color prueba=new Color(bf.getRGB(i, j)); 
//                for(int a=0;a<5;a++){
//                    int resu=0;
//                     resu+=a;
//                    if(prueba.getBlue()== 12+resu  && prueba.getGreen()==25+resu && prueba.getRed()==155+resu){
//                        bf.setRGB(i, j, verde.getRGB());
//                    }
//                }
//            }
//        }
//        
//        Image nueva = ImageManager.toImage(bf);
//        JFrameImagen frame2 = new JFrameImagen(nueva);
    }
    
}