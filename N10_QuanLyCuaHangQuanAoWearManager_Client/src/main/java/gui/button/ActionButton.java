package gui.button;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ActionButton extends JButton {
   
    private Icon icon;

    public ActionButton(Icon icon) {
        this.icon = icon; // Ghi nhớ biểu tượng
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(4, 4, 4, 4));
        setOpaque(false);
        setBorderPainted(false); 
        setFocusPainted(false); 
        setPreferredSize(new Dimension(40, 100));
        setIcon(icon);
    }
}
