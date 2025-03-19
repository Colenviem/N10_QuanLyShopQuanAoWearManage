package gui.textfield;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TextFieldCustom extends JTextField {

    private String placeholder;
    private boolean isFocused = false; // Biến để lưu trạng thái focus

    public TextFieldCustom(String text) {
        setOpaque(false);
        setPlaceholder(text); // Lưu giá trị placeholder
        setText(text);
        setBorder(new EmptyBorder(0, 10, 0, 10));
        setForeground(Color.GRAY);

        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK); // Đặt lại màu chữ khi nhập
                }
                isFocused = true; // Cập nhật trạng thái focus
                repaint(); // Vẽ lại thành phần để cập nhật viền
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (getText().isEmpty()) {
                    setForeground(Color.GRAY);
                    setText(placeholder); // Hiển thị lại đúng placeholder
                }
                isFocused = false; // Cập nhật trạng thái mất focus
                repaint(); // Vẽ lại thành phần để cập nhật viền
            }
        });
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Thay đổi màu viền dựa vào trạng thái focus
        if (isFocused) {
            g2.setColor(new Color(0, 120, 215)); // Màu xanh khi có focus
        } else {
            g2.setColor(new Color(206, 212, 218)); // Màu xám khi không có focus
        }

        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 3, 10, 10); // Vẽ viền bo góc
        super.paintComponent(g);
    }
}
