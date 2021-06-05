package ClasesAuxiliares;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServidor extends Thread{
    private final int PORT = 6050;
    private ServerSocket serverSocket;
    private String contraseña;
    
    public SocketServidor(String contraseña) throws IOException
    {
        this.serverSocket = new ServerSocket(PORT);
        this.contraseña = contraseña;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try {
                Socket socket = serverSocket.accept();
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(contraseña);
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
