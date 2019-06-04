/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frecuencias;

import GUI.JFrameImagen;
import frecuencias.HerramientasColor.CanalColor;
import io.ImageManager;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import muestreo.Convolucion;
import muestreo.Expansion;

/**
 *
 * @author Roberto Cruz Leija
 */
public class Gestor {
    // imagen original
    private BufferedImage imagenOriginal;
    public Map<HerramientasColor.CanalColor, NumeroComplejo[][]> representacionEspacial;
    Map<HerramientasColor.CanalColor, NumeroComplejo[][]> representacionFrecuencias;

    public Gestor(BufferedImage imagenOriginal) {
        this.imagenOriginal = imagenOriginal;
        this.representacionEspacial = new HashMap<HerramientasColor.CanalColor, NumeroComplejo[][]>();
        this.representacionFrecuencias = new HashMap<HerramientasColor.CanalColor, NumeroComplejo[][]>();
        // obtener la informacion de los pixeles y crear los mapeos correspondientes
        for(HerramientasColor.CanalColor color: HerramientasColor.CanalColor.values()){
        
            this.representacionEspacial.put(color,obtenerDatosPorCanal(color,this.imagenOriginal));
        
        }
    }

    private NumeroComplejo[][] obtenerDatosPorCanal(HerramientasColor.CanalColor color, BufferedImage imagenOriginal) {
       NumeroComplejo[][] aux = new NumeroComplejo[this.imagenOriginal.getWidth()][this.imagenOriginal.getHeight()];
       //  recorremos en un ciclo el buffered para crear los diferentes numeros complejos
       for(int x=0;x<this.imagenOriginal.getWidth();x++){
           for(int y=0;y<this.imagenOriginal.getHeight();y++){
               aux[x][y] = new NumeroComplejo(HerramientasColor.obtenerValorPorCanal(imagenOriginal.getRGB(x, y), color),0);
           }
       }
       return aux;
    }
    
    //Agregando los otros metodos
        
    
public BufferedImage obtenerImagenFrecuencias(boolean reAjustarCuadrante) {
        /// obtenemos las dimensiones
        int anchoImagen = this.imagenOriginal.getWidth();
        int altoImagen = this.imagenOriginal.getHeight();
        BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);

        FFTCalculo fft = new FFTCalculo();
        // construir el mapeo de representacion en frecuencias utilizando FFT

