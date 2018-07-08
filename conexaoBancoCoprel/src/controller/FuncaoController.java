/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FuncaoDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Funcao;
import view.FuncaoView;


/**
 *
 * @author edimar
 */
public class FuncaoController {

    public static void atualizaTabela(JTable tabelaFuncao) {
        removeLinhasTabela(tabelaFuncao);
        try {
            DefaultTableModel model = (DefaultTableModel) tabelaFuncao.getModel();

            FuncaoDAO dao = new FuncaoDAO(); //alterar
            List<Funcao> objetos = dao.selecionar();
            Object linhas[] = new Object[3]; //alterar o índice de acordo com o número de campos exibidos 

            if (!objetos.isEmpty()) {
                for (Funcao objeto : objetos) {//alterar a classe
                    //alterar definir o que vai em cada linha - 1 linha para cada atributo exibido na tabela
                    linhas[0] = objeto.getCodFuncao();  //alterar
                    linhas[1] = objeto.getNome(); //alterar
                    linhas[2] = objeto.getNivel_acesso();
                    
                    model.addRow(linhas);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeLinhasTabela(JTable tabelaFuncao) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tabelaFuncao.getModel();
            dtm.setRowCount(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void atualizaCampos(FuncaoView tela) {
        int linhaSelecionada = tela.tabelaFuncao.getSelectedRow();

        //alterar obtendo os valores da tabela
        String codFuncao = tela.tabelaFuncao.getValueAt(linhaSelecionada, 0).toString(); //está na coluna 0
        String nome = tela.tabelaFuncao.getValueAt(linhaSelecionada, 1).toString(); //está na coluna 1
        String nivel_acesso = tela.tabelaFuncao.getValueAt(linhaSelecionada, 2).toString(); //está na coluna 1


        //alterar setando os valores dos campos
        tela.jtfCodFuncao.setText(codFuncao);
        tela.jtfNome.setText(nome);
        tela.jtfNivelAcesso.setText(nivel_acesso);

        // habilita/desabilita botões
        tela.jbtAdicionar.setEnabled(false);
        tela.jbtAlterar.setEnabled(true);
        tela.jbtExcluir.setEnabled(true);
    }

    public static void adicionar(FuncaoView tela) {
        //verificando se os campos estão preenchidos
        if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }

        //alterar:: obtendo os valores preenchidos
        String nome = tela.jtfNome.getText().trim();
        Integer nivel_acesso = Integer.parseInt(tela.jtfNivelAcesso.getText().trim());    

        //alterar:: criando objeto
        Funcao funcao = new Funcao();
        funcao.setNome(nome);
        funcao.setNivel_acesso(nivel_acesso);        

        //alterar:: adicionando o objeto no banco de dados
        FuncaoDAO dao = new FuncaoDAO();
        boolean resultado = dao.adicionar(funcao);
        if (resultado) {
            atualizaTabela(tela.tabelaFuncao);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Inserido com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a inserção!");
        }

    }

    public static void alterar(FuncaoView tela) {
        //verificando se os campos estão preenchidos
        if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }
        
        Integer codFuncao = Integer.parseInt(tela.jtfCodFuncao.getText().trim());
        String nome = tela.jtfNome.getText().trim();
        Integer nivel_acesso = Integer.parseInt(tela.jtfNivelAcesso.getText().trim());    

        //alterar:: criando objeto
        Funcao funcao = new Funcao();
        funcao.setCodFuncao(codFuncao);
        funcao.setNome(nome);
        funcao.setNivel_acesso(nivel_acesso);   
        
        //alterar:: alterando o objeto no banco de dados
        FuncaoDAO dao = new FuncaoDAO(); //alterar
        boolean resultado = dao.alterar(funcao); //alterar
        
        if (resultado) {
            atualizaTabela(tela.tabelaFuncao);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Alterado com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a alteração!");
        }
    }
    
    public static void excluir(FuncaoView tela) {
        //verificando se usuário tem certeza
        int result = JOptionPane.showConfirmDialog(tela, "Tem certeza que deseja excluir?", "Exclusão", JOptionPane.YES_NO_OPTION);
        if (result!=JOptionPane.YES_OPTION) {
            return; //não quer excluir
        }
        
        //alterar:: obtendo a chave primária
        Integer codFuncao = Integer.parseInt(tela.jtfCodFuncao.getText().trim());

        //alterar:: criando objeto
        Funcao funcao = new Funcao();
        funcao.setCodFuncao(codFuncao); //na exclusão só precisa setar a chave primária

        //alterar:: excluindo o objeto no banco de dados
        FuncaoDAO dao = new FuncaoDAO(); //alterar
        boolean resultado = dao.excluir(funcao); //alterar
        
        if (resultado) {
            atualizaTabela(tela.tabelaFuncao);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Excluído com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a exclusão!");
        }
    }

    /**
     * Verifica se os campos estão preenchidos corretamente
     *
     * @param tela
     * @return true se todos os campos estão preenchidos corretamente, false se
     * algum campo não está preenchido corretamente
     */
    public static boolean verificarCampos(FuncaoView tela) {
        //alterar:: conforme os campos obrigatórios
        if (tela.jtfNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo nome!");
            return false;
        }else if (tela.jtfNivelAcesso.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo nivel de acesso!");
            return false;
        }else
        return true;
    }

    /**
     * Deixa os campos em branco e habilita/desabilita os botões
     *
     * @param tela
     */
    public static void limparCampos(FuncaoView tela) {
        //alterar:: limpando os campos
        tela.jtfCodFuncao.setText("");
        tela.jtfNome.setText("");
        tela.jtfNivelAcesso.setText("");

        //habilitando/desabilitando os botões
        tela.jbtAdicionar.setEnabled(true);
        tela.jbtAlterar.setEnabled(false);
        tela.jbtExcluir.setEnabled(false);
    }
}
