package gui.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class TablePriceCellRender extends DefaultTableCellRenderer{

    private int hoveredRow = -1;

    public TablePriceCellRender(JTable table) {
        addMouseMotionListenerToTable(table);
    }   
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean btn, int row, int column) {
        Component com = super.getTableCellRendererComponent(table, value, isSelected, btn, row, column); 
        JPanel pnlPrice = new JPanel();
        pnlPrice.setLayout(new BorderLayout());
        JLabel priceDis = new JLabel("100000");
        priceDis.setPreferredSize(new Dimension(10, 50));
        priceDis.setHorizontalAlignment(RIGHT);
        JLabel price = new JLabel("<html><s>100000</s></html>");
        price.setHorizontalAlignment(RIGHT);
        pnlPrice.add(priceDis, BorderLayout.NORTH);
        pnlPrice.add(price);
        pnlPrice.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        if (isSelected) {
            pnlPrice.setBackground(new Color(200, 200, 200)); // Màu nền khi chọn
        } else if (row == hoveredRow) {
            pnlPrice.setBackground(new Color(240, 240, 240)); // Màu nền khi hover
        } else {
            pnlPrice.setBackground(com.getBackground()); // Màu nền mặc định
        }
        
        
        return pnlPrice;
    }
    
    private void addMouseMotionListenerToTable(JTable table) {
        table.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row; // Cập nhật hàng đang hover
                    table.repaint(); // Vẽ lại bảng khi hover thay đổi
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                hoveredRow = -1; // Reset khi chuột ra ngoài bảng
                table.repaint(); // Vẽ lại bảng
            }   
        });
    }
}
