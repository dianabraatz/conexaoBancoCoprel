/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OcorrenciaDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Ocorrencia;
import view.OcorrenciaView;

public class OcorrenciaController {

    public static void atualizaTabela(JTable tabelaOcorrencia) {
        removeLinhasTabela(tabelaOcorrencia);
        try {
            DefaultTableModel model = (DefaultTableModel) tabelaOcorrencia.getModel();

            OcorrenciaDAO dao = new OcorrenciaDAO(); //alterar
            List<Ocorrencia> objetos = dao.selecionar();
            Object colunas[] = new Object[1]; //alterar o índice de acordo com o número de campos exibidos 
            
            if (!objetos.isEmpty()) {
                for (Ocorrencia objeto : objetos) {//alterar a classe
                    //alterar definir o que vai em cada linha - 1 linha para cada atributo exibido na tabela
                    colunas[0] = objeto.getCodocorrencia();  //alterar
                    //colunas[1] = objeto.getNomeSetor(); //alterar
                    //colunas[0] = objeto.getCodSetor();  //alterar
                    //colunas[1] = objeto.getNomeSetor(); 
                    
                    model.addRow(colunas);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeLinhasTabela(JTable tabelaOcorrencia) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tabelaOcorrencia.getModel();
            dtm.setRowCount(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void atualizaCampos(OcorrenciaView tela) {
        int linhaSelecionada = tela.tabelaOcorrencia.getSelectedRow();

        //alterar obtendo os valores da tabela
        String codOcorrencia = tela.tabelaOcorrencia.getValueAt(linhaSelecionada, 0).toString(); //está na coluna 0
        //String nomeSetor = tela.tabelaOcorrencia.getValueAt(linhaSelecionada, 1).toString(); //está na coluna 1


        //alterar setando os valores dos campos
        tela.jtfcodocorrencia.setText(codOcorrencia);
        //tela.jtfNomeSetor.setText(nomeSetor);

        // habilita/desabilita botões
        tela.jbtAdicionar.setEnabled(false);
        tela.jbtAlterar.setEnabled(true);
        tela.jbtExcluir.setEnabled(true);
    }

    public static void adicionar(OcorrenciaView tela) {
        //verificando se os campos estão preenchidos
        if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }

        //alterar:: obtendo os valores preenchidos
        //String nomeSetor = tela.jtfNomeSetor.getText().trim();

        //alterar:: criando objeto
        Ocorrencia ocorrencia = new Ocorrencia;
        ocorrencia.setNomeSetor(nomeSetor);

        //alterar:: adicionando o objeto no banco de dados
        OcorrenciaDAO dao = new OcorrenciaDAO();
        boolean resultado = dao.adicionar(ocorrencia);
        if (resultado) {
            atualizaTabela(tela.tabelaOcorrencia);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Inserido com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a inserção!");
        }

    }

    public static void alterar(OcorrenciaView tela) {
        //verificando se os campos estão preenchidos
        if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }
        
        //alterar:: obtendo os valores preenchidos
        Integer codocorrencia = Integer.parseInt(tela.jtfcodocorrencia.getText().trim());
        String numeroregistro = tela.jtfnumeroregistro.getText().trim();

        //alterar:: criando objeto
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setCodocorrencia(codocorrencia);
        //ocorrencia.setJustificativa(numeroregistro);
        
        //alterar:: alterando o objeto no banco de dados
        OcorrenciaDAO dao = new OcorrenciaDAO(); //alterar
        boolean resultado = dao.alterar(ocorrencia); //alterar
        
        if (resultado) {
            atualizaTabela(tela.tabelaOcorrencia);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Alterado com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a alteração!");
        }
    }
    
    public static void excluir(OcorrenciaView tela) {
        //verificando se usuário tem certeza
        int result = JOptionPane.showConfirmDialog(tela, "Tem certeza que deseja excluir?", "Exclusão", JOptionPane.YES_NO_OPTION);
        if (result!=JOptionPane.YES_OPTION) {
            return;
        }
        
        //alterar:: obtendo a chave primária
        Integer codocorrencia = Integer.parseInt(tela.jtfcodocorrencia.getText().trim());

        //alterar:: criando objeto
        Ocorrencia ocorrencia= new Ocorrencia();
        ocorrencia.setCodocorrencia(codocorrencia); //na exclusão só precisa setar a chave primária

        //alterar:: excluindo o objeto no banco de dados
        OcorrenciaDAO dao = new OcorrenciaDAO(); //alterar
        boolean resultado = dao.excluir(ocorrencia); //alterar
        
        if (resultado) {
            atualizaTabela(tela.tabelaOcorrencia);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Excluído com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a exclusão, tente novamente!");
        }
    }

    /**
     * Verifica se os campos estão preenchidos corretamente
     *
     * @param tela
     * @return true se todos os campos estão preenchidos corretamente, false se
     * algum campo não está preenchido corretamente
     */
    public static boolean verificarCampos(OcorrenciaView tela) {
        //alterar:: conforme os campos obrigatórios
        if (tela.jtfcodocorrencia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo nome!");
            return false;
        }else
        return true;
    }

    /**
     * Deixa os campos em branco e habilita/desabilita os botões
     *
     * @param tela
     */
    public static void limparCampos(OcorrenciaView tela) {
        //alterar:: limpando os campos
        tela.jtfnumeroregistro.setText("");
        tela.jtfcodocorrencia.setText("");

        //habilitando/desabilitando os botões
        tela.jbtAdicionar.setEnabled(true);
        tela.jbtAlterar.setEnabled(false);
        tela.jbtExcluir.setEnabled(false);
        
    }
}
