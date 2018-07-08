/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

/**
 *
 * @author Administrador
 */
public class FuncionarioDAO {
    
    public boolean adicionar(Funcionario objeto) { //alterar a classe do parâmetro
        try {
            String sql = "INSERT INTO funcionario (numeroRegistro, dataNascimento, nome, ctps, rg, cpf, cnh, dataAdmissao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; //alterar a tabela, os atributos e o número de interrogações, conforme o número de atributos

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            //definindo as interrogações (uma linha para cada ? do SQL)
            pstmt.setInt(1, objeto.getNumeroRegistro()); // alterar o primeiro parâmetro indica a interrogação, começando em 1
            pstmt.setDate(2, new Date(objeto.getDataNascimento().getTime()));
            //pstmt.setDate(2, objeto.getDataNascimento()); 
            pstmt.setString(3, objeto.getNome()); // alterar o primeiro parâmetro indica a interrogação, começando em 1
            pstmt.setString(4, objeto.getCtps());
            pstmt.setString(5, objeto.getRg());
            pstmt.setString(6, objeto.getCpf()); // alterar o primeiro parâmetro indica a interrogação, começando em 1
            pstmt.setString(7, objeto.getCnh());
            pstmt.setDate(8, new Date(objeto.getDataNascimento().getTime()));         
            
            pstmt.executeUpdate(); //executando
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean alterar(Funcionario objeto) {
        try {
            String sql = " UPDATE funcionario "
                    + "    SET dataNascimento = ?, nome = ?, ctps = ?, rg = ?, cpf = ?, cnh = ?, dataAdmissao = ?"
                    + "  WHERE numeroRegistro = ? "; //alterar tabela, atributos e chave primária - where a chave primaria

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);

            //definindo as interrogações (uma linha para cada ? do SQL)
            pstmt.setDate(1, new Date(objeto.getDataNascimento().getTime())); 
            pstmt.setString(2, objeto.getNome()); 
            pstmt.setString(3, objeto.getCtps());
            pstmt.setString(4, objeto.getRg());
            pstmt.setString(5, objeto.getCpf()); 
            pstmt.setString(6, objeto.getCnh());
            pstmt.setDate(7, new Date(objeto.getDataNascimento().getTime()));   
            pstmt.setInt(8, objeto.getNumeroRegistro());

            pstmt.executeUpdate(); //executando
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean excluir(Funcionario objeto) {
        try {
            String sql = " DELETE FROM funcionario WHERE numeroRegistro = ? "; //alterar a tabela e a chave primária no WHERE

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, objeto.getNumeroRegistro()); //alterar conforme a chave primária

            if (pstmt.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Funcionario> selecionar() {
        String sql = "SELECT numeroRegistro, nome FROM funcionario ORDER BY nome"; //alterar tabela e atributos

        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Funcionario> lista = new ArrayList<>();//alterar a classe 

            while (rs.next()) {
                Funcionario objeto = new Funcionario(); //alterar o nome da classe e o construtor

                //setar os atributos do objeto. Cuidar o tipo dos atributos
                objeto.setNumeroRegistro(rs.getInt("numeroRegistro")); //alterar
                objeto.setNome(rs.getString("nome"));  //alterar
                objeto.setRg(rs.getString("rg"));
                objeto.setCpf(rs.getString("cpf"));
                objeto.setDataNascimento(rs.getDate("dataNascimento"));
                objeto.setCtps(rs.getString("ctps"));
                

                lista.add(objeto);
            }
            stmt.close();
            return lista;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    //verifica se existe usuário e senha no banco
     public boolean executaLogin(Funcionario objeto){
        boolean resultado= false;
        try{
            String sql = "SELECT * FROM funcionario WHERE numRegistro =? AND senha =?;";
        
            PreparedStatement pst = Conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, objeto.getNumeroRegistro());
            pst.setString(2, objeto.getSenha());
            
            ResultSet rs = pst.executeQuery();
            
            //retorna true ou false para confirmar se a busca foi completa;
            resultado = rs.next();
            
            System.out.println(resultado);
            
            return resultado;
            
        }catch (SQLException | ClassNotFoundException e) {
                 System.out.println(e);
                 return resultado;
        }
        
    }
    
    public int buscaNivelAcesso(Funcionario objeto){
        int nivelAcesso = 0;
 
        try{
            String sql = "SELECT funcao.nivelAcesso as nivel_acesso FROM funcao funcao JOIN funcionario f ON funcao.codFuncao = f.codFuncao WHERE numRegistro = ? AND senha =?;";
            
            PreparedStatement pst = Conexao.getConexao().prepareStatement(sql); 
            pst.setInt(1, objeto.getNumeroRegistro());
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

    //método só para testar
    public static void main(String[] args) {
        Funcionario objeto = new Funcionario(); //alterar
        objeto.setNome("Diana"); //alterar
        objeto.setCpf(new String("02851371070")); //alterar - crei um objeto long

        FuncionarioDAO dao = new FuncionarioDAO(); //alterar
        dao.adicionar(objeto); //alterar
    }
    
}
