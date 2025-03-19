package gui.table;

import gui.button.ActionButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableRemoveCellRender extends DefaultTableCellRenderer {
    
    private int hoveredRow = -1;
    private DefaultTableModel tableModel;

    public TableRemoveCellRender(JTable table) {
        this.tableModel = (DefaultTableModel) table.getModel(); // Lấy mô hình bảng
        addMouseMotionListenerToTable(table);
        addMouseListenerToTable(table); // Thêm MouseListener
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean btn, int row, int column) {
        Component com = super.getTableCellRendererComponent(table, value, isSelected, btn, row, column);
        JPanel pnlRemove = new JPanel();
        Icon icon = new ImageIcon("imgs/icon/remove.png"); // Đảm bảo đường dẫn biểu tượng chính xác
        ActionButton btnRemove = new ActionButton(icon);
        pnlRemove.add(btnRemove);
        pnlRemove.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        
        // Kiểm tra trạng thái chọn và hover để thiết lập màu nền
        if (isSelected) {
            pnlRemove.setBackground(new Color(200, 200, 200)); // Màu nền khi chọn
        } else if (row == hoveredRow) {
            pnlRemove.setBackground(new Color(240, 240, 240)); // Màu nền khi hover
        } else {
            pnlRemove.setBackground(com.getBackground()); // Màu nền mặc định
        }
        return pnlRemove;
    }

    // Thêm MouseMotionListener cho toàn bộ bảng
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
    
    private void addMouseListenerToTable(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                if (row >= 0 && column == table.getColumnCount() - 1) { // Giả sử cột cuối là nơi có nút xóa
                    tableModel.removeRow(row);
                }
            }
        });
    }
}
