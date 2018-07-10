/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class PontoDAO {
    
    public boolean abrirPonto(int objeto) { //alterar a classe do parâmetro
        try {
            String sql = "INSERT INTO ponto (dh_inicio, numregistro) VALUES (CURRENT_TIMESTAMP, ?)"; //alterar a tabela, os atributos e o número de interrogações, conforme o número de atributos

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, objeto);             
            pstmt.executeUpdate();
            
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean fecharPonto(int objeto) {
        try {
            String sql = "UPDATE ponto SET dh_fim = CURRENT_TIMESTAMP WHERE dh_fim is null AND DATE(dh_inicio) = DATE(NOW()) AND numregistro = ?;"; //alterar tabela, atributos e chave primária - where a chave primaria

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, objeto);
            pstmt.executeUpdate();
            
            return true;
            
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    
    
/*
    public boolean excluir(Ponto objeto) {
        try {
            String sql = " DELETE FROM ponto WHERE codigo = ? "; //alterar a tabela e a chave primária no WHERE

            PreparedStatement pstmt = Conexao.getConexao().prepareStatement(sql);
            pstmt.setInt(1, objeto.getCodigo()); //alterar conforme a chave primária

            if (pstmt.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Ponto> selecionar() {
        String sql = "SELECT codigo, dh_inicio, dh_fim FROM ponto ORDER BY dh_inicio DESC"; //alterar tabela e atributos

        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Ponto> lista = new ArrayList<>();//alterar a classe 

            while (rs.next()) {
                Ponto objeto = new Ponto(); //alterar o nome da classe e o construtor

                //setar os atributos do objeto. Cuidar o tipo dos atributos
                objeto.setCodigo(rs.getInt("codigo")); //alterar
                objeto.setDh_inicio(rs.getDate("Dh_inicio"));  //alterar
                objeto.setDh_fim(rs.getDate("Dh_fim"));  //alterar

                lista.add(objeto);
            }
            stmt.close();
            return lista;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

*/
    public static void main(String[] args) {
        
    }
    
}
