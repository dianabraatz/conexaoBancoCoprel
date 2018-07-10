/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Funcionario;


/**
 *
 * @author edimar
 */
public class AdministradorView extends javax.swing.JFrame {

    /**
     * Creates new form MenuView
     */
    public AdministradorView() {
        initComponents();
        this.setLocationRelativeTo(null);
        setTitle("Administrador");
    }

    public AdministradorView(Funcionario login) {
       // PontoView obj = new PontoView();
        PontoView obj2 = new PontoView(login);
        obj2.setVisible(true);
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jButton1 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        jmnList = new javax.swing.JMenu();
        jmiAnimal = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jmiPonto1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Exemplo de Software");
        setLocationByPlatform(true);
        setResizable(false);

        jButton1.setText("Deslogar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton1);
        jButton1.setBounds(823, 640, 100, 23);

        jmnList.setMnemonic('e');
        jmnList.setText("Cadastros");

        jmiAnimal.setText("Funcionario");
        jmiAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAnimalActionPerformed(evt);
            }
        });
        jmnList.add(jmiAnimal);

        jMenuItem1.setText("Funcao");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmnList.add(jMenuItem1);

        jMenuItem2.setText("Setor");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jmnList.add(jMenuItem2);

        jmiPonto1.setText("Ponto");
        jmiPonto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPonto1ActionPerformed(evt);
            }
        });
        jmnList.add(jmiPonto1);

        menuBar.add(jmnList);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAnimalActionPerformed

        FuncionarioView dialog = new FuncionarioView(this, true); //alterar
        dialog.setVisible(true); //alterar
    }//GEN-LAST:event_jmiAnimalActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        FuncaoView dialog = new FuncaoView(this, true); //alterar
        dialog.setVisible(true); //alterar
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        SetorView dialog = new SetorView(this, true); //alterar
        dialog.setVisible(true); //alterar
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jmiPonto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPonto1ActionPerformed
        PontoView dialog = PontoView();
        dialog.setVisible(true);
    }//GEN-LAST:event_jmiPonto1ActionPerformed

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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministradorView().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jmiAnimal;
    private javax.swing.JMenuItem jmiPonto1;
    private javax.swing.JMenu jmnList;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

    private PontoView PontoView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
