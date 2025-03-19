package gui.table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatter;

public class TableQtyCellRender extends DefaultCellEditor{
    
    private JSpinner input;
    private JTable table;
    private int row;

    public TableQtyCellRender() {
        super(new JCheckBox());

        // Khởi tạo JSpinner với mô hình số
        input = new JSpinner(); // Giá trị tối thiểu là 1, tối đa là 100, bước tăng là 1
        SpinnerNumberModel numberModel = (SpinnerNumberModel) input.getModel();
        numberModel.setMinimum(1);
        // Tùy chỉnh giao diện UI của JSpinner để sửa các nút tăng/giảm
        input.setUI(new CustomSpinnerUI());

        // Cấu hình editor cho JSpinner
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        input.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Component com = super.getTableCellEditorComponent(table, value, isSelected, row, column);
        this.table = table;
        this.row = row;

        // Lấy giá trị từ bảng và đặt vào JSpinner
        int qty = Integer.parseInt(value.toString());
        input.setValue(qty); // Đặt giá trị hiện tại cho JSpinner
        input.setEnabled(false); // Tạm thời vô hiệu hóa JSpinner
        input.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        enable(); // Kích hoạt lại JSpinner sau 100ms

        // Tạo JPanel để chứa JSpinner
        JPanel pnlInput = new JPanel(new BorderLayout());
        pnlInput.setBorder(new EmptyBorder(30, 20, 30, 20)); // Căn chỉnh lề cho JPanel
        pnlInput.add(input, BorderLayout.CENTER); // Thêm JSpinner vào JPanel
        pnlInput.setBackground(com.getBackground());
        return pnlInput; // Trả về JPanel chứa JSpinner để hiển thị trong bảng
    }

    // Bật lại JSpinner sau 100ms
    private void enable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    input.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public Object getCellEditorValue() {
        return input.getValue();
    }   
    
    // Tùy chỉnh UI của JSpinner
    private static class CustomSpinnerUI extends BasicSpinnerUI {
        @Override
        protected Component createNextButton() {
            // Tạo nút tăng tùy chỉnh
            JButton increaseButton = new BasicArrowButton(SwingConstants.NORTH) {
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(15, 15); // Kích thước của nút tăng
                }
            };
            increaseButton.setOpaque(false); // Bỏ nền của nút
            increaseButton.setContentAreaFilled(false); // Bỏ phần vùng chứa nền
            increaseButton.setBorder(BorderFactory.createEmptyBorder()); // Bỏ viền của nút
            installNextButtonListeners(increaseButton);
            return increaseButton;  
        }

        @Override
        protected Component createPreviousButton() {
            // Tạo nút giảm tùy chỉnh
            JButton decreaseButton = new BasicArrowButton(SwingConstants.SOUTH) {
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(15, 15); // Kích thước của nút giảm
                }
            };
            decreaseButton.setOpaque(false); // Bỏ nền của nút
            decreaseButton.setContentAreaFilled(false); // Bỏ phần vùng chứa nền
            decreaseButton.setBorder(BorderFactory.createEmptyBorder()); // Bỏ viền của nút
            installPreviousButtonListeners(decreaseButton);
            return decreaseButton;
        }

        @Override
        protected JComponent createEditor() {
            // Tùy chỉnh editor của JSpinner
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) super.createEditor();
            editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa văn bản
            editor.getTextField().setBorder(BorderFactory.createEmptyBorder()); // Bỏ viền của ô nhập
            return editor;
        }
    }
}
