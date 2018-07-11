/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author diana
 */
public class Ocorrencia {
    private int codocorrencia;
    private String justificativa;
    private String status;
    private int codponto;

    

    public int getCodocorrencia() {
        return codocorrencia;
    }

    public void setCodocorrencia(int codocorrencia) {
        this.codocorrencia = codocorrencia;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCodponto() {
        return codponto;
    }

    public void setCodponto(int codponto) {
        this.codponto = codponto;
    }
    
    
    
}
