package gui.scroll;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {
    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(6, 8));
        setForeground(new Color(0, 0, 0, 20));
        setBackground(new Color(255, 255, 255));
        setOpaque(false);
    }   
}
