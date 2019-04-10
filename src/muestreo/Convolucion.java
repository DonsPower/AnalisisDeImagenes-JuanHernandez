
package muestreo;

import static Expansion.expansionImage.validarRango;
import io.ImageManager;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static muestreo.kircho.arregloMascaras;
import static muestreo.kircho.arrglomascaras1;
import static muestreo.kircho.arrglomascaras2;
import static muestreo.kircho.arrglomascaras3;
import static muestreo.kircho.arrglomascaras4;
import static muestreo.kircho.arrglomascaras5;
import static muestreo.kircho.laplace;

/**
 *
 * @author Dons
 */
public class Convolucion {
    
     private int dim;
    private Image imagenOriginal;
    private double kernel[][];

    public Convolucion(Image imagenOriginal) {
        this.dim = 0;
        this.imagenOriginal = imagenOriginal;
        this.kernel = null;
    }
    
    public Image aplicar(double kernel[][], int divisor){
       this.dim = kernel[0].length;
       this.kernel = kernel;
       BufferedImage nueva = new BufferedImage(this.imagenOriginal.getWidth(null),this.imagenOriginal.getHeight(null),BufferedImage.TYPE_INT_RGB);
       BufferedImage bi = ImageManager.toBufferedImage(imagenOriginal);
        
       //System.out.println("W:"+nueva.getWidth()+"H:"+nueva.getHeight());
       //proceso iterativo para generar un imagen nueva
       for(int x=0; x<this.imagenOriginal.getWidth(null);x++){
           for(int y=0; y<this.imagenOriginal.getHeight(null);y++){
           double muestra[][] =extraerMuestra(x,y,bi);
            //System.out.println(x+","+y);
            // validar que la muestra se generó 
            if(muestra!=null){
            Color colorRes = convolucionarKirsch(muestra,divisor);
            nueva.setRGB(x, y, colorRes.getRGB());
            
            }else{
            nueva.setRGB(x, y, new Color(255,255,255).getRGB());
            
            }
                 
           }
       }
       
       return ImageManager.toImage(nueva);
    }

    private double[][] extraerMuestra(int x, int y, BufferedImage bi) {
        double matriz[][] = new double[this.kernel[0].length][this.kernel[0].length];
       int xx=0;
        int yy=0;
        try {
         // recorremos la imagen 
         for(int i=x-(this.kernel[0].length-1)/2;i<=x+(this.kernel[0].length-1)/2;i++){
            for(int j=y-(this.kernel[0].length-1)/2;j<=y+(this.kernel[0].length-1)/2;j++){
            matriz[xx][yy] = bi.getRGB(i,j);
            yy++;
            }
            yy=0;
            xx++;
         }
          return matriz;
        } catch (Exception e) {
           // System.out.println("Indice no valido");
            return null;
        }
       
    }

    public static Color convulacionar(double[][] kernel, double[][] muestra, int divisor) {
        int acumuladorR = 0;
        int acumuladorG = 0;
        int acumuladorB = 0;
        Color aux;
        // recorremos el kernel y la muestra 
        for(int x=0; x<kernel[0].length;x++)
            for(int y=0; y<kernel[0].length;y++){
         aux = new Color((int)muestra[x][y]);
         acumuladorR+=(kernel[x][y]*aux.getRed());
         acumuladorG+=(kernel[x][y]*aux.getGreen());
         acumuladorB+=(kernel[x][y]*aux.getBlue());
        
         }
        acumuladorR/=divisor;
        acumuladorG/=divisor;
        acumuladorB/=divisor;
        
        return new Color((int)validarRango(acumuladorR),(int)validarRango(acumuladorG),(int)validarRango(acumuladorB));
        
    }
    private Color convolucionarKirsch(double[][] muestra, int divisor) {
        // recorremos las mascaras 
        int mayorR = -1;
        int mayorG = -1;
        int mayorB = -1;
        int r,g,b;
            //for(int i=0; i<1;i++){
                Color color = convulacionar(laplace, muestra, divisor);
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                if(r>mayorR)mayorR=r;
                if(g>mayorG)mayorG=g;
                if(b>mayorB)mayorB=b;
            //}
            return new Color((int)validarRango(mayorR),(int)validarRango(mayorG), (int)validarRango(mayorB));
        
        }
    
    
    
}