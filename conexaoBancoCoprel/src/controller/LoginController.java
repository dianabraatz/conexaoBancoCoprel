/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import view.LoginView;
import model.Login;
import dao.LoginDAO;
import view.PontoAdministradorView;
import view.PontoUserComumView;

/**
 *
 * @author Charlan
 */
public class LoginController {
        public static void limparCampos(LoginView tela) {
            //alterar:: limpando os campos
            tela.jtfnumRegistro.setText("");
            tela.jpfSenha.setText("");

    }
        
         public static boolean verificarCampos(LoginView tela) {
        //alterar:: conforme os campos obrigatórios
        if (tela.jtfnumRegistro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo do numero de registro!");
            return false;
        }else if (tela.jpfSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo da senha.");
            return false;
        }return true;
    }
         
    public static void executaLogin(LoginView tela){
        //verificando se os campos estão preenchidos
        if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }
        //alterar:: obtendo os valores preenchidos
        Integer numeroRegistro = Integer.parseInt(tela.jtfnumRegistro.getText().trim());
        String senha = String.valueOf(tela.jpfSenha.getPassword());
        
        //alterar:: criando objeto
        Login login = new Login();
        login.setNumRegistro(numeroRegistro);
        login.setSenha(senha);
        
        //alterar:: alterando o objeto no banco de dados
        LoginDAO dao = new LoginDAO(); //alterar
        boolean resultado = dao.executaLogin(login);
        //System.out.println(resultado);
        if (resultado) {
            JOptionPane.showMessageDialog(tela, "Numero de registro e senha encontrados."); //não alterar
            buscaNivelAcesso(login);
        } else {
            
            JOptionPane.showMessageDialog(tela, "Problemas com a verificação!");
        }
    }
    
    public static void buscaNivelAcesso(Login login){
        LoginDAO dao = new LoginDAO();
        int resultado = dao.buscaNivelAcesso(login);
        
        System.out.println(resultado);
        
        if(resultado == 1){ //usuário comum
            
            LoginView log = new LoginView();            
            
            PontoUserComumView obj =new PontoUserComumView();
            obj.setVisible(true);
            log.setVisible(false);
         
        }else if(resultado == 2){
            LoginView log = new LoginView();            
            
            PontoAdministradorView obj =new PontoAdministradorView();
            obj.setVisible(true);
            log.setVisible(false);
        }
    }
    
}
