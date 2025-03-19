package gui.application.form.other;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FormLookup extends JPanel {

    public FormLookup() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        tabbedPane = new gui.tabbedpane.TabbedPaneCustom();
        pnlEmp = new javax.swing.JPanel();
        pnlNavEmp = new javax.swing.JPanel();
        navEmp = new gui.navbar.NavbarSearch();
        pnlTableCon = new javax.swing.JPanel();
        pnlTableEmp = new javax.swing.JPanel();
        lblNameEmp = new javax.swing.JLabel();
        scrollPaneEmp = new javax.swing.JScrollPane();
        tableEmp = new gui.table.TableCustom();
        pnlCus = new javax.swing.JPanel();
        pnlNavCus = new javax.swing.JPanel();
        navCus = new gui.navbar.NavbarSearch();
        pnlTableConCus = new javax.swing.JPanel();
        pnlTableCus = new javax.swing.JPanel();
        lblNameCus = new javax.swing.JLabel();
        scrollPaneCus = new javax.swing.JScrollPane();
        tableCus = new gui.table.TableCustom();
        pnlOrder = new javax.swing.JPanel();
        pnlNavOrder = new javax.swing.JPanel();
        navOrder = new gui.navbar.NavbarSearch();
        pnlTableConOrder = new javax.swing.JPanel();
        pnlTableOrder = new javax.swing.JPanel();
        lblNameOrder = new javax.swing.JLabel();
        scrollPaneOrder = new javax.swing.JScrollPane();
        tableOrder = new gui.table.TableCustom();
        pnlVender = new javax.swing.JPanel();
        pnlNavVender = new javax.swing.JPanel();
        navVender = new gui.navbar.NavbarSearch();
        pnlTableConVender = new javax.swing.JPanel();
        pnlTableVender = new javax.swing.JPanel();
        lblNameVender = new javax.swing.JLabel();
        scrollPaneVender = new javax.swing.JScrollPane();
        tableVender = new gui.table.TableCustom();
        pnlDis = new javax.swing.JPanel();
        pnlNavDis = new javax.swing.JPanel();
        navDis = new gui.navbar.NavbarSearch();
        pnlTableConDis = new javax.swing.JPanel();
        pnlTableDis = new javax.swing.JPanel();
        lblNameDis = new javax.swing.JLabel();
        scrollPaneDis = new javax.swing.JScrollPane();
        tableDis = new gui.table.TableCustom();
        pnlAcc = new javax.swing.JPanel();
        pnlNavAcc = new javax.swing.JPanel();
        navAcc = new gui.navbar.NavbarSearch();
        pnlTableConAcc = new javax.swing.JPanel();
        pnlTableAcc = new javax.swing.JPanel();
        lblNameAcc = new javax.swing.JLabel();
        scrollPaneAcc = new javax.swing.JScrollPane();
        tableAcc = new gui.table.TableCustom();
        pnlMe = new javax.swing.JPanel();
        pnlNavMe = new javax.swing.JPanel();
        navMe = new gui.navbar.NavbarSearch();
        pnlTableConMe = new javax.swing.JPanel();
        pnlTableMe = new javax.swing.JPanel();
        lblNameMe = new javax.swing.JLabel();
        scrollPaneMe = new javax.swing.JScrollPane();
        tableMe = new gui.table.TableCustom();
        pnlMeE = new javax.swing.JPanel();
        pnlNavMeE = new javax.swing.JPanel();
        navMeE = new gui.navbar.NavbarSearch();
        pnlTableConMeE = new javax.swing.JPanel();
        pnlTableMeE = new javax.swing.JPanel();
        lblNameMeE = new javax.swing.JLabel();
        scrollPaneMeE = new javax.swing.JScrollPane();
        tableMeE = new gui.table.TableCustom();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setLayout(new java.awt.BorderLayout());

        pnlEmp.setOpaque(false);
        pnlEmp.setLayout(new java.awt.BorderLayout());

        pnlNavEmp.setOpaque(false);
        pnlNavEmp.setLayout(new java.awt.BorderLayout());
        pnlNavEmp.add(navEmp, java.awt.BorderLayout.CENTER);

        pnlEmp.add(pnlNavEmp, java.awt.BorderLayout.PAGE_START);

        pnlTableCon.setOpaque(false);
        pnlTableCon.setLayout(new java.awt.BorderLayout());

        pnlTableEmp.setLayout(new java.awt.BorderLayout());

        lblNameEmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameEmp.setText("Danh sách nhân viên");
        pnlTableEmp.add(lblNameEmp, java.awt.BorderLayout.PAGE_START);

        tableEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"sadsa", "adsda", null, null},
                {"", null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneEmp.setViewportView(tableEmp);

        pnlTableEmp.add(scrollPaneEmp, java.awt.BorderLayout.CENTER);

        pnlTableCon.add(pnlTableEmp, java.awt.BorderLayout.CENTER);

        pnlEmp.add(pnlTableCon, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Nhân viên", pnlEmp);

        pnlCus.setOpaque(false);
        pnlCus.setLayout(new java.awt.BorderLayout());

        pnlNavCus.setOpaque(false);
        pnlNavCus.setLayout(new java.awt.BorderLayout());
        pnlNavCus.add(navCus, java.awt.BorderLayout.CENTER);

        pnlCus.add(pnlNavCus, java.awt.BorderLayout.PAGE_START);

        pnlTableConCus.setOpaque(false);
        pnlTableConCus.setLayout(new java.awt.BorderLayout());

        pnlTableCus.setLayout(new java.awt.BorderLayout());

        lblNameCus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameCus.setText("Danh sách khách hàng");
        pnlTableCus.add(lblNameCus, java.awt.BorderLayout.PAGE_START);

        tableCus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneCus.setViewportView(tableCus);

        pnlTableCus.add(scrollPaneCus, java.awt.BorderLayout.CENTER);

        pnlTableConCus.add(pnlTableCus, java.awt.BorderLayout.CENTER);

        pnlCus.add(pnlTableConCus, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Khách hàng", pnlCus);

        pnlOrder.setOpaque(false);
        pnlOrder.setLayout(new java.awt.BorderLayout());

        pnlNavOrder.setOpaque(false);
        pnlNavOrder.setLayout(new java.awt.BorderLayout());
        pnlNavOrder.add(navOrder, java.awt.BorderLayout.CENTER);

        pnlOrder.add(pnlNavOrder, java.awt.BorderLayout.PAGE_START);

        pnlTableConOrder.setOpaque(false);
        pnlTableConOrder.setLayout(new java.awt.BorderLayout());

        pnlTableOrder.setLayout(new java.awt.BorderLayout());

        lblNameOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameOrder.setText("Danh sách hóa đơn");
        pnlTableOrder.add(lblNameOrder, java.awt.BorderLayout.PAGE_START);

        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneOrder.setViewportView(tableOrder);

        pnlTableOrder.add(scrollPaneOrder, java.awt.BorderLayout.CENTER);

        pnlTableConOrder.add(pnlTableOrder, java.awt.BorderLayout.CENTER);

        pnlOrder.add(pnlTableConOrder, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Hóa đơn", pnlOrder);

        pnlVender.setOpaque(false);
        pnlVender.setLayout(new java.awt.BorderLayout());

        pnlNavVender.setOpaque(false);
        pnlNavVender.setLayout(new java.awt.BorderLayout());
        pnlNavVender.add(navVender, java.awt.BorderLayout.CENTER);

        pnlVender.add(pnlNavVender, java.awt.BorderLayout.PAGE_START);

        pnlTableConVender.setOpaque(false);
        pnlTableConVender.setLayout(new java.awt.BorderLayout());

        pnlTableVender.setLayout(new java.awt.BorderLayout());

        lblNameVender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameVender.setText("Danh sách nhà cung cấp");
        pnlTableVender.add(lblNameVender, java.awt.BorderLayout.PAGE_START);

        tableVender.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhà cung cấp", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        scrollPaneVender.setViewportView(tableVender);

        pnlTableVender.add(scrollPaneVender, java.awt.BorderLayout.CENTER);

        pnlTableConVender.add(pnlTableVender, java.awt.BorderLayout.CENTER);

        pnlVender.add(pnlTableConVender, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Nhà cung cấp", pnlVender);

        pnlDis.setOpaque(false);
        pnlDis.setLayout(new java.awt.BorderLayout());

        pnlNavDis.setOpaque(false);
        pnlNavDis.setLayout(new java.awt.BorderLayout());
        pnlNavDis.add(navDis, java.awt.BorderLayout.CENTER);

        pnlDis.add(pnlNavDis, java.awt.BorderLayout.PAGE_START);

        pnlTableConDis.setOpaque(false);
        pnlTableConDis.setLayout(new java.awt.BorderLayout());

        pnlTableDis.setLayout(new java.awt.BorderLayout());

        lblNameDis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameDis.setText("Danh sách khuyễn mãi");
        pnlTableDis.add(lblNameDis, java.awt.BorderLayout.PAGE_START);

        tableDis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneDis.setViewportView(tableDis);

        pnlTableDis.add(scrollPaneDis, java.awt.BorderLayout.CENTER);

        pnlTableConDis.add(pnlTableDis, java.awt.BorderLayout.CENTER);

        pnlDis.add(pnlTableConDis, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Khuyễn mãi", pnlDis);

        pnlAcc.setOpaque(false);
        pnlAcc.setLayout(new java.awt.BorderLayout());

        pnlNavAcc.setOpaque(false);
        pnlNavAcc.setLayout(new java.awt.BorderLayout());
        pnlNavAcc.add(navAcc, java.awt.BorderLayout.CENTER);

        pnlAcc.add(pnlNavAcc, java.awt.BorderLayout.PAGE_START);

        pnlTableConAcc.setOpaque(false);
        pnlTableConAcc.setLayout(new java.awt.BorderLayout());

        pnlTableAcc.setLayout(new java.awt.BorderLayout());

        lblNameAcc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameAcc.setText("Danh sách tài khoản");
        pnlTableAcc.add(lblNameAcc, java.awt.BorderLayout.PAGE_START);

        tableAcc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneAcc.setViewportView(tableAcc);

        pnlTableAcc.add(scrollPaneAcc, java.awt.BorderLayout.CENTER);

        pnlTableConAcc.add(pnlTableAcc, java.awt.BorderLayout.CENTER);

        pnlAcc.add(pnlTableConAcc, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Tài khoản", pnlAcc);

        pnlMe.setOpaque(false);
        pnlMe.setLayout(new java.awt.BorderLayout());

        pnlNavMe.setOpaque(false);
        pnlNavMe.setLayout(new java.awt.BorderLayout());
        pnlNavMe.add(navMe, java.awt.BorderLayout.CENTER);

        pnlMe.add(pnlNavMe, java.awt.BorderLayout.PAGE_START);

        pnlTableConMe.setOpaque(false);
        pnlTableConMe.setLayout(new java.awt.BorderLayout());

        pnlTableMe.setLayout(new java.awt.BorderLayout());

        lblNameMe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameMe.setText("Danh sách thuốc");
        pnlTableMe.add(lblNameMe, java.awt.BorderLayout.PAGE_START);

        tableMe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneMe.setViewportView(tableMe);

        pnlTableMe.add(scrollPaneMe, java.awt.BorderLayout.CENTER);

        pnlTableConMe.add(pnlTableMe, java.awt.BorderLayout.CENTER);

        pnlMe.add(pnlTableConMe, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Thuốc", pnlMe);

        pnlMeE.setOpaque(false);
        pnlMeE.setLayout(new java.awt.BorderLayout());

        pnlNavMeE.setOpaque(false);
        pnlNavMeE.setLayout(new java.awt.BorderLayout());
        pnlNavMeE.add(navMeE, java.awt.BorderLayout.CENTER);

        pnlMeE.add(pnlNavMeE, java.awt.BorderLayout.PAGE_START);

        pnlTableConMeE.setOpaque(false);
        pnlTableConMeE.setLayout(new java.awt.BorderLayout());

        pnlTableMeE.setLayout(new java.awt.BorderLayout());

        lblNameMeE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameMeE.setText("Danh sách thiết bị y tế");
        pnlTableMeE.add(lblNameMeE, java.awt.BorderLayout.PAGE_START);

        tableMeE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneMeE.setViewportView(tableMeE);

        pnlTableMeE.add(scrollPaneMeE, java.awt.BorderLayout.CENTER);

        pnlTableConMeE.add(pnlTableMeE, java.awt.BorderLayout.CENTER);

        pnlMeE.add(pnlTableConMeE, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Thiết bị y tế", pnlMeE);

        add(tabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel lblNameAcc;
    private javax.swing.JLabel lblNameCus;
    private javax.swing.JLabel lblNameDis;
    private javax.swing.JLabel lblNameEmp;
    private javax.swing.JLabel lblNameMe;
    private javax.swing.JLabel lblNameMeE;
    private javax.swing.JLabel lblNameOrder;
    private javax.swing.JLabel lblNameVender;
    private gui.navbar.NavbarSearch navAcc;
    private gui.navbar.NavbarSearch navCus;
    private gui.navbar.NavbarSearch navDis;
    private gui.navbar.NavbarSearch navEmp;
    private gui.navbar.NavbarSearch navMe;
    private gui.navbar.NavbarSearch navMeE;
    private gui.navbar.NavbarSearch navOrder;
    private gui.navbar.NavbarSearch navVender;
    private javax.swing.JPanel pnlAcc;
    private javax.swing.JPanel pnlCus;
    private javax.swing.JPanel pnlDis;
    private javax.swing.JPanel pnlEmp;
    private javax.swing.JPanel pnlMe;
    private javax.swing.JPanel pnlMeE;
    private javax.swing.JPanel pnlNavAcc;
    private javax.swing.JPanel pnlNavCus;
    private javax.swing.JPanel pnlNavDis;
    private javax.swing.JPanel pnlNavEmp;
    private javax.swing.JPanel pnlNavMe;
    private javax.swing.JPanel pnlNavMeE;
    private javax.swing.JPanel pnlNavOrder;
    private javax.swing.JPanel pnlNavVender;
    private javax.swing.JPanel pnlOrder;
    private javax.swing.JPanel pnlTableAcc;
    private javax.swing.JPanel pnlTableCon;
    private javax.swing.JPanel pnlTableConAcc;
    private javax.swing.JPanel pnlTableConCus;
    private javax.swing.JPanel pnlTableConDis;
    private javax.swing.JPanel pnlTableConMe;
    private javax.swing.JPanel pnlTableConMeE;
    private javax.swing.JPanel pnlTableConOrder;
    private javax.swing.JPanel pnlTableConVender;
    private javax.swing.JPanel pnlTableCus;
    private javax.swing.JPanel pnlTableDis;
    private javax.swing.JPanel pnlTableEmp;
    private javax.swing.JPanel pnlTableMe;
    private javax.swing.JPanel pnlTableMeE;
    private javax.swing.JPanel pnlTableOrder;
    private javax.swing.JPanel pnlTableVender;
    private javax.swing.JPanel pnlVender;
    private javax.swing.JScrollPane scrollPaneAcc;
    private javax.swing.JScrollPane scrollPaneCus;
    private javax.swing.JScrollPane scrollPaneDis;
    private javax.swing.JScrollPane scrollPaneEmp;
    private javax.swing.JScrollPane scrollPaneMe;
    private javax.swing.JScrollPane scrollPaneMeE;
    private javax.swing.JScrollPane scrollPaneOrder;
    private javax.swing.JScrollPane scrollPaneVender;
    private gui.tabbedpane.TabbedPaneCustom tabbedPane;
    private gui.table.TableCustom tableAcc;
    private gui.table.TableCustom tableCus;
    private gui.table.TableCustom tableDis;
    private gui.table.TableCustom tableEmp;
    private gui.table.TableCustom tableMe;
    private gui.table.TableCustom tableMeE;
    private gui.table.TableCustom tableOrder;
    private gui.table.TableCustom tableVender;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1400, 800);
        FormLookup card = new FormLookup();
        frame.add(card);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
        
    private void init() {
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setOpaque(false);
        
        //Tabbed Pane nhân viên
        pnlEmp.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableCon.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableEmp.setBackground(new Color(255, 255, 255));
        pnlTableEmp.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navEmp.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã nhân viên", "Tên nhân viên"}));
        tableEmp.ScrollPaneTable(scrollPaneEmp);
        lblNameEmp.setPreferredSize(new Dimension(100, 40));
        lblNameEmp.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameEmp.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameEmp.setForeground(new Color(148, 148, 148));
        lblNameEmp.setOpaque(false);
        lblNameEmp.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));
        
        //Tabbed Pane khách hàng
        pnlCus.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConCus.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableCus.setBackground(new Color(255, 255, 255));
        pnlTableCus.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navCus.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã nhân viên", "Tên nhân viên"}));
        tableCus.ScrollPaneTable(scrollPaneCus);
        lblNameCus.setPreferredSize(new Dimension(100, 40));
        lblNameCus.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameCus.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameCus.setForeground(new Color(148, 148, 148));
        lblNameCus.setOpaque(false);
        lblNameCus.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));
        
        //Tabbed Pane hóa đơn
        pnlOrder.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConOrder.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableOrder.setBackground(new Color(255, 255, 255));
        pnlTableOrder.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navOrder.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã nhân viên", "Tên nhân viên"}));
        tableOrder.ScrollPaneTable(scrollPaneOrder);
        lblNameOrder.setPreferredSize(new Dimension(100, 40));
        lblNameOrder.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameOrder.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameOrder.setForeground(new Color(148, 148, 148));
        lblNameOrder.setOpaque(false);
        lblNameOrder.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));
        
        //Tabbed Pane nhà cung cấp
        pnlVender.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConVender.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableVender.setBackground(new Color(255, 255, 255));
        pnlTableVender.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navVender.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã nhân viên", "Tên nhân viên"}));
        tableVender.ScrollPaneTable(scrollPaneVender);
        lblNameVender.setPreferredSize(new Dimension(100, 40));
        lblNameVender.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameVender.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameVender.setForeground(new Color(148, 148, 148));
        lblNameVender.setOpaque(false);
        lblNameVender.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));
        
        //Tabbed Pane khuyễn mãi
        pnlDis.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConDis.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableDis.setBackground(new Color(255, 255, 255));
        pnlTableDis.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navDis.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã nhân viên", "Tên nhân viên"}));
        tableDis.ScrollPaneTable(scrollPaneDis);
        lblNameDis.setPreferredSize(new Dimension(100, 40));
        lblNameDis.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameDis.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameDis.setForeground(new Color(148, 148, 148));
        lblNameDis.setOpaque(false);
        lblNameDis.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));
        
        //Tabbed Pane tải khoản
        pnlAcc.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConAcc.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableAcc.setBackground(new Color(255, 255, 255));
        pnlTableAcc.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navAcc.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã nhân viên", "Tên nhân viên"}));
        tableAcc.ScrollPaneTable(scrollPaneAcc);
        lblNameAcc.setPreferredSize(new Dimension(100, 40));
        lblNameAcc.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameAcc.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameAcc.setForeground(new Color(148, 148, 148));
        lblNameAcc.setOpaque(false);
        lblNameAcc.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));
        
        //Tabbed Pane thuốc
        pnlMe.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConMe.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableMe.setBackground(new Color(255, 255, 255));
        pnlTableMe.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navMe.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã nhân viên", "Tên nhân viên"}));
        tableMe.ScrollPaneTable(scrollPaneMe);
        lblNameMe.setPreferredSize(new Dimension(100, 40));
        lblNameMe.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameMe.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameMe.setForeground(new Color(148, 148, 148));
        lblNameMe.setOpaque(false);
        lblNameMe.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));
        
        //Tabbed Pane thiết bị y tế
        pnlMeE.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableConMeE.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlTableMeE.setBackground(new Color(255, 255, 255));
        pnlTableMeE.setBorder(new LineBorder(new Color(206, 212, 218), 2));
        navMeE.setDefaultComboBox(new DefaultComboBoxModel<>(new String[]{"Mã nhân viên", "Tên nhân viên"}));
        tableMeE.ScrollPaneTable(scrollPaneMeE);
        lblNameMeE.setPreferredSize(new Dimension(100, 40));
        lblNameMeE.setBorder(new EmptyBorder(0, 20, 0, 20));
        lblNameMeE.setFont(new Font("Regular", Font.BOLD, 18));
        lblNameMeE.setForeground(new Color(148, 148, 148));
        lblNameMeE.setOpaque(false);
        lblNameMeE.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(206, 212, 216)));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
        // Vẽ viền
        g2.setColor(new Color(206, 212, 218));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 16, 16);
        super.paintComponent(g);
    }
}
