/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Login;

/**
 *
 * @author Charlan
 */
public class LoginDAO {
    public boolean executaLogin(Login objeto){
        
        boolean resultado= false;
        try{
            String sql = "SELECT * FROM funcionario WHERE numRegistro =? AND senha =?;";
        
            PreparedStatement pst = Conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, objeto.getNumRegistro());
            pst.setString(2, objeto.getSenha());
            
            ResultSet rs = pst.executeQuery();
            
            resultado = rs.next();
            return resultado;
 
        }catch (SQLException | ClassNotFoundException e) {
                 System.out.println(e);
                 return resultado;
        }
        
    }
    
    public int buscaNivelAcesso(Login objeto){
        int nivelAcesso = 0;
 
        try{
            String sql = "SELECT funcao.nivelAcesso as nivel_acesso FROM funcao funcao JOIN funcionario f ON funcao.codFuncao = f.codFuncao WHERE numRegistro = ? AND senha =?;";
            
            PreparedStatement pst = Conexao.getConexao().prepareStatement(sql); 
            pst.setInt(1, objeto.getNumRegistro());
            pst.setString(2, objeto.getSenha());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                nivelAcesso = rs.getInt("nivel_acesso");
            }
            pst.close();
            return nivelAcesso;
            
            
        }catch (SQLException | ClassNotFoundException e) {
                 System.out.println(e);
        }

        return nivelAcesso;

    }
    
    public static void main(String[] args) {
        
    }
}