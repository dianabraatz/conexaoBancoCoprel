
package controller;

import dao.PontoDAO;
import javax.swing.JOptionPane;
import view.PontoView;


/**
 *
 * @author edimar
 */
public class PontoController {


    public static void abrirponto(int objeto, PontoView tela) {
    
        PontoDAO dao = new PontoDAO();
        boolean resultado = dao.abrirPonto(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(tela, "Inserido com sucesso!"); //não alterar
            tela.setVisible(false);
            tela.dispose();
            
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a inserção!");
        }

    }
    
    public static void fecharPonto(int objeto, PontoView tela) {
        
        System.out.println("fecha ponto" +objeto);
        
        PontoDAO dao = new PontoDAO();
        boolean resultado = dao.fecharPonto(objeto);
        
        if (resultado) {
            JOptionPane.showMessageDialog(tela, "Inserido com sucesso!"); 
            tela.setVisible(false);
            tela.dispose();
            
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a inserção!");
        }

    }
} 
