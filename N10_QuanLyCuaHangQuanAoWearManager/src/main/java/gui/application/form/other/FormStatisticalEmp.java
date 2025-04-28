package gui.application.form.other;

import bus.EmployeeBUS;
import gui.chart.ModelChartLine;
import gui.chart.ModelChartPie;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.rmi.RemoteException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FormStatisticalEmp extends javax.swing.JPanel {

    private EmployeeBUS emp_bus;
    private DefaultTableModel tableModel;
    private ButtonGroup periodGroup;
    private LocalDate currentDate = LocalDate.now();
    private Map<Integer, String> dayOfWeekMap = Map.of(
            1, "Chủ Nhật",
            2, "Thứ Hai",
            3, "Thứ Ba",
            4, "Thứ Tư",
            5, "Thứ Năm",
            6, "Thứ Sáu",
            7, "Thứ Bảy"
    );

    public FormStatisticalEmp() throws RemoteException {
        initComponents();
        emp_bus = new EmployeeBUS();
        tableModel = (DefaultTableModel) table.getModel();
        periodGroup = new ButtonGroup();
        periodGroup.add(radioThisWeek);
        periodGroup.add(radioLastWeek);
        periodGroup.add(radioTwoWeeksAgo);
        radioThisWeek.setSelected(true);
        loadDataForSelectedPeriod();
    }

    private LocalDate[] getStartAndEndDate(String period) {
        LocalDate start = null;
        LocalDate end = null;
        switch (period) {
            case "this_week":
                start = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                end = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
            case "last_week":
                LocalDate lastWeekStart = currentDate.minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                start = lastWeekStart;
                end = lastWeekStart.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
            case "two_weeks_ago":
                LocalDate twoWeeksAgoStart = currentDate.minusWeeks(2).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                start = twoWeeksAgoStart;
                end = twoWeeksAgoStart.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
        }
        return new LocalDate[]{start, end};
    }

    private void loadDataForSelectedPeriod() {
        String selectedPeriod = "this_week";
        if (radioLastWeek.isSelected()) {
            selectedPeriod = "last_week";
        } else if (radioTwoWeeksAgo.isSelected()) {
            selectedPeriod = "two_weeks_ago";
        }
        loadData(selectedPeriod);
    }

    private void loadData(String period) {
        tableModel.setRowCount(0);
        chartLine.setModel(new ArrayList<>());
        chartPie.setModel(new ArrayList<>());
        chartLine.getPnlData().removeAll();
        chartLine.getPnlData().revalidate();
        chartLine.getPnlData().repaint();

        LocalDate[] dateRange = getStartAndEndDate(period);
        LocalDate startDate = dateRange[0];
        LocalDate endDate = dateRange[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String periodLabel = "";
        if (startDate != null && endDate != null) {
            periodLabel = String.format(" (%s - %s)", startDate.format(formatter), endDate.format(formatter));
        }
        jLabel1.setText("THỐNG KÊ DOANH SỐ NHÂN VIÊN TRONG TUẦN" + periodLabel);

        try {
            // 1. Lấy số lượng sản phẩm bán theo ngày trong tuần của mỗi nhân viên
            List<Object[]> salesCountByDay = emp_bus.getEmployeeProductSalesCountByDayOfWeek(startDate, endDate);
            System.out.println("Dữ liệu số lượng sản phẩm bán theo ngày (" + period + "): " + salesCountByDay);

// Aggregate sales count by day (sum across all employees) for the line chart
// Map<Integer, Double> aggregatedSalesCount = new HashMap<>(); // No longer needed
// for (Object[] row : salesCountByDay) {
//     int dayOfWeek = ((Number) row[0]).intValue();
//     long totalCount = ((Number) row[1]).longValue(); // Assuming count is at index 2
//     aggregatedSalesCount.put(dayOfWeek, aggregatedSalesCount.getOrDefault(dayOfWeek, 0.0) + totalCount);
// }

            List<ModelChartLine> lineData = new ArrayList<>();
            for (Object[] row : salesCountByDay) {
                int dayOfWeek = ((Number) row[0]).intValue();
                long totalCount = ((Number) row[1]).longValue();
                lineData.add(new ModelChartLine(
                        dayOfWeekMap.get(dayOfWeek),
                        (double) totalCount // Cast to double for ModelChartLine
                ));
            }
            chartLine.setModel(lineData);

            // 2. Lấy top 5 nhân viên có số lượng sản phẩm bán nhiều nhất
            List<Object[]> top5Employees = emp_bus.getTop5BestSellingProductsThisWeek(startDate, endDate);
            List<ModelChartPie> pieData = new ArrayList<>();
            Random rand = new Random();
            if (top5Employees != null) {
                for (Object[] row : top5Employees) {
                    String employeeName = (String) row[1]; // Assuming employee name is at index 1
                    long totalCount = ((Number) row[2]).longValue(); // Assuming total count is at index 2
                    pieData.add(new ModelChartPie(
                            employeeName,
                            (double) totalCount,
                            new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))
                    ));
                }
            }
            chartPie.setModel(pieData);

            // 3. Lấy tổng số lượng và tổng giá trị sản phẩm bán của từng nhân viên trong tuần
            List<Object[]> employeeProductStats = emp_bus.getEmployeeTotalProductStatsThisWeek(startDate, endDate);
            System.out.println("Thống kê sản phẩm bán của nhân viên tuần (" + period + "): " + employeeProductStats);
            if (employeeProductStats != null) {
                for (Object[] row : employeeProductStats) {
                    String employeeName = (String) row[1]; // Assuming employee name is at index 1
                    long totalCount = ((Number) row[2]).longValue(); // Assuming total count is at index 2
                    double totalRevenue = (row[3] != null) ? ((Number) row[3]).doubleValue() : 0.0; // Assuming total revenue is at index 3

                    tableModel.addRow(new Object[]{
                            employeeName,
                            totalCount,
                            String.format("%.2f VNĐ", totalRevenue)
                    });
                }
            }

            SwingUtilities.invokeLater(() -> {
                try {
                    table.setRowHeight(40);
                    table.getColumnModel().getColumn(0).setPreferredWidth(200); // Tên nhân viên
                    table.getColumnModel().getColumn(1).setPreferredWidth(150); // Số lượng sản phẩm bán
                    table.getColumnModel().getColumn(2).setPreferredWidth(150); // Tổng doanh thu
                    table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        chartLine = new gui.chart.ChartLine();
        chartPie = new gui.chart.ChartPie();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new gui.table.TableCustom();
        nav = new gui.navbar.NavbarSearch();
        jPanel1 = new javax.swing.JPanel();
        radioThisWeek = new javax.swing.JRadioButton();
        radioLastWeek = new javax.swing.JRadioButton();
        radioTwoWeeksAgo = new javax.swing.JRadioButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("THỐNG KÊ DOANH SỐ NHÂN VIÊN TRONG TUẦN");

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                        "Tên nhân viên", "Số lượng sản phẩm bán", "Tổng doanh thu"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane2.setViewportView(table);

        nav.hideComponentSearch();

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        radioThisWeek.setText("Tuần này");
        radioThisWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioThisWeekActionPerformed(evt);
            }
        });
        jPanel1.add(radioThisWeek);

        radioLastWeek.setText("Tuần trước");
        radioLastWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioLastWeekActionPerformed(evt);
            }
        });
        jPanel1.add(radioLastWeek);

        radioTwoWeeksAgo.setText("2 tuần trước");
        radioTwoWeeksAgo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTwoWeeksAgoActionPerformed(evt);
            }
        });
        jPanel1.add(radioTwoWeeksAgo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nav, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(chartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 800, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(chartPie, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                                        .addComponent(jLabel1)
                                        .addComponent(jScrollPane2)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(nav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(chartPie, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                        .addComponent(chartLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    private void radioThisWeekActionPerformed(java.awt.event.ActionEvent evt) {
        loadDataForSelectedPeriod();
    }

    private void radioLastWeekActionPerformed(java.awt.event.ActionEvent evt) {
        loadDataForSelectedPeriod();
    }

    private void radioTwoWeeksAgoActionPerformed(java.awt.event.ActionEvent evt) {
        loadDataForSelectedPeriod();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Test FormStatisticalEmp");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1200, 800);
                frame.setLocationRelativeTo(null);

                FormStatisticalEmp form = new FormStatisticalEmp();
                frame.setContentPane(form);

                frame.setVisible(true);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    // Variables declaration - do not modify
    private gui.chart.ChartLine chartLine;
    private gui.chart.ChartPie chartPie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private gui.navbar.NavbarSearch nav;
    private javax.swing.JRadioButton radioLastWeek;
    private javax.swing.JRadioButton radioThisWeek;
    private javax.swing.JRadioButton radioTwoWeeksAgo;
    private gui.table.TableCustom table;
    // End of variables declaration//GEN-END:variables
}