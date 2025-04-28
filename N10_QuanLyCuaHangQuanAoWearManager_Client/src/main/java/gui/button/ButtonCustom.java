package gui.button;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonCustom extends JButton {

    private Color hoverColor = new Color(240, 240, 240); // Màu nền khi hover
    private Color defaultColor = new Color(255, 255, 255); // Màu nền mặc định
    private boolean isHovered = false; // Trạng thái hover
    private boolean showBorder = true; // Kiểm soát hiển thị viền
    private Font font;

    public ButtonCustom(String text, String path) {
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setText(text);
        setFont(font);
        setIcon(new ImageIcon(path));
        setVerticalTextPosition(SwingConstants.CENTER); // Văn bản và hình ảnh cùng hàng
        setHorizontalTextPosition(SwingConstants.RIGHT); // Văn bản ở bên phải hình ảnh

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

    // Phương thức setter để thay đổi màu hover
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
        repaint(); // Vẽ lại khi thay đổi màu
    }

    // Phương thức setter để thay đổi màu nền mặc định
    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
        repaint(); // Vẽ lại khi thay đổi màu
    }

    // Phương thức setter để kiểm soát hiển thị viền
    public void setShowBorder(boolean showBorder) {
        this.showBorder = showBorder;
        repaint(); // Vẽ lại khi thay đổi viền
    }

    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void paintComponent(Graphics g) {
        // Gọi trước để đảm bảo thành phần mặc định được vẽ
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ màu nền theo trạng thái hover
        if (isHovered) {
            g2.setColor(hoverColor); // Nếu đang hover, sử dụng màu hover
        } else {
            g2.setColor(defaultColor); // Nếu không hover, sử dụng màu mặc định
        }

        // Kiểm tra xem có cần vẽ viền hay không
        if (showBorder) {
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 4, 4); // Vẽ nền tròn
            g2.setStroke(new BasicStroke(2));
            g2.setColor(new Color(206, 212, 218));
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 4, 4);
        }else{
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 4, 4);
        }
        
        super.paintComponent(g); // Gọi super để vẽ văn bản và hình ảnh

        
    }
}
