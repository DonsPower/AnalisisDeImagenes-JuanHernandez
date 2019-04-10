/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muestreo;

import static Expansion.expansionImage.validarRango;
import GUI.JFrameImagen;
import io.ImageManager;
import java.awt.Color;
import java.awt.Image;
import static muestreo.Convolucion.convulacionar;

/**
 *
 * @author Dons
 */
public class kircho {
    Image iamgenOriginal;
    
    public kircho(Image imagenOriginal){
        this.iamgenOriginal=imagenOriginal;
    }
    private static final double[][] diferenciaPixelesGx = {{0.0, 0.0, 0.0}, {0.0, 1.0, -1.0}, {0.0, 0.0, 0.0}};
    private static final double[][] diferenciaPixelesGy = {{0.0, -1.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 0.0}};
    public static double [][][] arrglomascaras1 ={diferenciaPixelesGx,diferenciaPixelesGy };
    
    
     // mascara de diferencia de pixeless separados
    private  static final double[][] diferenciaPixelesSeparadosGx = {{0.0, 0.0, 0.0}, {1.0, 0.0, -1.0}, {0.0, 0.0, 0.0}};
    private  static final double[][] diferenciaPixelesSeparadosGy = {{0.0, -1.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 1.0, 0.0}};
    public static double [][][] arrglomascaras2 ={diferenciaPixelesSeparadosGx,diferenciaPixelesSeparadosGy };
    
     // mascara de operador prewitt
    private static final double[][] prewittGx = {{1.0, 0.0, -1.0}, {1.0, 0.0, -1.0}, {1.0, 0.0, -1.0}};
    private static final  double[][] prewittGy = {{-1.0, -1.0, -1.0}, {0.0, 0.0, 0.0}, {1.0, 1.0, 1.0}};
    public static double [][][] arrglomascaras3 ={prewittGx,prewittGy };
    
    // mascara de operador Sobel
    private static final double[][] SobelGx = {{1.0, 0.0, -1.0}, {2.0, 0.0, -2.0}, {1.0, 0.0, -1.0}};
    private static final double[][] SobelGy = {{-1.0, -2.0, -1.0}, {0.0, 0.0, 0.0}, {1.0, 2.0, 1.0}};
    public static double [][][] arrglomascaras4 ={SobelGx,SobelGy };
    // mascara dde operador Roberts
    private static double[][] robertsGx = {{0.0, 0.0, -1.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 0.0}};
    private static double[][] robertsGy = {{-1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 0.0}};
     public static double [][][] arrglomascaras5 ={robertsGx,robertsGy };
     static double[][] laplace = {{0.0, 1.0, 0.0}, {1.0, -4.0, 1.0}, {0.0, 1.0, 0.0}};
    
    private static final double[][] kirsch1 = {{-3, -3, 5}, {-3, 0, 5}, {-3, -3, 5}};
    private static double[][] kirsch2 = {{-3, 5, 5}, {-3, 0, 5}, {-3, -3, -3}};
    private static double[][] kirsch3 = {{5, 5, 5}, {-3, 0, -3}, {-3, -3, -3}};
    private static double[][] kirsch4 = {{5, 5, -3}, {5, 0, -3}, {-3, -3, -3}};
    private static double[][] kirsch5 = {{5, -3, -3}, {5, 0, -3}, {5, -3, -3}};
    private static double[][] kirsch6 = {{-3, -3, -3}, {5, 0, -3}, {5, 5, -3}};
    private static double[][] kirsch7 = {{-3, -3, -3}, {-3, 0, -3}, {5, 5, 5}};
    private static double[][] kirsch8 = {{-3, -3, -3}, {-3, 0, 5}, {-3, 5, 5}};
    public static double[][][] arregloMascaras = {kirsch1, kirsch2, kirsch3,
        kirsch4, kirsch5, kirsch6,
        kirsch7, kirsch8};
    
       private Color convolucionarKirsch(double[][] muestra, int divisor) {
        // recorremos las mascaras 
        int mayorR = -1;
        int mayorG = -1;
        int mayorB = -1;
        int r,g,b;
            for(int i=0; i<8;i++){
                Color color = convulacionar(arregloMascaras[i], muestra, divisor);
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                if(r>mayorR)mayorR=r;
                if(g>mayorG)mayorG=g;
                if(b>mayorB)mayorB=b;
            }
            return new Color((int)validarRango(mayorR),(int)validarRango(mayorG), (int)validarRango(mayorB));
        
        }
}
