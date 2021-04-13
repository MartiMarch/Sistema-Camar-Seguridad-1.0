package ComponentesBasicos;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JSeparator;

public class JBSeparator extends JSeparator{
    public JBSeparator()
    {
        super();
        this.setBackground(new Color(83,83,83));
        this.setForeground(new Color(83,83,83));
        this.setPreferredSize(new Dimension(800,2));
        this.setBorder(null);
        this.setAlignmentX(LEFT_ALIGNMENT);
    }
}