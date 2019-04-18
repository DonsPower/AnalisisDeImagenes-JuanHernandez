/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis.de.imagenes;

import GUI.JFrameImagen;
import io.ImageManager;
import java.awt.Image;
import muestreo.EscalaGrises;
import muestreo.HistogramaFrecuencias;
import muestreo.Umbralizacion;
import muestreo.centroDeMasa;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs; // imread, imwrite, etc 
import org.opencv.videoio.VideoCapture; // VideoCapture﻿
        
 
/**
 *
 * @author Dons
 */
public class analisisOpenCv {
    
           public static void main(String args[]){ 
               
        
//        //------------------Imagen Binarizada----------------
//               Image imagen = ImageManager.openImage();
//        JFrameImagen frame1 = new JFrameImagen(imagen);
//        
//        Image binaria = Umbralizacion.umbralizacionSimple(70,imagen);
//         HistogramaFrecuencias histo = new HistogramaFrecuencias(binaria);
//        histo.graficarHistogramasRGB();
//        JFrameImagen frame3 = new JFrameImagen(binaria);
//        Image resu=centroDeMasa.umbralizacionMasa(binaria);
//         JFrameImagen frame4 = new JFrameImagen(resu);             
               
               System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
               VideoCapture camera = new VideoCapture(0);
    	
                if(!camera.isOpened()){
                    System.out.println("Error");
                }
                else {
                    Mat frame = new Mat();
                    while(true){
                      if (camera.read(frame)){
    	    		System.out.println("Frame Obtained");
    	    		System.out.println("Captured Frame Width " + 
    	    		frame.width() + " Height " + frame.height());
                        
    	    		Imgcodecs.imwrite("camera.jpg", frame);
                        
    	    		System.out.println("OK");
    	    		
                      }
                    }	
                }
    	camera.release();
           }

        
}
