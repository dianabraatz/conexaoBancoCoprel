/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Funcionario;

/**
 *
 * @author Charlan
 */
public class UsuarioComumView extends javax.swing.JFrame {

    /**
     * Creates new form PontoUserComumView
     */
    public UsuarioComumView() {
        initComponents();
        this.setLocationRelativeTo(null);
        setTitle("Funcionário");
    }

    public UsuarioComumView(Funcionario login) {
        PontoView obj = new PontoView();
        PontoView obj2 = new PontoView(login);
        obj.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jButton1 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        jmnList = new javax.swing.JMenu();
        jmiAnimal = new javax.swing.JMenuItem();
        jmiAnimal1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Deslogar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton1);
        jButton1.setBounds(813, 630, 100, 23);

        jmnList.setMnemonic('e');
        jmnList.setText("Cadastros");

        jmiAnimal.setText("Funcionario");
        jmiAnimal.setEnabled(false);
        jmiAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAnimalActionPerformed(evt);
            }
        });
        jmnList.add(jmiAnimal);

        jmiAnimal1.setText("Ponto");
        jmiAnimal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAnimal1ActionPerformed(evt);
            }
        });
        jmnList.add(jmiAnimal1);

        menuBar.add(jmnList);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAnimalActionPerformed

        FuncionarioView dialog = new FuncionarioView(this, true); //alterar
        dialog.setVisible(true); //alterar
    }//GEN-LAST:event_jmiAnimalActionPerformed

    private void jmiAnimal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAnimal1ActionPerformed
        PontoView dialog = new PontoView(); //alterar
        dialog.setVisible(true); //alterar
    }//GEN-LAST:event_jmiAnimal1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoginView dialog = new LoginView();
        dialog.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(UsuarioComumView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioComumView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioComumView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioComumView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuarioComumView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuItem jmiAnimal;
    private javax.swing.JMenuItem jmiAnimal1;
    private javax.swing.JMenu jmnList;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}
