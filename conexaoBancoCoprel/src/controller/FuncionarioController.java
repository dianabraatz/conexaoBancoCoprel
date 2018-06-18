/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FuncionarioDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;
import view.AdicionarFuncionario;

/**
 *
 * @author edimar
 */
public class FuncionarioController {

    public static void atualizaTabela(JTable tabela) {
        removeLinhasTabela(tabela);
        try {
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();

            FuncionarioDAO dao = new FuncionarioDAO(); //alterar
            List<Funcionario> objetos = dao.selecionar();
            Object linhas[] = new Object[2]; //alterar o índice de acordo com o número de campos exibidos 

            if (!objetos.isEmpty()) {
                for (Funcionario objeto : objetos) {//alterar a classe
                    //alterar definir o que vai em cada linha - 1 linha para cada atributo exibido na tabela
                    linhas[0] = objeto.getId();  //alterar
                    linhas[1] = objeto.getNome(); //alterar
                    linhas[2] = objeto.getEmail();  //alterar
                    linhas[3] = objeto.getCpf();
                    
                    model.addRow(linhas);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeLinhasTabela(JTable tabela) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
            dtm.setRowCount(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void atualizaCampos(AdicionarFuncionario tela) {
        /*int linhaSelecionada = tela.tabela.getSelectedRow();

        //alterar obtendo os valores da tabela
        String codigo = tela.tabela.getValueAt(linhaSelecionada, 0).toString(); //está na coluna 0
        String nome = tela.tabela.getValueAt(linhaSelecionada, 1).toString(); //está na coluna 1
        String email = tela.tabela.getValueAt(linhaSelecionada, 2).toString(); //está na coluna 0
        Long cpf = Long.parseLong(tela.tabela.getValueAt(linhaSelecionada, 3).toString()); //está na coluna 1

        //alterar setando os valores dos campos
        tela.jtfCodigo.setText(codigo);
        tela.jtfNome.setText(nome);
        tela.jtfEmail.setText(codigo);
        tela.jtfCPF.setText(nome);

        // habilita/desabilita botões
        tela.jbtAdicionar.setEnabled(false);
        tela.jbtAlterar.setEnabled(true);
        tela.jbtExcluir.setEnabled(true); */
    }

    public static void adicionar(AdicionarFuncionario tela) {
        //verificando se os campos estão preenchidos
        /*if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }

        //alterar:: obtendo os valores preenchidos
        String nome = tela.jtfNome.getText().trim();
        String email = tela.jtfEmail.getText().trim();
        Long cpf = Long.parseLong(tela.jtfCPF.getText().trim());
        

        //alterar:: criando objeto
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setEmail(email);
        pessoa.setCpf(cpf);

        //alterar:: adicionando o objeto no banco de dados
        FuncionarioDAO dao = new FuncionarioDAO();
        boolean resultado = dao.adicionar(funcionario);
        if (resultado) {
            atualizaTabela(tela.tabela);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Inserido com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a inserção!");
        } */

    }

    public static void alterar(AdicionarFuncionario tela) {
        //verificando se os campos estão preenchidos
        /*if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }
        //alterar:: obtendo os valores preenchidos
        Integer id = Integer.parseInt(tela.jtfCodigo.getText().trim());
        String nome = tela.jtfNome.getText().trim();
        String email = tela.jtfEmail.getText().trim();
        Long cpf = Long.parseLong(tela.jtfCPF.getText().trim());

        //alterar:: criando objeto
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id); //na alteração tem que setar o código
        pessoa.setNome(nome);

        //alterar:: alterando o objeto no banco de dados
        PessoaDAO dao = new PessoaDAO(); //alterar
        boolean resultado = dao.alterar(pessoa); //alterar
        
        if (resultado) {
            atualizaTabela(tela.tabela);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Alterado com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a alteração!");
        }*/
    }
    
    public static void excluir(AdicionarFuncionario tela) {
        //verificando se usuário tem certeza
        int result = JOptionPane.showConfirmDialog(tela, "Tem certeza que deseja excluir?", "Exclusão", JOptionPane.YES_NO_OPTION);
        if (result!=JOptionPane.YES_OPTION) {
            return; //não quer excluir
        }
        
        //alterar:: obtendo a chave primária
       // Integer id = Integer.parseInt(tela.jtfCodigo.getText().trim());

        //alterar:: criando objeto
        Funcionario funcionario = new Funcionario();
        //funcionario.setId(id); //na exclusão só precisa setar a chave primária

        //alterar:: excluindo o objeto no banco de dados
        FuncionarioDAO dao = new FuncionarioDAO(); //alterar
        boolean resultado = dao.excluir(funcionario); //alterar
        
        if (resultado) {
           // atualizaTabela(tela.tabela);
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
    /*public static boolean verificarCampos(AdicionarFuncionario tela) {
        //alterar:: conforme os campos obrigatórios
        if (tela.jtfNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo nome!");
            return false;
        }
        return true;
    }*/

    /**
     * Deixa os campos em branco e habilita/desabilita os botões
     *
     * @param tela
     */
    public static void limparCampos(AdicionarFuncionario tela) {
        //alterar:: limpando os campos
        /*tela.jtfCodigo.setText("");
        tela.jtfNome.setText("");
        tela.jtfEmail.setText("");
        tela.jtfCPF.setText("");

        //habilitando/desabilitando os botões
        tela.jbtAdicionar.setEnabled(true);
        tela.jbtAlterar.setEnabled(false);
        tela.jbtExcluir.setEnabled(false);*/
    }
}
