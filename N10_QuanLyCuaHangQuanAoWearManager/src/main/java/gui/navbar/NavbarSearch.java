package gui.navbar;

import gui.button.ButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.textfield.TextFieldCustom;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NavbarSearch extends JPanel {
    private TextFieldCustom txtSearch;
    private ButtonCustom btnSearch;
    private DefaultComboBoxModel<String> defaultComboBox;
    private ComboBoxSuggestion<String> comboBox;
    private TextFieldCustom txtDateTo;
    private JButton btnDateTo;
    private TextFieldCustom txtDate;
    private JButton btnDate;
    
    public NavbarSearch() {
        initComponents();
        init();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    private void init() {
        txtSearch = new TextFieldCustom("Nhập nội dung tìm kiếm...");
        txtSearch.setPreferredSize(new Dimension(200, 40));
        btnSearch = new ButtonCustom("Tìm kiếm", "imgs\\img\\search.png");
        defaultComboBox = new DefaultComboBoxModel<>();
        comboBox = new ComboBoxSuggestion<>(defaultComboBox);
        comboBox.setPreferredSize(new Dimension(160, 40));
        JLabel lblDateTo = new JLabel("Thời gian: ");
        txtDateTo = new TextFieldCustom("Tháng năm");
        txtDateTo.setPreferredSize(new Dimension(160, 40));
        btnDateTo = new JButton();
        btnDateTo.setIcon(new ImageIcon("imgs\\icon\\date.png"));
        btnDateTo.setPreferredSize(new Dimension(40, 40));
        btnDateTo.setOpaque(false);
        btnDateTo.setBorderPainted(false); 
        btnDateTo.setContentAreaFilled(false); 
        btnDateTo.setFocusPainted(false); 
        btnDateTo.setOpaque(false);
        JLabel lblDate = new JLabel("Đến: ");
        txtDate = new TextFieldCustom("Tháng năm");
        txtDate.setPreferredSize(new Dimension(160, 40));
        btnDate = new JButton();
        btnDate.setIcon(new ImageIcon("imgs\\icon\\date.png"));
        btnDate.setPreferredSize(new Dimension(40, 40));
        btnDate.setOpaque(false);
        btnDate.setBorderPainted(false); 
        btnDate.setContentAreaFilled(false); 
        btnDate.setFocusPainted(false); 
        btnDate.setOpaque(false);
        add(comboBox);
        add(txtSearch);
        add(btnSearch);
        add(lblDateTo);
        add(txtDateTo);
        add(btnDateTo);
        add(lblDate);
        add(txtDate);
        add(btnDate);
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
    }

    public void setDefaultComboBox(DefaultComboBoxModel<String> defaultComboBox) {
        this.defaultComboBox = defaultComboBox;
        comboBox.setModel(defaultComboBox);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 

        // Thêm đổ bóng
        g2.setColor(new Color(0, 0, 0, 20)); 
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 18, 18);

        // Màu nền
        g2.setColor(new Color(255, 255, 255)); 
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);

        // Vẽ viền dày
        g2.setColor(new Color(206, 212, 218));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 2, 18, 18);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
