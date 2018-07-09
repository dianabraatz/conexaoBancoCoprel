/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Administrador
 */
public class Funcao {
    private Integer codFuncao;
    private Integer nivel_acesso;
    private String nome;

    public Integer getCodFuncao() {
        return codFuncao;
    }

    public void setCodFuncao(Integer codFuncao) {
        this.codFuncao = codFuncao;
    }

    public Integer getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(Integer nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
    

    
    
    

    
    
    
    
}
