/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis.de.imagenes;

import static Expansion.expansionImage.expansionExponencial;
import static Expansion.expansionImage.expansionLineal;
import static Expansion.expansionImage.expansionLogaritmica;
import static Expansion.expansionImage.miExpansion;
import GUI.JFrameImagen;
import frecuencias.FFTCalculo;
import frecuencias.FiltroButterworthPasaBajas;
import frecuencias.FiltroExponencialPasaBajas;
import frecuencias.FiltroIdealPasaAltas;
import frecuencias.FiltroIdealPasaBajas;
import frecuencias.Gestor;
import io.ImageManager;
import java.awt.Image;
import java.awt.image.BufferedImage;
import muestreo.Convolucion;
import muestreo.EscalaGrises;
import muestreo.HistogramaFrecuencias;
import static muestreo.Negativos.generarImagenEnNegativo;
import muestreo.Umbralizacion;

/**q 
 *
 * @author Roberto Cruz Leija
 */
public class AnalisisDeImagenes {

    
    public static void main(String[] args) {
//          FILTROS PASABAJAS Y PASAALTAS.

       BufferedImage bi = ImageManager.toBufferedImage(ImageManager.openImage());
        Gestor gestor = new Gestor(bi);
        JFrameImagen frame = new JFrameImagen(ImageManager.toImage(bi));
        JFrameImagen frame1 = new JFrameImagen(ImageManager.toImage(gestor.obtenerImagenFrecuencias(false)));
        FiltroIdealPasaAltas filtro = new FiltroIdealPasaAltas(512, 512, 40);
        //FiltroButterworthPasaBajas filtro= new FiltroButterworthPasaBajas(512,512, 50,3);
          //FiltroExponencialPasaBajas filtro= new FiltroExponencialPasaBajas(512,512,50,3);
          //FiltroIdealPasaBajas filtro = new FiltroIdealPasaBajas(512, 512, 100);
       // FiltroTrapezoidal filtro = new FiltroTrapezoidal(512, 512, 5, 50);
        filtro.generar();
        gestor.aplicarFiltro( filtro.getMatriz());
        JFrameImagen frame2 = new JFrameImagen(filtro.toImage());
        JFrameImagen frame3 = new JFrameImagen(ImageManager.toImage(gestor.obtenerImagenEspacial()));
        
        
        //Agregacion de la convoluciojn
//        Image imagen = ImageManager.openImage();
//        JFrameImagen frame1 = new JFrameImagen(imagen);
//        double kernel [][] = new double[][]{{-2,-1,0},{-1,1,1},{0,1,2}};
//     //    creamos mascara de Laplace
//     double[][] laplace = {{0.0, 1.0, 0.0}, {1.0, -4.0, 1.0}, {0.0, 1.0, 0.0}};
//        double kernel2 [][] = new double[][]{{1,1,1,1,1},{1,4,4,4,1},{1,4,12,4,1},{1,4,4,4,1},{1,1,1,1,1}};
//        Convolucion convo = new Convolucion(imagen);
//        Image nueva = convo.aplicar(laplace, 1);
//        
//        
//        JFrameImagen frame2 = new JFrameImagen(nueva);
         
       
    //System.out.println();
        
        
        
        
//    //Histograma e imagnees con expancion se ven mas brillantes -----------------
//         Image imagen = ImageManager.openImage();
//        JFrameImagen frame1 = new JFrameImagen(imagen);
//        //Image grises = EscalaGrises.generarImagenEnGrises(imagen);
//        //JFrameImagen frame2 = new JFrameImagen(grises);
//       // Image negativo = generarImagenEnNegativo(imagen);
//        //JFrameImagen frame4 = new JFrameImagen(negativo);        
//        HistogramaFrecuencias histo = new HistogramaFrecuencias(imagen);
//        histo.graficarHistogramasRGB();
//       Image contraste3= expansionLogaritmica(5,imagen);
//       Image contraste4 =expansionLineal(0.0,255.1,imagen);    
//        Image contraste1 = expansionExponencial(0.01,imagen);
//        Image contraste2 = miExpansion(50,imagen);
//       JFrameImagen frame2 = new JFrameImagen(contraste1);
//       JFrameImagen frame3 = new JFrameImagen(contraste2);
//       JFrameImagen frame4 = new JFrameImagen(contraste3);
//       JFrameImagen frame5 = new JFrameImagen(contraste4);
//        
//        
        
        //JFrameImagen frame3 = new JFrameImagen(contraste);
        //HistogramaFrecuencias histo2 = new HistogramaFrecuencias(contraste);
        //histo2.graficarHistogramasRGB();
        
       // System.out.println();
        
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
