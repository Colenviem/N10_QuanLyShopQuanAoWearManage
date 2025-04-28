package gui.textfield;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TextPay extends JTextField {

    private boolean isFocused = false; // Biến để theo dõi trạng thái focus

    public TextPay() {
        setOpaque(false);
        setBorder(new EmptyBorder(0, 10, 0, 10));
        setFont(new Font("Regular", Font.PLAIN, 14));

        // Thêm sự kiện focus để thay đổi trạng thái
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                isFocused = true; // Cập nhật trạng thái khi có focus
                repaint(); // Vẽ lại khi nhận focus
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                isFocused = false; // Cập nhật trạng thái khi mất focus
                repaint(); // Vẽ lại khi mất focus
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền tròn
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 4, 4);

        // Vẽ viền với màu thay đổi theo trạng thái focus
        if (isFocused) {
            g2.setColor(new Color(0, 120, 215)); // Màu xanh khi có focus
        } else {
            g2.setColor(new Color(206, 212, 218)); // Màu xám khi không có focus
        }

        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 3, 4, 4); // Vẽ viền tròn

        super.paintComponent(g); // Vẽ text bên trong sau cùng để đảm bảo viền và nền không che mất
    }
}
