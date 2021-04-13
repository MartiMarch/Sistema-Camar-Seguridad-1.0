package ComponentesBasicos;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JBTextField extends JTextField{
    public JBTextField()
    {
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.setBackground(new Color(127,127,127));
        this.setBorder(new EmptyBorder(1,1,1,1));
        this.setEditable(false);
    }
    
    public JBTextField(String texto)
    {
        super(texto);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.setBackground(new Color(127,127,127));
        this.setBorder(new EmptyBorder(1,1,1,1));
        this.setEditable(false);
    }
}
