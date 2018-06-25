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
import model.Setor;

/**
 *
 * @author Administrador
 */
public class SetorDAO {
    
    public boolean adicionar(Setor objeto) { //alterar a classe do parâmetro
        try {
            String sql = "INSERT INTO setor (codSetor, permissao_horaExtra, repouso_semanalFixo, nome) VALUES (?, ?, ?, ?)"; //alterar a tabela, os atributos e o número de interrogações, conforme o número de atributos

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            //definindo as interrogações (uma linha para cada ? do SQL)
            pstmt.setInt(1, objeto.getCodSetor()); // alterar o primeiro parâmetro indica a interrogação, começando em 1
            pstmt.setInt(2, objeto.getPermissao_horaExtra());
            pstmt.setInt(3, objeto.getRepouso_semanalFixo());
            pstmt.setString(4, objeto.getNomeSetor()); // alterar o primeiro parâmetro indica a interrogação, começando em 1             
            
            pstmt.executeUpdate(); //executando
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean alterar(Setor objeto) {
        try {
            String sql = " UPDATE setor "
                    + "    SET permissao_horaExtra = ?, repouso_semanalFixo = ?, nomeSetor = ?"
                    + "  WHERE codSetor = ? "; //alterar tabela, atributos e chave primária - where a chave primaria

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);

            //definindo as interrogações (uma linha para cada ? do SQL)
            pstmt.setInt(1, objeto.getPermissao_horaExtra());
            pstmt.setInt(2, objeto.getRepouso_semanalFixo());
            pstmt.setString(3, objeto.getNomeSetor()); 
            pstmt.setInt(4, objeto.getCodSetor());

            pstmt.executeUpdate(); //executando
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean excluir(Setor objeto) {
        try {
            String sql = " DELETE FROM setor WHERE codSetor = ? "; //alterar a tabela e a chave primária no WHERE

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, objeto.getCodSetor()); //alterar conforme a chave primária

            if (pstmt.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Setor> selecionar() {
        String sql = "SELECT codSetor, nomeSetor FROM setor ORDER BY nomeSetor"; //alterar tabela e atributos

        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Setor> lista = new ArrayList<>();//alterar a classe 

            while (rs.next()) {
                Setor objeto = new Setor(); //alterar o nome da classe e o construtor

                //setar os atributos do objeto. Cuidar o tipo dos atributos
                objeto.setCodSetor(rs.getInt("codSetor")); //alterar
                objeto.setNomeSetor(rs.getString("nomeSetor"));  //alterar

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
        Setor objeto = new Setor(); //alterar
        objeto.setNomeSetor("Diana"); //alterar

        SetorDAO dao = new SetorDAO(); //alterar
        dao.adicionar(objeto); //alterar
    }
    
}
