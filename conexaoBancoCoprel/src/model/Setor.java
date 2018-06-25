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
public class Setor {
    private Integer codSetor;
    private Integer permissao_horaExtra;
    private Integer repouso_semanalFixo;
    private String nomeSetor;

    public Integer getCodSetor() {
        return codSetor;
    }

    public void setCodSetor(Integer codSetor) {
        this.codSetor = codSetor;
    }

    public Integer getPermissao_horaExtra() {
        return permissao_horaExtra;
    }

    public void setPermissao_horaExtra(Integer permissao_horaExtra) {
        this.permissao_horaExtra = permissao_horaExtra;
    }

    public Integer getRepouso_semanalFixo() {
        return repouso_semanalFixo;
    }

    public void setRepouso_semanalFixo(Integer repouso_semanalFixo) {
        this.repouso_semanalFixo = repouso_semanalFixo;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }
    
        
    
    
}
