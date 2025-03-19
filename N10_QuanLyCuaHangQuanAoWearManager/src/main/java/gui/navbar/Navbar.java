package gui.navbar;

import gui.button.NavButtonCustom;
import gui.button.ButtonCustom;
import gui.combobox.ComboBoxSuggestion;
import gui.textfield.TextFieldCustom;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Navbar extends JPanel {

    private NavButtonCustom btnEdit;
    private NavButtonCustom btnImport;
    private NavButtonCustom btnExport;
    private DefaultComboBoxModel<String> defaultComboBox;
    private ComboBoxSuggestion<String> comboBox;
    private TextFieldCustom txtSearch;
    private ButtonCustom btnSearch;
    private JPanel pnlBtnLeft;
    private NavButtonCustom btnAdd;

    public Navbar() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    private void init() {
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel pnlBtn = new JPanel(new BorderLayout());
        pnlBtn.setOpaque(false);

        JPanel pnlBtnRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBtnRight.setOpaque(false);

        pnlBtnLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBtnLeft.setOpaque(false);
        btnAdd = new NavButtonCustom("Thêm", "images/png/add.png");
        btnEdit = new NavButtonCustom("SỬA", "images/png/edit.png");
        btnImport = new NavButtonCustom("THÊM EXCEL", "images/png/excel.png");
        btnExport = new NavButtonCustom("XUẤT EXCEL", "images/png/excel.png");
        defaultComboBox = new DefaultComboBoxModel<>();
        comboBox = new ComboBoxSuggestion<>(defaultComboBox);
        comboBox.setPreferredSize(new Dimension(200, 40));
        txtSearch = new TextFieldCustom("Nhập nội dung tìm kiếm...");
        txtSearch.setPreferredSize(new Dimension(200, 40));
        btnSearch = new ButtonCustom("Tìm kiếm", "images/png/search.png");

        pnlBtnLeft.add(btnAdd);
        pnlBtnLeft.add(btnEdit);
        pnlBtnLeft.add(btnImport);
        pnlBtnLeft.add(btnExport);

        pnlBtnRight.add(comboBox);
        pnlBtnRight.add(txtSearch);
        pnlBtnRight.add(btnSearch);

        pnlBtn.add(pnlBtnLeft, BorderLayout.WEST);
        pnlBtn.add(pnlBtnRight, BorderLayout.EAST);
        pnlBtnRight.setBorder(new EmptyBorder(20, 0, 0, 20));

        add(pnlBtn, BorderLayout.CENTER);
    }

    public void setDefaultComboBox(DefaultComboBoxModel<String> defaultComboBox) {
        this.defaultComboBox = defaultComboBox;
        comboBox.setModel(defaultComboBox);
    }

    public ButtonCustom getBtnSearch() {
        return btnSearch;
    }

    public TextFieldCustom getTxtSearch() {
        return txtSearch;
    }

    public ComboBoxSuggestion<String> getComboBox() {
        return comboBox;
    }

    public NavButtonCustom getBtnImport() {
        return btnImport;
    }

    public NavButtonCustom getBtnExport() {
        return btnExport;
    }

    public NavButtonCustom getBtnEdit() {
        return btnEdit;
    }

    public NavButtonCustom getBtnAdd() {
        return btnAdd;
    }

    public void removeAdd() {
        pnlBtnLeft.remove(btnAdd);
    }
}
