package ClasesAuxiliares;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketCorreo extends Thread{
    private final int PORT = 6060;
    private ServerSocket serverSocket;
    private String correo, contraseñaCorreo;
    
    public SocketCorreo(String correo, String contraseñaCorreo) throws IOException
    {
        this.serverSocket = new ServerSocket(PORT);
        this.correo = correo;
        this.contraseñaCorreo = contraseñaCorreo;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(correo);
                writer.flush();
                writer.println(contraseñaCorreo);
                writer.close();
                output.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketCorreo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
