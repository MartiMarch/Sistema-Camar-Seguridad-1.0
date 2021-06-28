package Modelo;

import ClasesAuxiliares.Correo;
import Controlador.Controlador;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.DriverManager;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.opencv.core.Core;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.features2d.ORB;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

public class Movimiento{
    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.loadLibrary("opencv_java451");
        System.loadLibrary("opencv_videoio_ffmpeg451_64");
        System.loadLibrary("openh264-1.8.0-win64");
    }
    
    private Controlador controlador;
    private ArrayList<Camara> camaras;
    private ArrayList<Cliente> clientes;
    private ArrayList<HRead> hilosLeer = new ArrayList();
    private ArrayList<HWrite> hilosEscribir = new ArrayList();
    
    public Movimiento(Controlador controlador) throws SQLException, ClassNotFoundException, InterruptedException
    {
        this.controlador = controlador;
        camaras = controlador.getSCS().obtenerCamras();
        for(int i = 0; i < camaras.size(); ++i)
        {
            BlockingQueue queue = new ArrayBlockingQueue(124);
            HRead hr = new HRead(camaras.get(i).getURL(), queue);
            HWrite hw = new HWrite(hr.getFps(), hr.getWidth(), hr.getHeigth(), this.controlador, camaras.get(i).getURL(), queue);
            new Thread(hr).start();
            new Thread(hw).start();
            hilosLeer.add(hr);
            hilosEscribir.add(hw);
        }
    }
    
    public class HRead extends Thread
    {
        private BlockingQueue queue;
        private String url;
        private VideoCapture camara;
        private int h;
        private int w;
        private double fps;
        private volatile boolean ejecucion = true;
        
        public HRead(String url, BlockingQueue<Mat> queue) throws InterruptedException
        {
            this.url = url;
            this.queue = queue;
            camara = new VideoCapture(url);
            h = (int) camara.get(Videoio.CAP_PROP_FRAME_HEIGHT);
            w = (int) camara.get(Videoio.CAP_PROP_FRAME_WIDTH);
            fps = camara.get(Videoio.CAP_PROP_FPS);
        }
        
        @Override
        public void run()
        {
            if(ejecucion)
            {
                camara = new VideoCapture(url);
            }
            while(!camara.isOpened() && ejecucion)
            {
                try {Thread.sleep(1);} catch(InterruptedException ex){Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);}
            }
            while(ejecucion)
            {
                Mat imagen = new Mat();
                camara.grab();
                camara.read(imagen);
                try {
                    queue.put(imagen);
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        public int getHeigth()
        {
            return h;
        }
        
        public int getWidth()
        {
            return w;
        }
        
        public double getFps()
        {
            return fps;
        }
        
        public String getUrl()
        {
            return url;
        }
        
        public void finalizarHilo() throws InterruptedException
        {
            
            ejecucion = false;
            Thread.sleep(100);
            camara.release();
        }
    }
    
    public class HWrite extends Thread{
        private BlockingQueue queue;
        private double fps;
        private int w;
        private int h;
        private String ruta = "C:\\SGCSR\\";
        private String nombreArchivo;
        private String url;
        private Controlador controlador;
        private volatile boolean ejecucion = true;
        private volatile boolean algoritmo = true;
        private ORB orb = ORB.create(100);
        
        public HWrite(double fps, int w, int h, Controlador controlador, String url, BlockingQueue queue)
        {
            this.fps = fps;
            this.w = w;
            this.h = h;
            this.url = url;
            this.controlador = controlador;
            this.queue = queue;
        }
        
        @Override
        public void run()
        {
            if(ejecucion)
            {
                Mat primerFrame = null;
                try {
                    primerFrame = reiniciarFrame((Mat) queue.take());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
                }

                VideoWriter salida = new VideoWriter();
                int cambiarReferencia = 0;
                int cerrarVideo = 0;
                boolean movimiento = false;
                double tiempoInicial = getTime();
                EsquinasValidas ev = null;
                try {
                    ev = obtenerEsquinasValidas();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                while(ejecucion)
                {
                    if(!queue.isEmpty())
                    {
                        if(algoritmo)
                        {
                            if(cambiarReferencia == 60)
                            {
                                primerFrame.release();
                                try{
                                    primerFrame = reiniciarFrame((Mat) queue.take());
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            ++cambiarReferencia;
                            
                            //Se transforma la imagen de la camara a una imagen gris
                            Mat imagen = null;
                            Mat imagenGris = new Mat();
                            try {
                                imagen = (Mat) queue.take();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Imgproc.cvtColor(imagen, imagenGris, Imgproc.COLOR_BGR2GRAY);

                            //Se hace borrosa la imagen para que sea mas facil detectar el movimiento
                            Mat imagenBorrosa = new Mat();
                            Imgproc.GaussianBlur(imagenGris, imagenBorrosa, new Size(21, 21), 0);

                            //Comparamos la imagen mediante el algoritmo absdiff y creamos una imagen de pixeles blancos y negros
                            Mat imagenComparacion = new Mat();
                            Core.absdiff(primerFrame, imagenBorrosa, imagenComparacion);

                            //En una imagen pasada a grises se busca los pixeles más intensos
                            Mat pixelesIntensos = new Mat();
                            Imgproc.threshold(imagenComparacion, pixelesIntensos, 30, 255, Imgproc.THRESH_BINARY);
                            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
                            Imgproc.dilate(pixelesIntensos, pixelesIntensos, kernel, new Point(-1,-1), 2);

                            //Sebuscan los bordes
                            List<MatOfPoint> bordes = new ArrayList<>();
                            Imgproc.findContours(pixelesIntensos, bordes, pixelesIntensos, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

                            if(bordes.size() == 0)
                            {
                                ++cerrarVideo;
                            }
                            for(int i = 0; i <  bordes.size(); ++i)
                            {
                                //Si el valor es muy pequeño la sensibilidad es emnor
                                if(Imgproc.contourArea(bordes.get(i))<100)
                                {
                                    if(!movimiento)
                                    {
                                        salida = crearVideo();
                                        HTareas enviarCorreos = new HTareas(controlador, nombreArchivo);
                                        new Thread(enviarCorreos).start();
                                    }
                                    movimiento = true;
                                    salida.write(imagenGris);
                                }
                            }
                            if(cerrarVideo == 60)
                            {
                                salida.release();
                                cerrarVideo = 0;
                                movimiento = false;
                                nombreArchivo = null;
                            }

                            //IMPORTANTE: si no se liberan los recursoso el consumo de RAM es muy grande.
                            imagen.release();
                            imagenGris.release();
                            imagenBorrosa.release();
                            imagenComparacion.release();
                            pixelesIntensos.release();
                            kernel.release();
                        }
                        else
                        {
                            try
                            {
                                Mat imagen = (Mat) queue.take();
                                
                                Mat imagen1 = imagen.submat(0, (int) imagen.rows()/2, 0, (int) imagen.cols()/2);
                                Mat imagen2 = imagen.submat(0, (int) imagen.rows()/2, (int) imagen.cols()/2, (int) imagen.cols());
                                Mat imagen3 = imagen.submat((int) imagen.rows()/2, (int) imagen.rows(), 0, (int) imagen.cols()/2);
                                Mat imagen4 = imagen.submat((int) imagen.rows()/2, (int) imagen.rows(), (int) imagen.cols()/2, (int) imagen.cols());
                                
                                Mat imagenGris = new Mat();
                                Imgproc.cvtColor(imagen, imagenGris, Imgproc.COLOR_BGR2GRAY);
                                
                                Mat imagenGris1 = imagenGris.submat(0, (int) imagenGris.rows()/2, 0, (int) imagenGris.cols()/2);
                                Mat imagenGris2 = imagenGris.submat(0, (int) imagenGris.rows()/2, (int) imagenGris.cols()/2, (int) imagenGris.cols());
                                Mat imagenGris3 = imagenGris.submat((int) imagenGris.rows()/2, (int) imagenGris.rows(), 0, (int) imagenGris.cols()/2);
                                Mat imagenGris4 = imagenGris.submat((int) imagenGris.rows()/2, (int) imagenGris.rows(), (int) imagenGris.cols()/2, (int) imagenGris.cols());
                                
                                MatOfPoint2f nuevasEsquinas1 = new MatOfPoint2f();
                                MatOfPoint2f nuevasEsquinas2 = new MatOfPoint2f();
                                MatOfPoint2f nuevasEsquinas3 = new MatOfPoint2f();
                                MatOfPoint2f nuevasEsquinas4 = new MatOfPoint2f();
                                       
                                MatOfByte estatus1 = new MatOfByte();
                                MatOfByte estatus2 = new MatOfByte();
                                MatOfByte estatus3 = new MatOfByte();
                                MatOfByte estatus4 = new MatOfByte();
                                
                                MatOfFloat error1 = new MatOfFloat();
                                MatOfFloat error2 = new MatOfFloat();
                                MatOfFloat error3 = new MatOfFloat();
                                MatOfFloat error4 = new MatOfFloat();
                                
                                Video.calcOpticalFlowPyrLK(ev.imagenesIniciales.get(0), imagenGris1, ev.esquinasIniciales.get(0), nuevasEsquinas1, estatus1, error1, new Size(15, 15), 2, new TermCriteria(TermCriteria.COUNT + TermCriteria.EPS, 10, 0.03));
                                Video.calcOpticalFlowPyrLK(ev.imagenesIniciales.get(1), imagenGris2, ev.esquinasIniciales.get(1), nuevasEsquinas2, estatus2, error2, new Size(15, 15), 2, new TermCriteria(TermCriteria.COUNT + TermCriteria.EPS, 10, 0.03));
                                Video.calcOpticalFlowPyrLK(ev.imagenesIniciales.get(2), imagenGris3, ev.esquinasIniciales.get(2), nuevasEsquinas3, estatus3, error3, new Size(15, 15), 2, new TermCriteria(TermCriteria.COUNT + TermCriteria.EPS, 10, 0.03));
                                Video.calcOpticalFlowPyrLK(ev.imagenesIniciales.get(3), imagenGris4, ev.esquinasIniciales.get(3), nuevasEsquinas4, estatus4, error4, new Size(15, 15), 2, new TermCriteria(TermCriteria.COUNT + TermCriteria.EPS, 10, 0.03));
                                
                                if(!revisarEstatus(estatus1.toArray()) || !revisarEstatus(estatus2.toArray()) || !revisarEstatus(estatus3.toArray()) || !revisarEstatus(estatus4.toArray()))
                                {
                                    System.out.println("MOVIMIENTO\n");
                                }
                                
                                double tiempoActual = getTime();
                                if(tiempoActual - tiempoInicial >= 900000)
                                {
                                    tiempoActual = tiempoInicial;
                                    ev = obtenerEsquinasValidas();
                                }
                                
                                imagen.release();
                                imagen1.release();
                                imagen2.release();
                                imagen3.release();
                                imagen4.release();
                                imagenGris.release();
                                imagenGris1.release();
                                imagenGris2.release();
                                imagenGris3.release();
                                imagenGris4.release();
                                nuevasEsquinas1.release();
                                nuevasEsquinas2.release();
                                nuevasEsquinas3.release();
                                nuevasEsquinas4.release();
                                estatus1.release();
                                estatus2.release();
                                estatus3.release();
                                estatus4.release();
                                error1.release();
                                error2.release();
                                error3.release();
                                error4.release();
                            }
                            catch(InterruptedException ex)
                            {
                                Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
        
        public boolean revisarEstatus(byte estatus[])
        {
            boolean movimiento = false;
            for(int i = 0; i < estatus.length && !movimiento; ++i)
            {
                if(estatus[i] == 0)
                {
                   movimiento = true;
                }
            }
            return movimiento;
        }
        
        public Mat reiniciarFrame(Mat frame)
        {
            Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
            Imgproc.GaussianBlur(frame, frame, new Size(21, 21), 0);
            return frame;
        }
        
        public VideoWriter crearVideo()
        {
            DateTimeFormatter fecha_ = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime horarioLocal = LocalDateTime.now();  
            String fecha = fecha_.format(horarioLocal);
            fecha = fecha.replace("/", "-");
            fecha = fecha.replace(":", "_");
            File directorio = new File(ruta);
            if(!directorio.exists())
            {
                directorio.mkdir();
            }
            nombreArchivo = fecha + ".mp4";
            Size size = new Size(this.w, this.h);
            VideoWriter salida  = new VideoWriter(ruta + nombreArchivo, VideoWriter.fourcc('H', '2', '6', '4'), this.fps, size, false);  
            
            return salida;
        }
        
        public void finalizarHilo() throws InterruptedException
        {
            ejecucion = false;
            Thread.sleep(100);
        }
        
        public void cambiarAlgoritmo(boolean algoritmo)
        {
            this.algoritmo = algoritmo;
        }
        
        public MatOfPoint2f toMatOfPoint2f(MatOfKeyPoint entrada)
        {
            KeyPoint[] puntosClave = entrada.toArray();
            ArrayList<Point> puntos = new ArrayList();
            for(int i = 0; i < puntosClave.length; ++i)
            {
                puntos.add(puntosClave[i].pt);
            }
            MatOfPoint resultado = new MatOfPoint();
            resultado.fromList(puntos);
            return new MatOfPoint2f(resultado.toArray());
        }
        
        public MatOfPoint2f procesarEsquinasInicialesSubimagen(Mat subimagen)
        {
            MatOfKeyPoint esquinasIniciales = new MatOfKeyPoint();
            orb.detect(subimagen, esquinasIniciales);
            subimagen.release();
            return toMatOfPoint2f(esquinasIniciales);
        }
        
        public double getTime()
        {
            return System.currentTimeMillis();
        }
        
        class EsquinasValidas
        {
            public ArrayList<MatOfPoint2f> esquinasIniciales = new ArrayList();
            public ArrayList<Mat> imagenesIniciales = new ArrayList();
            public EsquinasValidas(){}
            public ArrayList<MatOfPoint2f> getEsquinasIniciales()
            {
                return esquinasIniciales;
            }
            public ArrayList<Mat> getImagenesIniciales()
            {
                return imagenesIniciales;
            }
        }
        
        public EsquinasValidas obtenerEsquinasValidas() throws InterruptedException
        {
            EsquinasValidas ev = new EsquinasValidas();
            MatOfPoint2f esquinasIniciales1 = new MatOfPoint2f();
            MatOfPoint2f esquinasIniciales2 = new MatOfPoint2f();
            MatOfPoint2f esquinasIniciales3 = new MatOfPoint2f();
            MatOfPoint2f esquinasIniciales4 = new MatOfPoint2f();
            while(esquinasIniciales1.empty() || esquinasIniciales2.empty() || esquinasIniciales3.empty() || esquinasIniciales4.empty())
            {
                Mat primeraImagen = (Mat) queue.take();
                Mat primeraImagenGris = new Mat();
                Imgproc.cvtColor(primeraImagen, primeraImagenGris, Imgproc.COLOR_BGR2GRAY);
            
                ev.esquinasIniciales.clear();
                ev.imagenesIniciales.clear();
                
                Mat primeraImagenGris1 = primeraImagenGris.submat(0, (int) primeraImagenGris.rows()/2, 0, (int) primeraImagenGris.cols()/2);
                Mat primeraImagenGris2 = primeraImagenGris.submat(0, (int) primeraImagenGris.rows()/2, (int) primeraImagenGris.cols()/2, (int) primeraImagenGris.cols());
                Mat primeraImagenGris3 = primeraImagenGris.submat((int) primeraImagenGris.rows()/2, (int) primeraImagenGris.rows(), 0, (int) primeraImagenGris.cols()/2);
                Mat primeraImagenGris4 = primeraImagenGris.submat((int) primeraImagenGris.rows()/2, (int) primeraImagenGris.rows(), (int) primeraImagenGris.cols()/2, (int) primeraImagenGris.cols());
                
                esquinasIniciales1 = procesarEsquinasInicialesSubimagen(primeraImagenGris1);
                esquinasIniciales2 = procesarEsquinasInicialesSubimagen(primeraImagenGris2);
                esquinasIniciales3 = procesarEsquinasInicialesSubimagen(primeraImagenGris3);
                esquinasIniciales4 = procesarEsquinasInicialesSubimagen(primeraImagenGris4);
                
                ev.imagenesIniciales.add(primeraImagenGris1);
                ev.imagenesIniciales.add(primeraImagenGris2);
                ev.imagenesIniciales.add(primeraImagenGris3);
                ev.imagenesIniciales.add(primeraImagenGris4);
                
                ev.esquinasIniciales.add(esquinasIniciales1);
                ev.esquinasIniciales.add(esquinasIniciales2);
                ev.esquinasIniciales.add(esquinasIniciales3);
                ev.esquinasIniciales.add(esquinasIniciales4);
                
                primeraImagenGris1.release();
                primeraImagenGris2.release();
                primeraImagenGris3.release();
                primeraImagenGris4.release();
            }
            esquinasIniciales1.release();
            esquinasIniciales2.release();
            esquinasIniciales3.release();
            esquinasIniciales4.release();
            
            return ev;
        }
    }
    
    public void addCamra(String url) throws InterruptedException
    {
        url = controlador.getEncriptador().desencriptar(url, controlador.getAdministrador().getContraseña());
        BlockingQueue queue = new ArrayBlockingQueue(124);
        HRead hr = new HRead(url, queue);
        HWrite hw = new HWrite(hr.getFps(), hr.getWidth(), hr.getHeigth(), this.controlador, url, queue);
        new Thread(hr).start();
        new Thread(hw).start();
        hilosLeer.add(hr);
        hilosEscribir.add(hw);
    }
    
    public void finalizarHilos(String url) throws InterruptedException
    {
        url = controlador.getEncriptador().desencriptar(url, controlador.getAdministrador().getContraseña());
        for(int i = 0; i < hilosLeer.size(); ++i)
        {
            if(hilosLeer.get(i).getUrl().equals(url))
            {
                hilosLeer.get(i).finalizarHilo();
                hilosEscribir.get(i).finalizarHilo();
            }
        }
    }
    
    class HTareas extends Thread
    {
        private Controlador controlador;
        private String fecha;
        
        public HTareas(Controlador controlador, String fecha)
        {
            this.controlador = controlador;
            this.fecha = fecha;
        }
        
        @Override
        public void run()
        {
            // 1 - Se envía el correo a todos los clientes
            try {
                for(int i = 0; i < controlador.getSCS().obtenerClientes().size(); ++i)
                {
                    String correo = controlador.getSCS().obtenerClientes().get(i).getCorreo();
                    Correo enviarCorreo = new Correo();
                    enviarCorreo.enviarCorreo(controlador.getAdministrador().getEmail(), controlador.getAdministrador().getContraseñaCorreo(), correo, 
                            "SGCSR - Movimiento detectado.", 
                            "Se ha detectado movimiento el " + fecha);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // 2 - Se actualiza la lista de vídeos
            fecha = fecha.substring(0, fecha.length()-4);
            String dia = fecha.substring(0, 2);
            String mes = fecha.substring(3, 5);
            String año = fecha.substring(6, 10);
            String hora = fecha.substring(11, 13);
            String minutos = fecha.substring(14, 16);
            String segundos = fecha.substring(17, 19);
            try { 
                Class.forName("com.mysql.jdbc.Driver");
                Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sgcsr","root","3+UNO=cuatro");
                Statement st = (Statement) conexion.createStatement();
                String insertarVideo = "INSERT INTO videos VALUES ('" + fecha + "', '" + Integer.parseInt(año) + "', '" + Integer.parseInt(dia) + "', '" + Integer.parseInt(mes) + 
                                       "', '" + Integer.parseInt(segundos) + "', '" + Integer.parseInt(hora) + "', '" + Integer.parseInt(minutos) + "');";
                st.executeUpdate(insertarVideo);
                controlador.getVAdministrar().actualizarPanelVideos();
                
                // 3 - se generan las alarmas y se añaden al registro
                String insertarAlarma = "INSERT INTO alarmas VALUES('" + fecha + "', '" + Integer.parseInt(año) + "', '" + Integer.parseInt(dia) + "', '" + Integer.parseInt(mes) + 
                       "', '" + Integer.parseInt(segundos) + "');";
                st.executeUpdate(insertarAlarma);
                for(int i = 0; i < controlador.getClientes().size(); ++i)
                {
                    Cliente cliente = controlador.getClientes().get(i);
                    String saberEstadoCamara = "SELECT estado FROM camarasclientes WHERE nombreCliente = '" + controlador.getSCS().obtenerClientes().get(i).getNombre() + "'";
                    ResultSet rs = st.executeQuery(saberEstadoCamara);
                    String estado = "";
                    if(rs.next())
                    {
                        estado = rs.getString("estado");
                    }
                    if(estado.equals("ACTIVADA"))
                    {
                        String crearAlarmaCliente = "INSERT INTO alarmasclientes VALUES(NULL, '" + controlador.getSCS().obtenerClientes().get(i).getNombre() + "', '" + fecha + "');";
                        st.executeUpdate(crearAlarmaCliente);
                    }
                }
                st.close();
                conexion.close();
                
                // 3 - Se ctualiza el panel de los registros
                controlador.getVAdministrar().actualizarPanelRegistro();
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Movimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void cambiarAlgorimto(boolean seleccion)
    {
        for(int i = 0; i < hilosEscribir.size(); ++i)
        {
            hilosEscribir.get(i).cambiarAlgoritmo(seleccion);
        }
    }
}
