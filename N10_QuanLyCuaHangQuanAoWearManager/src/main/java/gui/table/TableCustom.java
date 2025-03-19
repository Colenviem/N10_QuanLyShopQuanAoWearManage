package gui.table;

import gui.scroll.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCustom extends JTable{
    
    private int hoveredRow = -1; 
    
    public TableCustom() {
        getTableHeader().setDefaultRenderer(new TableHeaderCustom());
        getTableHeader().setPreferredSize(new Dimension(0, 40));
        setDefaultRenderer(Object.class, new TableCellCustom());
        setRowHeight(40); 
        setBorder(BorderFactory.createEmptyBorder());
        setShowGrid(false); // Không hiển thị grid
        setIntercellSpacing(new Dimension(0, 0)); // Loại bỏ khoảng cách giữa các ô
        setFont(new Font("Regular", Font.BOLD, 14));
        setOpaque(false);
         // Thêm MouseMotionListener để xử lý hover
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row; // Cập nhật hàng đang hover
                    repaint(); // Vẽ lại bảng khi hover thay đổi
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredRow = -1; // Reset khi chuột ra ngoài bảng
                repaint(); // Vẽ lại bảng
            }
        });
    }
    
    public void ScrollPaneTable(JScrollPane scrollPane){
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
        scrollPane.setHorizontalScrollBar(new ScrollBarCustom());
        JPanel pnl = new JPanel();
        pnl.setBackground(new Color(255, 255, 255));
        scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, pnl);
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(255, 255, 255));
    }
 
    private class TableHeaderCustom extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean btn, int row, int column) {
           Component com = super.getTableCellRendererComponent(table, value, isSelected, btn, row, column); 
            setShowGrid(false); 
            setIntercellSpacing(new Dimension(0, 0));   
            if (com instanceof JLabel) {
                ((JLabel) com).setHorizontalAlignment(JLabel.CENTER);
            }
           com.setFont(new Font("Regular", Font.BOLD, 14));
           com.setForeground(new Color(148, 148, 148));
           setBorder(new EmptyBorder(0, 10, 0, 10));
            // Thêm viền dưới (bottom border)
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 218)));
//           setOpaque(false);
           return com;
        }
        
//        @Override
//        protected void paintComponent(Graphics g) {
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2.setColor(new Color(255, 255, 255));
//            g2.fillRect(0, 0, getWidth(), getHeight());
//
//            // Độ dày của viền
//            int thickness = 1;
//
//            // Vẽ viền phía dưới (bottom border)
//            g2.setColor(new Color(200, 200, 200));
//            g2.fillRect(0, getHeight() - thickness, getWidth(), thickness); // Tạo viền dưới dày hơn
//
//            super.paintComponent(g);
//        }
    }
    
    private class TableCellCustom extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean btn, int row, int column) {
            Component com = super.getTableCellRendererComponent(table, value, isSelected, btn, row, column);
            if (com instanceof JLabel) {
                ((JLabel) com).setHorizontalAlignment(JLabel.CENTER);
            }
            // Đổi màu nền của ô khi hover
            if (isCellSelected(row, column)) {
                com.setBackground(new Color(200, 200, 200)); // Màu nền khi chọn
            } else if (row == hoveredRow) {
                com.setBackground(new Color(240, 240, 240)); // Màu nền khi hover
            } else {
                com.setBackground(new Color(255, 255, 255)); // Màu nền mặc định
            }
            com.setForeground(new Color(21, 25, 44));
            com.setFont(new Font("Regular", Font.PLAIN, 14)); 
            setBorder(new EmptyBorder(0, 10, 0, 10));
            // Thêm viền dưới (bottom border)
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 218)));
            return com;
        } 
    }
}
