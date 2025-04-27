package gui.application.form;

import gui.application.Application;
import java.awt.Color;
import java.rmi.RemoteException;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class LoginForm extends javax.swing.JFrame {

    private Animator animatorLogin;
    private Animator animatorBody;
    private boolean signIn;

    public LoginForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(242, 242, 242));
        TimingTarget targetLogin = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (signIn) {
                    background1.setAnimate(fraction);
                } else {
                    background1.setAnimate(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (signIn) {
                    panelLogin.setVisible(false);
                    background1.setShowPaint(true);
                    panelBody.setAlpha(0);
                    panelBody.setVisible(true);
                    animatorBody.start();
                } else {
                    enableLogin(true);
                    txtUser.grabFocus();
                }
            }
        };
        TimingTarget targetBody = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (signIn) {
                    panelBody.setAlpha(fraction);
                } else {
                    panelBody.setAlpha(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (signIn == false) {
                    panelBody.setVisible(false);
                    background1.setShowPaint(false);
                    background1.setAnimate(1);
                    panelLogin.setVisible(true);
                    animatorLogin.start();
                }
            }
        };
        animatorLogin = new Animator(1500, targetLogin);
        animatorBody = new Animator(500, targetBody);
        animatorLogin.setResolution(0);
        animatorBody.setResolution(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBody = new gui.login.PanelTransparent();
        background1 = new gui.login.Background();
        panelLogin = new javax.swing.JPanel();
        cmdSignIn = new gui.login.Button();
        txtPass = new gui.login.PasswordField();
        txtUser = new gui.login.TextField();
        jLabel1 = new javax.swing.JLabel();
        cmdSignUp = new gui.login.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelBodyLayout = new javax.swing.GroupLayout(panelBody);
        panelBody.setLayout(panelBodyLayout);
        panelBodyLayout.setHorizontalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 723, Short.MAX_VALUE)
        );
        panelBodyLayout.setVerticalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );

        cmdSignIn.setBackground(new java.awt.Color(157, 153, 255));
        cmdSignIn.setForeground(new java.awt.Color(245, 245, 245));
        cmdSignIn.setText("LOGIN");
        cmdSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    cmdSignInActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        txtPass.setBackground(new java.awt.Color(242, 242, 242));
        txtPass.setLabelText("Password");

        txtUser.setBackground(new java.awt.Color(242, 242, 242));
        txtUser.setLabelText("User");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/png/splash.png"))); // NOI18N

        cmdSignUp.setBackground(new java.awt.Color(102, 255, 0));
        cmdSignUp.setForeground(new java.awt.Color(245, 245, 245));
        cmdSignUp.setText("REGISTER");
        cmdSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdSignUpMouseClicked(evt);
            }
        });
        cmdSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addGap(98, 98, 98)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addComponent(cmdSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(cmdSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114))
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSignInActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_cmdSignInActionPerformed
        if (!animatorLogin.isRunning()) {
            signIn = true;
            String user = txtUser.getText().trim();
            String pass = String.valueOf(txtPass.getPassword());
            boolean action = true;
            if (user.equals("")) {
                txtUser.setHelperText("Please input user name");
                txtUser.grabFocus();
                action = false;
            }
            if (pass.equals("")) {
                txtPass.setHelperText("Please input password");
                if (action) {
                    txtPass.grabFocus();
                }
                action = false;
            }
            if (action) {
                // Giả lập kiểm tra tài khoản và mật khẩu (thay bằng kiểm tra thực tế nếu có)
                if (user.equals("admin") && pass.equals("123")) {
                    this.dispose(); // Đóng Form_Login
                    Application.login();
                } else {
                    txtUser.setHelperText("Invalid username or password");
                    txtPass.setHelperText("Invalid username or password");
                }
            }
        }
    }//GEN-LAST:event_cmdSignInActionPerformed

    private void cmdSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSignUpActionPerformed
        RegisterForm registerForm = new RegisterForm();
        registerForm.setVisible(true);
        this.dispose(); // Close the current login form
    }//GEN-LAST:event_cmdSignUpActionPerformed

    private void cmdSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSignUpMouseClicked
       
    }//GEN-LAST:event_cmdSignUpMouseClicked

    private void enableLogin(boolean action) {
        txtUser.setEditable(action);
        txtPass.setEditable(action);
        cmdSignIn.setEnabled(action);
    }

    public void clearLogin() {
        txtUser.setText("");
        txtPass.setText("");
        txtUser.setHelperText("");
        txtPass.setHelperText("");
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LoginForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.login.Background background1;
    private gui.login.Button cmdSignIn;
    private gui.login.Button cmdSignUp;
    private javax.swing.JLabel jLabel1;
    private gui.login.PanelTransparent panelBody;
    private javax.swing.JPanel panelLogin;
    private gui.login.PasswordField txtPass;
    private gui.login.TextField txtUser;
    // End of variables declaration//GEN-END:variables
}
