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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Funcao;

/**
 *
 * @author Administrador
 */
public class FuncaoDAO {
    
    public boolean adicionar(Funcao objeto) { //alterar a classe do parâmetro
        try {
            String sql = "INSERT INTO funcao(nome, nivelAcesso) VALUES (?,?);"; //alterar a tabela, os atributos e o número de interrogações, conforme o número de atributos

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            
            pstmt.setString(1, objeto.getNome()); 
            pstmt.setInt(2, objeto.getNivel_acesso()); 
      
            
            pstmt.executeUpdate(); 
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean alterar(Funcao objeto) {
        try {
            String sql = " UPDATE funcao SET nome= ?, nivelAcesso = ? WHERE codFuncao = ?;"; //alterar tabela, atributos e chave primária - where a chave primaria

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);

            //definindo as interrogações (uma linha para cada ? do SQL)
            
            pstmt.setString(1, objeto.getNome()); 
            pstmt.setInt(2, objeto.getNivel_acesso()); 
            pstmt.setInt(3, objeto.getCodFuncao());

            pstmt.executeUpdate(); //executando
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean excluir(Funcao objeto) {
        try {
            String sql = " DELETE FROM funcao WHERE codFuncao = ? "; //alterar a tabela e a chave primária no WHERE

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, objeto.getCodFuncao()); //alterar conforme a chave primária

            if (pstmt.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Funcao> selecionar() {
        String sql = "SELECT * FROM funcao ORDER BY codFuncao"; //alterar tabela e atributos

        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Funcao> lista = new ArrayList<>();//alterar a classe 

            while (rs.next()) {
                Funcao objeto = new Funcao(); //alterar o nome da classe e o construtor

                //setar os atributos do objeto. Cuidar o tipo dos atributos
                objeto.setCodFuncao(rs.getInt("codFuncao")); //alterar
                objeto.setNome(rs.getString("nome"));  //alterar
                objeto.setNivel_acesso(rs.getInt("nivelAcesso"));

                lista.add(objeto);
            }
            stmt.close();
            return lista;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //método só para testar
    public static void main(String[] args) {
    }
    
}
