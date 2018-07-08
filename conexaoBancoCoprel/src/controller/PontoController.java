
package controller;

import dao.PontoDAO;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Ponto;
import view.AdministradorView;


/**
 *
 * @author edimar
 */
public class PontoController {


    public static void abrirponto(AdministradorView tela) {
     
        LocalDateTime horaLocal = LocalDateTime.now(); 
        
        //alterar:: criando objeto
        Ponto ponto = new Ponto();
        //ponto.setDh_inicio(dh_inicio);
        //reverrrrrrrrrrrrrrr
    
        //alterar:: adicionando o objeto no banco de dados
        PontoDAO dao = new PontoDAO();
        boolean resultado = dao.adicionar(ponto);
        if (resultado) {
            JOptionPane.showMessageDialog(tela, "Inserido com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a inserção!");
        }

    }
   

}
