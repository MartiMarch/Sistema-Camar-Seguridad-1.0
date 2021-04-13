package ComponentesBasicos;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class JBRadioButton extends JRadioButton{
    public JBRadioButton()
    {
        this.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(127,127,127));
        this.setBorder(new EmptyBorder(1,1,1,1));
        this.setFocusable(false);
    }
    
    public JBRadioButton(String texto)
    {
        super(texto);
        this.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(127,127,127));
        this.setBorder(new EmptyBorder(1,1,1,1));
        this.setFocusable(false);
    }
}
