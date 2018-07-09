/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FuncionarioDAO;
import javax.swing.JOptionPane;
import view.LoginView;
import model.Funcionario;
import view.AdministradorView;
import view.UsuarioComumView;

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
        Funcionario obj = new Funcionario();
        obj.setNumeroRegistro(numeroRegistro);
        obj.setSenha(senha);
        
        //alterar:: alterando o objeto no banco de dados
        FuncionarioDAO dao = new FuncionarioDAO(); //alterar
        boolean resultado = dao.executaLogin(obj);
        //System.out.println(resultado);
        if (resultado) {
            JOptionPane.showMessageDialog(tela, "Numero de registro e senha encontrados."); //não alterar
            buscaNivelAcesso(obj, tela);
        } else {
            
            JOptionPane.showMessageDialog(tela, "Problemas com a verificação!");
        }
    }
        //define para qual tela o usuário sera direcionado (administrador/usuário comum)
        public static void buscaNivelAcesso(Funcionario login, LoginView tela){
        FuncionarioDAO dao = new FuncionarioDAO();
        int resultado = dao.buscaNivelAcesso(login);
        
        System.out.println(resultado);
        
        if(resultado == 1){ //usuário comum           
            
            UsuarioComumView obj =new UsuarioComumView();
            UsuarioComumView obj2 = new UsuarioComumView(login);
            obj.setVisible(true);
            tela.setVisible(false);
         
        }else if(resultado == 2){  //administrador
            
            AdministradorView obj =new AdministradorView();
            AdministradorView obj2 = new AdministradorView(login);
            obj.setVisible(true);
            tela.setVisible(false);
            
        }
    }
    
}
