/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PontoDAO;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Ponto;
import view.PontoAdministradorView;


/**
 *
 * @author edimar
 */
public class PontoAdministradorController {


    public static void abrirponto(PontoAdministradorView tela) {
     
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
