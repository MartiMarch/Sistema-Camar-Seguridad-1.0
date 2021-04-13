package Vista;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class SalidaCamara extends javax.swing.JPanel implements Runnable{
    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary("opencv_java451");
        System.loadLibrary("opencv_videoio_ffmpeg451_64");
    }
    
    private VideoCapture video;
    private Mat frame;
    private BufferedImage buffer;
    private String url;
    private int altura = 0;
    private int anchura = 0;
    
    public SalidaCamara(String url) {
        this.url = url;
        initComponents();
        new Thread(this).start();  
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!(buffer == null)){
            g.drawImage(buffer, 0, 0, buffer.getWidth(), buffer.getHeight(), null);
        }
        return;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void run() {
        video = new VideoCapture(url);
        if(video.isOpened())
        {
            while(true)
            {
                frame = new Mat();
                video.read(frame);
                if(!frame.empty())
                {
                    MatToBufferedImage(frame);
                    this.repaint();
                }
                frame.release();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"No se pudo acceder a la cámara");
        }
    }
    
    private void MatToBufferedImage(Mat frame) {
        anchura = frame.width();
        altura = frame.height();
        int canales = frame.channels();
        
        byte[] source = new byte[anchura * altura * canales];
        frame.get(0, 0, source);
        buffer = new BufferedImage(anchura, altura, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] salida = ((DataBufferByte) buffer.getRaster().getDataBuffer()).getData();
        System.arraycopy(source, 0, salida, 0, source.length); 
    }
    
    public int getAltura()
    {
        return buffer.getHeight();
    }
    
    public int getAnchura()
    {
        return buffer.getWidth();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}