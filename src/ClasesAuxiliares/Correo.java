package ClasesAuxiliares;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo{
    
    public Correo(){}
    
    public void enviarCorreo(String correoOrigen, String contraseña, String correoDestino, String cabecera, String mensaje) throws AddressException, MessagingException
    {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port","587");
        props.setProperty("mail.smtp.user", correoOrigen);
        props.setProperty("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props);
        
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(correoOrigen));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
        message.setSubject(cabecera);
        message.setText(mensaje);
        
        Transport t = session.getTransport("smtp");
        t.connect(correoOrigen, contraseña);
        t.sendMessage(message,message.getAllRecipients());
        t.close();
    }
}
