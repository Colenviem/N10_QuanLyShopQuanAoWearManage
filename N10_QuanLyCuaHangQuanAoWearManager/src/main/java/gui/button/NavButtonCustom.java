package gui.button;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class NavButtonCustom extends JButton {
    private Color hoverColor = new Color(240, 240, 240); // Màu nền khi hover
    private Color defaultColor = new Color(255, 255, 255); // Màu nền mặc định
    private boolean isHovered = false; // Trạng thái hover

    public NavButtonCustom(String text, String path) {
        setOpaque(false);
        setBorderPainted(false); 
        setContentAreaFilled(false); 
        setFocusPainted(false); 
        setText(text);
        setIcon(new ImageIcon(getClass().getResource(path)));
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER); 
        setForeground(new Color(20, 125, 71));

        // Thêm sự kiện để theo dõi trạng thái hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true; // Đặt trạng thái hover là true
                repaint(); // Vẽ lại nút
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false; // Đặt trạng thái hover là false
                repaint(); // Vẽ lại nút
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Gọi trước để đảm bảo thành phần mặc định được vẽ
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Vẽ màu nền theo trạng thái hover
        if (isHovered) {
            g2.setColor(hoverColor); // Nếu đang hover, sử dụng màu hover
        } else {
            g2.setColor(defaultColor); // Nếu không hover, sử dụng màu mặc định
        }
        g2.setStroke(new BasicStroke(2));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18); // Vẽ nền tròn

        super.paintComponent(g); // Gọi super để vẽ văn bản và hình ảnh
    }
}
