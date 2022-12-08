/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visao;

import javax.swing.JFrame;
import visao.cadastro.cadastroSupervisor;
import visao.cadastro.cadastroOnibus;
import visao.cadastro.cadastroMotorista;
import visao.cadastro.cadastroFiscal;
import visao.cadastro.cadastroCobrador;

/**
 *
 * @author SHARK
 */
public class Cadastro extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public Cadastro() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo( null );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSupervisor = new javax.swing.JButton();
        btnFiscal = new javax.swing.JButton();
        btnOnibus = new javax.swing.JButton();
        btnMotorista = new javax.swing.JButton();
        btnCobrador = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSupervisor.setText("Supervisor");
        btnSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupervisorActionPerformed(evt);
            }
        });

        btnFiscal.setText("Fiscal");
        btnFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiscalActionPerformed(evt);
            }
        });

        btnOnibus.setText("Ônibus");
        btnOnibus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnibusActionPerformed(evt);
            }
        });

        btnMotorista.setText("Motorista");
        btnMotorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMotoristaActionPerformed(evt);
            }
        });

        btnCobrador.setText("Cobrador");
        btnCobrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobradorActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCobrador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSupervisor, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addComponent(btnOnibus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFiscal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMotorista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCobrador, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOnibus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupervisorActionPerformed
        cadastroSupervisor principal = new cadastroSupervisor();
        principal.setVisible(true);  
    }//GEN-LAST:event_btnSupervisorActionPerformed

    private void btnOnibusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnibusActionPerformed
        cadastroOnibus principal = new cadastroOnibus();
        principal.setVisible(true);
    }//GEN-LAST:event_btnOnibusActionPerformed

    private void btnCobradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobradorActionPerformed
        cadastroCobrador principal = new cadastroCobrador();
        principal.setVisible(true);  
    }//GEN-LAST:event_btnCobradorActionPerformed

    private void btnFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiscalActionPerformed
        cadastroFiscal principal = new cadastroFiscal();
        principal.setVisible(true);
    }//GEN-LAST:event_btnFiscalActionPerformed

    private void btnMotoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMotoristaActionPerformed
        cadastroMotorista principal = new cadastroMotorista();
        principal.setVisible(true);
    }//GEN-LAST:event_btnMotoristaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cadastro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCobrador;
    private javax.swing.JButton btnFiscal;
    private javax.swing.JButton btnMotorista;
    private javax.swing.JButton btnOnibus;
    private javax.swing.JButton btnSupervisor;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
