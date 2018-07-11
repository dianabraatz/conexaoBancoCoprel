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
            String sql = " UPDATE ocorrencia SET justificativa = ?  WHERE codocorrencia = ?;";

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);

            //definindo as interrogações (uma linha para cada ? do SQL)
            pstmt.setString(1, objeto.getJustificativa()); 
            pstmt.setInt(2, objeto.getCodocorrencia());

            pstmt.executeUpdate(); //executando
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean excluir(Ocorrencia objeto) {
        try {
            String sql = " DELETE FROM ocorrencia WHERE codocorrencia = ?;"; //alterar a tabela e a chave primária no WHERE

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, objeto.getCodocorrencia()); //alterar conforme a chave primária

            if (pstmt.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Ocorrencia> selecionar() {
        String sql = "SELECT dh_inicio, dh_fim, numregistro, codigo FROM ponto WHERE numregistro  "; //alterar tabela e atributos

        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Ocorrencia> lista = new ArrayList<>();//alterar a classe 

            while (rs.next()) {
                Ocorrencia objeto = new Ocorrencia(); //alterar o nome da classe e o construtor

                //setar os atributos do objeto. Cuidar o tipo dos atributos
                objeto.setCodocorrencia(rs.getInt("codocorrencia")); //alterar
                objeto.setJustificativa(rs.getString("justificativa"));  //alterar

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
        Ocorrencia objeto = new Ocorrencia(); //alterar
        objeto.setJustificativa("Diana"); //alterar

        OcorrenciaDAO dao = new OcorrenciaDAO(); //alterar
        dao.adicionar(objeto); //alterar
    }
    
}
