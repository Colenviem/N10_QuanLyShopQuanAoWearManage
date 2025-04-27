package gui.application.form.other;

import bus.EmployeeBUS;
import gui.chart.ModelChartLine;
import gui.chart.ModelChartPie;
import gui.model.ModelStaff;
import java.awt.Color;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class FormStatisticalEmp extends javax.swing.JPanel {

    private EmployeeBUS emp_bus;

    public FormStatisticalEmp() throws RemoteException {
        initComponents();
        emp_bus = new EmployeeBUS();
        initData();
    }

private void initData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data

        try {
            List<Object[]> productData = emp_bus.getEmployeeProductSalesAndCount();
            List<Object[]> totalSalesData = emp_bus.getEmployeeTotalSales();

            Map<Object, Object[]> productMap = new HashMap<>();
            for (Object[] row : productData) {
                productMap.put(row[0], row); // Key: employee ID, Value: [id, fullName, totalProductPrice, totalProductCount]
            }

            Map<Object, Object[]> salesMap = new HashMap<>();
            for (Object[] row : totalSalesData) {
                salesMap.put(row[0], row); // Key: employee ID, Value: [id, fullName, totalRevenue]
            }

            Set<Object> employeeIds = new HashSet<>();
            employeeIds.addAll(productMap.keySet());
            employeeIds.addAll(salesMap.keySet());

            for (Object id : employeeIds) {
                Object[] productRow = productMap.getOrDefault(id, new Object[]{id, "Unknown", 0.0, 0L});
                Object[] salesRow = salesMap.getOrDefault(id, new Object[]{id, "Unknown", 0.0});

                model.addRow(new Object[]{
                    productRow[0], // Mã nhân viên
                    productRow[1], // Tên nhân viên
                    productRow[3], // Số lượng sản phẩm bán
                    String.format("%.2f", productRow[2]), // Giá trị sản phẩm bán
                    String.format("%.2f VNĐ", salesRow[2])  // Tổng doanh thu
                });
            }

            // Configure table
            SwingUtilities.invokeLater(() -> {
                try {
//                    table.fixTable(jScrollPane2); // Sử dụng đúng jScrollPane2
                    table.setRowHeight(150);
                    table.getColumnModel().getColumn(0).setPreferredWidth(100); // Mã nhân viên
                    table.getColumnModel().getColumn(1).setPreferredWidth(200); // Tên nhân viên
                    table.getColumnModel().getColumn(2).setPreferredWidth(150); // Số lượng sản phẩm bán
                    table.getColumnModel().getColumn(3).setPreferredWidth(150); // Giá trị sản phẩm bán
                    table.getColumnModel().getColumn(4).setPreferredWidth(150); // Tổng doanh thu
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Populate chartPie (Tên nhân viên và Số lượng sản phẩm bán ra)
            List<ModelChartPie> pieData = new ArrayList<>();
            Random rand = new Random();
            for (Object[] row : productData) {
                if (((Number) row[3]).longValue() > 0) {
                    pieData.add(new ModelChartPie(
                        (String) row[1], // Employee Name
                        ((Number) row[3]).doubleValue(), // Số lượng sản phẩm
                        new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))
                    ));
                }
            }
            chartPie.setModel(pieData);

            // Populate chartLine (Tên nhân viên và Tổng giá trị sản phẩm bán ra)
            List<ModelChartLine> lineData = new ArrayList<>();
            for (Object[] row : productData) {
                if (((Number) row[2]).doubleValue() > 0) {
                    lineData.add(new ModelChartLine(
                        (String) row[1], // Employee Name
                        ((Number) row[2]).doubleValue() // Tổng giá trị sản phẩm bán ra
                    ));
                }
            }
            chartLine.setModel(lineData);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartLine = new gui.chart.ChartLine();
        chartPie = new gui.chart.ChartPie();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new gui.table.TableCustom();
        nav = new gui.navbar.NavbarSearch();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("TABLE EMPLOYEE");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Số lượng sản phẩm bán", "Tổng tiền bán", "Trung bình doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table);
        nav.hideComponentSearch();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(nav, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chartPie, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(nav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chartPie, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addComponent(chartLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.chart.ChartLine chartLine;
    private gui.chart.ChartPie chartPie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private gui.navbar.NavbarSearch nav;
    private gui.table.TableCustom table;
    // End of variables declaration//GEN-END:variables
}