        for (CanalColor canal : HerramientasColor.CanalColor.values()) {
            NumeroComplejo[][] datos = this.representacionEspacial.get(canal);
            NumeroComplejo[][] transformada = fft.calculateFT(datos, false);
            representacionFrecuencias.put(canal, transformada);
            // crear la imagen del espectro 
            for (int y = 0; y < aux.getHeight(); y++) {
                for (int x = 0; x < aux.getWidth(); x++) {
                    // modificamos la posicion de los cuadrantes 
                    int ejeX = reAjustarCuadrante ? (x + (anchoImagen / 2)) % anchoImagen : x;
                    int ejeY = reAjustarCuadrante ? (y + (altoImagen / 2)) % altoImagen : y;
                    // setear la info a la imagen 
                    // el que se ecuentre en la imagen 
                    int color1 = aux.getRGB(x, y);
                    int color2 = obtenerColorRealDeFrecuencia(ejeX, ejeY, transformada, canal);
                    int rgb = HerramientasColor.acumularColor(color1, color2);
                    aux.setRGB(x, y, rgb);

                }
            }
        }
        return aux;
    }

  private int obtenerColorRealDeFrecuencia(int ejeX, int ejeY, NumeroComplejo[][] transformada, CanalColor canal) {
        int color = (int) Math.abs(transformada[ejeX][ejeY].getReal());
        color = (int)Expansion.validarRango(color);
        color = HerramientasColor.obtenerRGBporCanal(color, canal);
        return color;
    }
  
  
  //El otro método
 public BufferedImage obtenerImagenEspacial() {
        /// obtenemos las dimensiones
        int anchoImagen = this.imagenOriginal.getWidth();
        int altoImagen = this.imagenOriginal.getHeight();
        BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);

        FFTCalculo fft = new FFTCalculo();
        // construir el mapeo de representacion en frecuencias utilizando FFT

        for (CanalColor canal : HerramientasColor.CanalColor.values()) {
            NumeroComplejo[][] datos = this.representacionFrecuencias.get(canal);
            NumeroComplejo[][] transformadaInv = fft.calculateFT(datos, true);
            representacionEspacial.put(canal, transformadaInv);
            // crear la imagen del espectro 
            for (int y = 0; y < aux.getHeight(); y++) {
                for (int x = 0; x < aux.getWidth(); x++) {

                    int color = (int) Math.abs(transformadaInv[x][y].getReal());
                    color =(int)Expansion.validarRango(color);
                    color = HerramientasColor.obtenerRGBporCanal(color, canal);

                    int rgb = HerramientasColor.acumularColor(aux.getRGB(x, y), color);
                    aux.setRGB(x, y, rgb);
                }
            }
        }
        return aux;

}
   public  BufferedImage mult(){
     
        int anchoImagen = this.imagenOriginal.getWidth();
        int altoImagen = this.imagenOriginal.getHeight();
        BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);

        FFTCalculo fft = new FFTCalculo();
        FiltroIdealPasaBajas al=new FiltroIdealPasaBajas(512,512,50);
        // construir el mapeo de representacion en frecuencias utilizando FFT

        for (CanalColor canal : HerramientasColor.CanalColor.values()) {
            NumeroComplejo[][] datos = this.representacionFrecuencias.get(canal);
            NumeroComplejo[][] transformadaInv = fft.calculateFT(datos, true);
            al.generar();
            al.setMatriz(datos);
            NumeroComplejo[][] datos2=al.getMatriz();
            representacionEspacial.put(canal, transformadaInv);
            // crear la imagen del espectro 
            for (int y = 0; y < aux.getHeight(); y++) {
                for (int x = 0; x < aux.getWidth(); x++) {

                    int color = (int) Math.abs(transformadaInv[x][y].getReal());
                    int color2=(int) Math.abs(datos2[x][y].getReal());
                    int num= color*color2;
                    num = (int)Expansion.validarRango(num);
                    num = HerramientasColor.obtenerRGBporCanal(num, canal);

                    int rgb = HerramientasColor.acumularColor(aux.getRGB(x, y), num);
                    
                    aux.setRGB(x, y, rgb);
                }
            }
        }
        return aux;
   }
     public void aplicarFiltro(NumeroComplejo[][] matrizFiltro) {
        // Se obtienen las dimensiones de la matriz de complejos del filtro
        int ancho = matrizFiltro.length;
        int alto = matrizFiltro[0].length;
        
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                // Obtener el RGB de la representación en frecuencias.
                //if(matrizFiltro[i][j].getReal() < 1) {
                    //System.out.println(matrizFiltro[i][j].getReal());
                    int rgb = obtenerPixelDominioFrecuencias(i, j, true);
                    Color aux = new Color(rgb);
                    int r = (int) (aux.getRed() * matrizFiltro[i][j].getReal());
                    int g = (int) (aux.getGreen() * matrizFiltro[i][j].getReal());
                    int b = (int) (aux.getBlue() * matrizFiltro[i][j].getReal());
                    r = (int) Expansion.validarRango(r);
                    g = (int) Expansion.validarRango(g);
                    b = (int) Expansion.validarRango(b);
                    aux = new Color(r, g, b);
                    setPixelDominioFrecuencias(i, j, true, aux.getRGB());
                //}
            }
        }
    }
    
    private int obtenerPixelDominioFrecuencias(int i, int j, boolean encuadre) {
        // obtener las dimensiones de la imagen original
        int ancho = this.imagenOriginal.getWidth();
        int alto = this.imagenOriginal.getHeight();
        // Modificamos la posición de los cuadrantes
        int ejeX = encuadre ? (i + (ancho / 2)) % ancho : i;
        int ejeY = encuadre ? (j + (alto / 2)) % alto : j;
        // Acumulamos por cada canal
        int valorColor = 0;
        for (CanalColor canal: CanalColor.values()) {
            // se obtiene la matriz para el canal actual
            NumeroComplejo[][] aux = this.representacionFrecuencias.get(canal);
            valorColor += obtenerColorRealDeFrecuencia(ejeX, ejeY, aux, canal);
        }
        
        return valorColor;
    }

    private void setPixelDominioFrecuencias(int x, int y, boolean encuadre, int color) {
        // Se obtienen las dimensiones
        int anchoImagen = this.imagenOriginal.getWidth();
        int altoImagen = this.imagenOriginal.getHeight();
        // Modificamos la posición de los cuadrantes
        int ejeX = encuadre ? (x + (anchoImagen / 2)) % anchoImagen : x;
        int ejeY = encuadre ? (x + (altoImagen / 2)) % altoImagen : y;
        // Recorrer por canal de color
        for (CanalColor canal : CanalColor.values()) {
            NumeroComplejo[][] datos = this.representacionFrecuencias.get(canal);
            int nuevoRGB = HerramientasColor.obtenerValorPorCanal(color, canal);
            datos[ejeX][ejeY] = new NumeroComplejo(nuevoRGB, nuevoRGB);
        }
    }
  
}
