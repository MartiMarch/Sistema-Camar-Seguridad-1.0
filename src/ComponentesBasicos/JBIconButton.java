package ComponentesBasicos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class JBIconButton extends JButton{
    public JBIconButton()
    {
        this.setBorder(new EmptyBorder(1, 1, 1, 1));
        this.setFocusable(false);
        this.setContentAreaFilled(false);
    }
    
    public JBIconButton(ImageIcon icon)
    {
        super(icon);
        this.setBorder(new EmptyBorder(1, 1, 1, 1));
        this.setFocusable(false);
        this.setContentAreaFilled(false);
    }
}
