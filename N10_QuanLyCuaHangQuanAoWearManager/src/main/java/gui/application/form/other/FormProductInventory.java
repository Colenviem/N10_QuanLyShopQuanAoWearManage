package gui.application.form.other;

import bus.ProductBUS;
import dao.ProductDAO;
import dto.Product;
import gui.event.EventItem;
import com.raven.model.ModelItem;
import gui.component.Item;
import gui.swing.ScrollBar;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

public class FormProductInventory extends javax.swing.JPanel {

    private EventItem event;
    private Animator animator;
    private ModelItem itemSelected;
    private Point animatePoint;
    private ProductBUS productBUS;

    public FormProductInventory(EventItem event) {
        this.event = event;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Product Inventory");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Căn giữa màn hình

            FormProductInventory formInbox = null;
            try {
                formInbox = new FormProductInventory();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            frame.add(formInbox);

            frame.setVisible(true);
        });
    }

    public FormProductInventory() throws RemoteException {
        initComponents();
        panelItem.remove(item1);
        panelItem.revalidate();
        panelItem.repaint();
        productBUS = new ProductBUS();

        this.event = new EventItem() {
            @Override
            public void itemClick(Component com, ModelItem item) {
                if (itemSelected != null) {
                    mainPanel.setImageOld(itemSelected.getImage());
                }
                if (itemSelected != item) {
                    if (!animator.isRunning()) {
                        itemSelected = item;
                        animatePoint = getLocationOf(com);
                        mainPanel.setImage(item.getImage());
                        mainPanel.setImageLocation(animatePoint);
                        mainPanel.setImageSize(new Dimension(180, 120));
                        mainPanel.repaint();
                        setSelected(com);
                        showItem(item);
                        animator.start();
                    }
                }
            }
        };

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = txtSearch.getText().trim(); // Lấy từ khóa từ text field và loại bỏ khoảng trắng thừa
                if (!keyword.isEmpty()) {
                    try {
                        TimKiemSanPham(keyword);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Xử lý lỗi nếu có
                    }
                } else {
                    // Nếu không có từ khóa, bạn có thể hiển thị lại tất cả sản phẩm hoặc thông báo cho người dùng
                    try {
                        DocDuLieu(); // Hiển thị lại tất cả sản phẩm (nếu muốn)
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String keyword = txtSearch.getText().trim();
                    if (!keyword.isEmpty()) {
                        try {
                            TimKiemSanPham(keyword);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            DocDuLieu();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
//        btnAddToCart.addActionListener((ActionListener) this);

        scroll.setVerticalScrollBar(new ScrollBar());
        //  Animator start form animatePoint to TagetPoint
        animator = PropertySetter.createAnimator(500, mainPanel, "imageLocation", animatePoint, mainPanel.getTargetLocation());
        animator.addTarget(new PropertySetter(mainPanel, "imageSize", new Dimension(180, 120), mainPanel.getTargetSize()));
        animator.addTarget(new TimingTargetAdapter() {
            @Override
            public void end() {
                mainPanel.setImageOld(null);
            }
        });

        try {
            DocDuLieu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void DocDuLieu() throws Exception{
        List<Product> products = productBUS.getAllProducts();
        for (Product product : products) {
            URL imageURL = getClass().getClassLoader().getResource(product.getImageUrl());
            ImageIcon imageIcon = null;
            if (imageURL != null) {
                imageIcon = new ImageIcon(imageURL);
            } else {
                System.out.println("Hình ảnh không tìm thấy!");
            }

            ModelItem modelItem = new ModelItem(
                    product.getId(),
                    product.getProductName(),
                    product.getDescription(),
                    (double) product.getPrice(),
                    product.getColor(),
                    imageIcon
            );
            addItem(modelItem); // Giả sử bạn có phương thức này để thêm item vào giao diện
        }
    }

    private void TimKiemSanPham(String keyword) throws Exception {
        panelItem.removeAll(); // Xóa các item hiện có trên panel
        List<Product> searchResults = productBUS.getProductByName(keyword);
        if (searchResults != null){
            for (Product product : searchResults) {
                URL imageURL = getClass().getClassLoader().getResource(product.getImageUrl());
                ImageIcon imageIcon = null;
                if (imageURL != null) {
                    imageIcon = new ImageIcon(imageURL);
                } else {
                    System.out.println("Hình ảnh không tìm thấy!");
                }
                ModelItem modelItem = new ModelItem(
                        product.getId(),
                        product.getProductName(),
                        product.getDescription(),
                        (double) product.getPrice(),
                        product.getColor(),
                        imageIcon
                );
                addItem(modelItem);
            }
            panelItem.revalidate();
            panelItem.repaint();
        }else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
            txtSearch.requestFocus();
            return;
        }
    }
    

    public void addItem(ModelItem data) {
        Item item = new Item();
        item.setData(data);
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (event != null) {
                        event.itemClick(item, data);
                    } else {
                        System.err.println("Lỗi: biến event chưa được khởi tạo!");
                    }
                }
            }
        });

        panelItem.add(item);
        panelItem.repaint();
        panelItem.revalidate();
    }

    public void setSelected(Component item) {
        for (Component com : panelItem.getComponents()) {
            Item i = (Item) com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
        }
        ((Item) item).setSelected(true);
    }

    public void showItem(ModelItem data) {
        lbName.setText(data.getItemName());
        txtDescription.setText(data.getDescription());
        lbBrand.setText(data.getBrandName());
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        lbPrice.setText(df.format(data.getPrice()));
    }

    public Point getPanelItemLocation() {
        Point p = scroll.getLocation();
        return new Point(p.x, p.y - scroll.getViewport().getViewPosition().y);
    }

    private Point getLocationOf(Component com) {
        Point p = this.getPanelItemLocation();
        int x = p.x;
        int y = p.y;
        int itemX = com.getX();
        int itemY = com.getY();
        int left = 10;
        int top = 35;
        return new Point(x + itemX + left, y + itemY + top);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new gui.application.form.MainPanel();
        header = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbBrand = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextPane();
        jSeparator1 = new javax.swing.JSeparator();
        btnAddToCart = new gui.login.Button();
        scroll = new javax.swing.JScrollPane();
        panelItem = new gui.swing.PanelItem();
        item1 = new gui.component.Item();
        txtSearch = new gui.swing.MyTextField();
        btnSearch = new gui.login.Button();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        header.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbBrand.setForeground(new java.awt.Color(47, 46, 46));
        lbBrand.setText("Brand");

        lbPrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(71, 71, 71));
        lbPrice.setText("$0.00");

        lbName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(71, 71, 71));
        lbName.setText("Item Name");

        txtDescription.setBorder(null);
        txtDescription.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDescription.setForeground(new java.awt.Color(153, 153, 153));
        txtDescription.setFocusable(false);
        jScrollPane2.setViewportView(txtDescription);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbBrand)
                            .addComponent(lbPrice))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        btnAddToCart.setBackground(new java.awt.Color(255, 0, 51));
        btnAddToCart.setForeground(new java.awt.Color(255, 255, 255));
        btnAddToCart.setText("Thêm vào giỏ hàng");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelItem.add(item1);

        scroll.setViewportView(panelItem);

        txtSearch.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/search.png"))); // NOI18N
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(255, 0, 102));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scroll, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        if (itemSelected != null) {
            // Thực hiện logic thêm sản phẩm vào giỏ hàng ở đây (nếu bạn có)

            JOptionPane.showMessageDialog(this, "Đã thêm '" + itemSelected.getItemName() + "' vào giỏ hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trước khi thêm vào giỏ hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked

    }//GEN-LAST:event_txtSearchMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
 
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

    }//GEN-LAST:event_btnSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.login.Button btnAddToCart;
    private gui.login.Button btnSearch;
    private javax.swing.JPanel header;
    private gui.component.Item item1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbBrand;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPrice;
    private gui.application.form.MainPanel mainPanel;
    private gui.swing.PanelItem panelItem;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextPane txtDescription;
    private gui.swing.MyTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
