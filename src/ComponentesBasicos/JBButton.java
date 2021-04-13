package ComponentesBasicos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class JBButton extends JButton{
    public JBButton()
    {
        this.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(83,83,83));
        this.setFocusable(false);
        this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(127,127,127), new Color(127,127,127), new Color(127,127,127), new Color(127,127,127)));
        this.setPreferredSize(new Dimension(90, 29));
    }
    
    public JBButton(String texto)
    {
        super(texto);
        this.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(83,83,83));
        this.setFocusable(false);
        this.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(127,127,127), new Color(127,127,127), new Color(127,127,127), new Color(127,127,127)));
        this.setPreferredSize(new Dimension(90, 29));
    }
}
