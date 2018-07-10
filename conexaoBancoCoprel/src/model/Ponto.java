/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Ponto {
    private Integer codigo;
    private Date dh_inicio;
    private Date dh_fim;
    private Integer cod_funcionario;
    private String nomeFuncionario;

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Date getDh_inicio() {
        return dh_inicio;
    }

    public Date getDh_fim() {
        return dh_fim;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setDh_inicio(Date dh_inicio) {
        this.dh_inicio = dh_inicio;
    }

     public void setDh_inicio(String dh_inicio) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            setDh_inicio(format.parse(dh_inicio));
        } catch (ParseException ex) {
            Logger.getLogger(Ponto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void setDh_fim(Date dh_fim) {
        this.dh_fim = dh_fim;
    }

    
     public void setDh_fim(String dh_fim) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            setDh_fim(format.parse(dh_fim));
        } catch (ParseException ex) {
            Logger.getLogger(Ponto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public Integer getCod_funcionario() {
        return cod_funcionario;
    }

    public void setCod_funcionario(Integer cod_funcionario) {
        this.cod_funcionario = cod_funcionario;
    }
    
    
    
    



    
    
    

    
    
    
    
}
