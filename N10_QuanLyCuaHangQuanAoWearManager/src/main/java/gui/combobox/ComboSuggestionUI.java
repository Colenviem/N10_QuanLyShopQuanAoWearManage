package gui.combobox;

import gui.scroll.ScrollPane;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ComboSuggestionUI extends BasicComboBoxUI {

    @Override
    public void installUI(JComponent jc) {
        super.installUI(jc);
        Border border = new Border();
        JTextField txt = (JTextField) comboBox.getEditor().getEditorComponent();
        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                border.setColor(new Color(128, 189, 255));
            }

            @Override
            public void focusLost(FocusEvent fe) {
                border.setColor(new Color(206, 212, 218));
            }
        });
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                arrowButton.setBackground(new Color(180, 180, 180));
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
                arrowButton.setBackground(new Color(150, 150, 150));
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent pme) {
                arrowButton.setBackground(new Color(150, 150, 150));
            }
        });
        AutoCompleteDecorator.decorate(comboBox);
        txt.setSelectionColor(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
        txt.setBorder(new EmptyBorder(0, 6, 0, 6));
//        txt.setFont(new Font("Regular", Font.BOLD, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(border);
    }

    @Override
    protected JButton createArrowButton() {
        return new ArrowButton();
    }

    @Override
    protected ComboPopup createPopup() {
        return new ComboSuggestionPopup(comboBox);
    }

    @Override
    protected ListCellRenderer createRenderer() {
        return new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean bln, boolean bln1) {
                String text = e == null ? "" : e.toString();
                JLabel label = new JLabel(text);
                label.setFont(comboBox.getFont());        
                if (i >= 0) {
                    label.setBorder(new EmptyBorder(5, 8, 5, 8));
                } else {
                    label.setBorder(null);
                }
                label.setPreferredSize(new Dimension(18, 40));
//                label.setFont(new Font("Regular", Font.BOLD, 14));
                if (bln) { // Nếu mục được chọn
                    label.setOpaque(true); // Đặt JLabel thành không trong suốt
                    // Xóa hoặc bỏ màu nền cho JLabel
                    label.setBackground(new Color(240, 240, 240));
                    label.setForeground(new Color(17, 155, 215)); // Đặt màu chữ cho JLabel
                } else {
                    label.setOpaque(false); // Đặt lại thành trong suốt cho các mục không được chọn
                }
                return label;
            }
        };
    }

    @Override
    public void paintCurrentValueBackground(Graphics grphcs, Rectangle rctngl, boolean bln) {
    }

    private class ComboSuggestionPopup extends BasicComboPopup {

        public ComboSuggestionPopup(JComboBox combo) {
            super(combo);
            setBorder(new Border(1));
        }

        @Override
        protected JScrollPane createScroller() {
            // Sử dụng ScrollPane thay vì JScrollPane mặc định
            ScrollPane scroll = new ScrollPane();
            list.setBackground(Color.WHITE);

            // Điều chỉnh thanh cuộn dọc và ngang nếu cần
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            // Thiết lập danh sách được cuộn vào trong ScrollPane
            scroll.setViewportView(list);

            return scroll;
        }


    }

    //Mũi tên
    private class ArrowButton extends JButton {

        public ArrowButton() {
            setContentAreaFilled(false);
            setBorder(new EmptyBorder(0, 6, 0, 6));
            setBackground(new Color(150, 150, 150));
        }

        @Override
        public void paint(Graphics grphcs) {
            super.paint(grphcs);
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            int sizeX = 12;
            int sizeY = 8;
            int x = (width - sizeX) / 2;
            int y = (height - sizeY) / 2;
            int px[] = {x, x + sizeX, x + sizeX / 2};
            int py[] = {y, y, y + sizeY};
            g2.setColor(getBackground());
            g2.fillPolygon(px, py, px.length);
            g2.dispose();
        }
    }

    private class Border extends EmptyBorder {
        public Color getFocusColor() {
            return focusColor;
        }

        public void setFocusColor(Color focusColor) {
            this.focusColor = focusColor;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        private Color focusColor = new Color(128, 189, 255);
        private Color color = new Color(206, 212, 218); // Màu viền mặc định

        public Border(int border) {
            super(border, border, border, border);
        }

        public Border() {
            this(5);
        }

        @Override
        public void paintBorder(Component cmpnt, Graphics grphcs, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Thiết lập màu viền dựa trên trạng thái focus
            if (cmpnt.isFocusOwner()) {
                g2.setColor(focusColor); // Màu viền khi có focus
            } else {
                g2.setColor(color); // Màu viền khi mất focus
            }

            g2.setStroke(new BasicStroke(2)); // Đặt độ dày viền là 2px
            g2.drawRoundRect(x + 1, y + 1, width - 2, height - 2, 4, 4);
            g2.dispose();

        }
    }

}
