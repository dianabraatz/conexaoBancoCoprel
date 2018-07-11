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
import model.Ocorrencia;

/**
 *
 * @author Administrador
 */
public class OcorrenciaDAO {
    
    public boolean adicionar(Ocorrencia objeto) { //alterar a classe do parâmetro
        try {
            String sql = "INSERT INTO ocorrencia(justificativa) VALUES (?);"; //alterar a tabela, os atributos e o número de interrogações, conforme o número de atributos

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            pstmt.setString(1, objeto.getJustificativa());           
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean alterar(Ocorrencia objeto) {
        try {
            String sql = " UPDATE setor SET nome = ?  WHERE codSetor = ?;";

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);

            //definindo as interrogações (uma linha para cada ? do SQL)
            pstmt.setString(1, objeto.getNomeSetor()); 
            pstmt.setInt(2, objeto.getCodSetor());

            pstmt.executeUpdate(); //executando
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean excluir(Setor objeto) {
        try {
            String sql = " DELETE FROM setor WHERE codSetor = ?;"; //alterar a tabela e a chave primária no WHERE

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

    public List<Ocorrencia> selecionar() {
        String sql = "SELECT * FROM setor"; //alterar tabela e atributos

        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Setor> lista = new ArrayList<>();//alterar a classe 

            while (rs.next()) {
                Setor objeto = new Setor(); //alterar o nome da classe e o construtor

                //setar os atributos do objeto. Cuidar o tipo dos atributos
                objeto.setCodSetor(rs.getInt("codSetor")); //alterar
                objeto.setNomeSetor(rs.getString("nome"));  //alterar

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

        OcorrenciaDAO dao = new OcorrenciaDAO(); //alterar
        dao.adicionar(objeto); //alterar
    }
    
}
