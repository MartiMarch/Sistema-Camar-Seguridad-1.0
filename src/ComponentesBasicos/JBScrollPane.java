package ComponentesBasicos;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class JBScrollPane extends JScrollPane{
    public JBScrollPane(JPanel panel)
    {
        super(panel);
        this.getViewport().setBackground(new Color(127,127,127));
        this.setBorder(new EmptyBorder(0,0,0,0));
        UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(83,83,83)));
        this.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
    }
}
