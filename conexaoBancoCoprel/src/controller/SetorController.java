/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SetorDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Setor;
import view.SetorView;


/**
 *
 * @author edimar
 */
public class SetorController {

    public static void atualizaTabela(JTable tabelaSetor) {
        removeLinhasTabela(tabelaSetor);
        try {
            DefaultTableModel model = (DefaultTableModel) tabelaSetor.getModel();

            SetorDAO dao = new SetorDAO(); //alterar
            List<Setor> objetos = dao.selecionar();
            Object linhas[] = new Object[4]; //alterar o índice de acordo com o número de campos exibidos 

            if (!objetos.isEmpty()) {
                for (Setor objeto : objetos) {//alterar a classe
                    //alterar definir o que vai em cada linha - 1 linha para cada atributo exibido na tabela
                    linhas[0] = objeto.getCodSetor();  //alterar
                    linhas[1] = objeto.getNomeSetor(); //alterar
                    linhas[2] = objeto.getPermissao_horaExtra();
                    linhas[3] = objeto.getRepouso_semanalFixo();
                    
                    model.addRow(linhas);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeLinhasTabela(JTable tabelaSetor) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tabelaSetor.getModel();
            dtm.setRowCount(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void atualizaCampos(SetorView tela) {
        int linhaSelecionada = tela.tabelaSetor.getSelectedRow();

        //alterar obtendo os valores da tabela
        String codSetor = tela.tabelaSetor.getValueAt(linhaSelecionada, 0).toString(); //está na coluna 0
        String nomeSetor = tela.tabelaSetor.getValueAt(linhaSelecionada, 1).toString(); //está na coluna 1
        String permissao_horaExtra = tela.tabelaSetor.getValueAt(linhaSelecionada, 2).toString(); //está na coluna 1
        String repouso_semanalFixo = tela.tabelaSetor.getValueAt(linhaSelecionada, 3).toString();


        //alterar setando os valores dos campos
        tela.jtfCodSetor.setText(codSetor);
        tela.jtfNomeSetor.setText(nomeSetor);
        tela.jtfPermissaoHoraExtra.setText(permissao_horaExtra);
        tela.jtfRepousoSemanalFixo.setText(repouso_semanalFixo);

        // habilita/desabilita botões
        tela.jbtAdicionar.setEnabled(false);
        tela.jbtAlterar.setEnabled(true);
        tela.jbtExcluir.setEnabled(true);
    }

    public static void adicionar(SetorView tela) {
        //verificando se os campos estão preenchidos
        if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }

        //alterar:: obtendo os valores preenchidos
        Integer codSetor = Integer.parseInt(tela.jtfCodSetor.getText().trim());
        String nomeSetor = tela.jtfNomeSetor.getText().trim();
        Integer permissao_horaExtra = Integer.parseInt(tela.jtfPermissaoHoraExtra.getText().trim());
        Integer repouso_semanalFixo = Integer.parseInt(tela.jtfRepousoSemanalFixo.getText().trim());

        //alterar:: criando objeto
        Setor setor = new Setor();
        setor.setCodSetor(codSetor);
        setor.setNomeSetor(nomeSetor);
        setor.setPermissao_horaExtra(permissao_horaExtra);
        setor.setRepouso_semanalFixo(repouso_semanalFixo);

        //alterar:: adicionando o objeto no banco de dados
        SetorDAO dao = new SetorDAO();
        boolean resultado = dao.adicionar(setor);
        if (resultado) {
            atualizaTabela(tela.tabelaSetor);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Inserido com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a inserção!");
        }

    }

    public static void alterar(SetorView tela) {
        //verificando se os campos estão preenchidos
        if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }
        
        //alterar:: obtendo os valores preenchidos
        Integer codSetor = Integer.parseInt(tela.jtfCodSetor.getText().trim());
        String nomeSetor = tela.jtfNomeSetor.getText().trim();
        Integer permissao_horaExtra = Integer.parseInt(tela.jtfPermissaoHoraExtra.getText().trim());
        Integer repouso_semanalFixo = Integer.parseInt(tela.jtfRepousoSemanalFixo.getText().trim());

        //alterar:: criando objeto
        Setor setor = new Setor();
        setor.setCodSetor(codSetor);
        setor.setNomeSetor(nomeSetor);
        setor.setPermissao_horaExtra(permissao_horaExtra);
        setor.setRepouso_semanalFixo(repouso_semanalFixo);
        
        //alterar:: alterando o objeto no banco de dados
        SetorDAO dao = new SetorDAO(); //alterar
        boolean resultado = dao.alterar(setor); //alterar
        
        if (resultado) {
            atualizaTabela(tela.tabelaSetor);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Alterado com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a alteração!");
        }
    }
    
    public static void excluir(SetorView tela) {
        //verificando se usuário tem certeza
        int result = JOptionPane.showConfirmDialog(tela, "Tem certeza que deseja excluir?", "Exclusão", JOptionPane.YES_NO_OPTION);
        if (result!=JOptionPane.YES_OPTION) {
            return; //não quer excluir
        }
        
        //alterar:: obtendo a chave primária
        Integer codSetor = Integer.parseInt(tela.jtfCodSetor.getText().trim());

        //alterar:: criando objeto
        Setor setor = new Setor();
        setor.setCodSetor(codSetor); //na exclusão só precisa setar a chave primária

        //alterar:: excluindo o objeto no banco de dados
        SetorDAO dao = new SetorDAO(); //alterar
        boolean resultado = dao.excluir(setor); //alterar
        
        if (resultado) {
            atualizaTabela(tela.tabelaSetor);
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
    public static boolean verificarCampos(SetorView tela) {
        //alterar:: conforme os campos obrigatórios
        if (tela.jtfCodSetor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo codigo!");
            return false;
        }else if (tela.jtfNomeSetor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo nome!");
            return false;
        }else if (tela.jtfPermissaoHoraExtra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo hora extra!");
            return false;
        }else if (tela.jtfRepousoSemanalFixo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo repouso semanal!");
            return false;
        }else
        return true;
    }

    /**
     * Deixa os campos em branco e habilita/desabilita os botões
     *
     * @param tela
     */
    public static void limparCampos(SetorView tela) {
        //alterar:: limpando os campos
        tela.jtfCodSetor.setText("");
        tela.jtfNomeSetor.setText("");
        tela.jtfPermissaoHoraExtra.setText("");
        tela.jtfRepousoSemanalFixo.setText("");

        //habilitando/desabilitando os botões
        tela.jbtAdicionar.setEnabled(true);
        tela.jbtAlterar.setEnabled(false);
        tela.jbtExcluir.setEnabled(false);
    }
}
