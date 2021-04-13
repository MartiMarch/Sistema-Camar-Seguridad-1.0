package Modelo;

import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

public class Movimiento {
    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary("opencv_java451");
        System.loadLibrary("opencv_videoio_ffmpeg451_64");
        System.loadLibrary("openh264-1.8.0-win64");
    }
    
    private ArrayList<Camara> camaras;
    
    
    
    class HiloLeer extends Thread
    {
        private VideoCapture camara = new VideoCapture();
           
        public HiloLeer()
        {
            
        }
        
        @Override
        public void run()
        {
            
        }
    }
    
}
