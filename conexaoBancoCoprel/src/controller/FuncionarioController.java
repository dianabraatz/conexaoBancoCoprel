/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FuncaoDAO;
import dao.FuncionarioDAO;
import dao.SetorDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Funcao;
import model.Funcionario;
import model.Setor;
import view.FuncionarioView;


/**
 *
 * @author edimar
 */
public class FuncionarioController {

    public static void atualizaTabela(JTable tabelaFuncionario) {
        removeLinhasTabela(tabelaFuncionario);
        try {
            DefaultTableModel model = (DefaultTableModel) tabelaFuncionario.getModel();

            FuncionarioDAO dao = new FuncionarioDAO(); //alterar
            List<Funcionario> objetos = dao.selecionar();
            Object linhas[] = new Object[2]; //alterar o índice de acordo com o número de campos exibidos 

            if (!objetos.isEmpty()) {
                for (Funcionario objeto : objetos) {//alterar a classe
                    //alterar definir o que vai em cada linha - 1 linha para cada atributo exibido na tabela
                    linhas[0] = objeto.getNumeroRegistro();  //alterar
                    linhas[1] = objeto.getNome(); //alterar     
                  
                    model.addRow(linhas);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void AtualizaComboBoxSetor(JComboBox cbSetor){
        cbSetor.removeAllItems();
        SetorDAO dao = new SetorDAO();
        for(Setor setor: dao.selecionar()){
            cbSetor.addItem(setor);
        }
    }
    
    public static void AtualizaComboBoxFuncao(JComboBox cbFuncao){
        cbFuncao.removeAllItems();
        FuncaoDAO dao = new FuncaoDAO();
        for(Funcao funcao: dao.selecionar()){
            cbFuncao.addItem(funcao);
        }
    }

    public static void removeLinhasTabela(JTable tabelaFuncionario) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tabelaFuncionario.getModel();
            dtm.setRowCount(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void atualizaCampos(FuncionarioView tela) {
        int linhaSelecionada = tela.tabelaFuncionario.getSelectedRow();

        //alterar obtendo os valores da tabela
        String numeroRegistro = tela.tabelaFuncionario.getValueAt(linhaSelecionada, 0).toString(); //está na coluna 0
        String nome = tela.tabelaFuncionario.getValueAt(linhaSelecionada, 1).toString(); //está na coluna 1

        //alterar setando os valores dos campos
        tela.jtfNumeroRegistro.setText(numeroRegistro);
        tela.jtfNome.setText(nome);

        // habilita/desabilita botões
        tela.jbtAdicionar.setEnabled(false);
        tela.jbtAlterar.setEnabled(true);
        tela.jbtExcluir.setEnabled(true);
    }
    
    	public static Date formataData(String data) throws Exception { 
		if (data == null || data.equals(""))
			return null;
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date)formatter.parse(data);
        } catch (ParseException e) {            
            throw e;
        }
        return date;
	}
    
    public static void adicionar(FuncionarioView tela) {
        //verificando se os campos estão preenchidos
        if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        
        //alterar:: obtendo os valores preenchidos
        Integer numeroRegistro = Integer.parseInt(tela.jtfNumeroRegistro.getText().trim());
        String nome = tela.jtfNome.getText().trim();
        String rg = tela.jtfRG.getText().trim();
        String cpf = tela.jtfCPF.getText().trim();  
        String ctps = tela.jtfCTPS.getText().trim();
        String cnh = tela.jtfCNH.getText().trim();   
        String setor = tela.cbSetor.getSelectedItem().toString();
        String funcao = tela.cbFuncao.getSelectedItem().toString();
        String senha = tela.jtfSenha.getText().trim();
        
        //ERRO: PRECISA CONVERTER DE STRING PRA java.util.Date
        //não esta convertendo a data certa que é colocada na tela
        Date dataNascimento = null;
        Date dataAdmissao = null;
        try {
            dataNascimento = formato.parse(tela.jtfDataAdmissao.getText().trim());
            dataAdmissao = formato.parse(tela.jtfDataAdmissao.getText().trim());
            System.out.println(dataNascimento);
            System.out.println(dataAdmissao);
        } catch (ParseException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //alterar:: criando objeto
        Funcionario funcionario = new Funcionario();
        funcionario.setNumeroRegistro(numeroRegistro);
        funcionario.setNome(nome);
        funcionario.setRg(rg);
        funcionario.setCpf(cpf);
        funcionario.setDataNascimento(dataNascimento);
        funcionario.setCtps(ctps);
        funcionario.setCnh(cnh);
        funcionario.setDataAdmissao(dataAdmissao); 
        //funcionario.setFuncao(funcao);
        //funcionario.setSetor(setor);
        funcionario.setSenha(senha);
        
        Setor objSetor = new Setor();
        objSetor.getCodSetor();
        
        Funcao objFuncao = new Funcao();
        objFuncao.getCodFuncao();
        

        //alterar:: adicionando o objeto no banco de dados
        FuncionarioDAO dao = new FuncionarioDAO();
        boolean resultado = dao.adicionar(funcionario, objSetor, objFuncao);
        if (resultado) {
            atualizaTabela(tela.tabelaFuncionario);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Inserido com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a inserção!");
        }

    }

    public static void alterar(FuncionarioView tela) {
        //verificando se os campos estão preenchidos
        if (!verificarCampos(tela)) {
            return; //algum campo não está preenchido corretamente
        }
        //alterar:: obtendo os valores preenchidos
        Integer numeroRegistro = Integer.parseInt(tela.jtfNumeroRegistro.getText().trim());
        String nome = tela.jtfNome.getText().trim();
        String rg = tela.jtfRG.getText().trim();
        String cpf = tela.jtfCPF.getText().trim();
        String dataNascimento = tela.jtfDataNascimento.getText().trim();
        String ctps = tela.jtfCTPS.getText().trim();
        String cnh = tela.jtfCNH.getText().trim();
        String dataAdmissao = tela.jtfDataAdmissao.getText().trim();
        String funcao = tela.cbFuncao.getSelectedItem().toString();
        String setor = tela.cbSetor.getSelectedItem().toString();
        String senha = tela.jtfSenha.getText().trim();

        //alterar:: criando objeto
        Funcionario funcionario = new Funcionario();
        funcionario.setNumeroRegistro(numeroRegistro);
        funcionario.setNome(nome);
        funcionario.setRg(rg);
        funcionario.setCpf(cpf);
        funcionario.setDataNascimento(dataNascimento);
        funcionario.setCtps(ctps);
        funcionario.setCnh(cnh);
        funcionario.setDataAdmissao(dataAdmissao); 
        //funcionario.setFuncao(funcao);
        //funcionario.setSetor(setor);
        funcionario.setSenha(senha);
        
        //alterar:: alterando o objeto no banco de dados
        FuncionarioDAO dao = new FuncionarioDAO(); //alterar
        boolean resultado = dao.alterar(funcionario); //alterar
        
        if (resultado) {
            atualizaTabela(tela.tabelaFuncionario);
            //limpa os campos e habilita/desabilita os botões
            limparCampos(tela);
            JOptionPane.showMessageDialog(tela, "Alterado com sucesso!"); //não alterar
        } else {
            JOptionPane.showMessageDialog(tela, "Problemas com a alteração!");
        }
    }
    
    public static void excluir(FuncionarioView tela) {
        //verificando se usuário tem certeza
        int result = JOptionPane.showConfirmDialog(tela, "Tem certeza que deseja excluir?", "Exclusão", JOptionPane.YES_NO_OPTION);
        if (result!=JOptionPane.YES_OPTION) {
            return; //não quer excluir
        }
        
        //alterar:: obtendo a chave primária
        Integer numeroRegistro = Integer.parseInt(tela.jtfNumeroRegistro.getText().trim());

        //alterar:: criando objeto
        Funcionario funcionario = new Funcionario();
        funcionario.setNumeroRegistro(numeroRegistro); //na exclusão só precisa setar a chave primária

        //alterar:: excluindo o objeto no banco de dados
        FuncionarioDAO dao = new FuncionarioDAO(); //alterar
        boolean resultado = dao.excluir(funcionario); //alterar
        
        if (resultado) {
            atualizaTabela(tela.tabelaFuncionario);
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
    public static boolean verificarCampos(FuncionarioView tela) {
        //alterar:: conforme os campos obrigatórios
        if (tela.jtfNumeroRegistro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo numero de registro!");
            return false;
        }else if (tela.jtfNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo nome!");
            return false;
        }else if (tela.jtfRG.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo RG!");
            return false;
        }else if (tela.jtfCPF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo CPF!");
            return false;
        }else if (tela.jtfDataNascimento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo data de nascimento!");
            return false;
        }else if (tela.jtfCTPS.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo CTPS!");
            return false;
        }else if (tela.jtfCNH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo CNH!");
            return false;
        }else if (tela.jtfDataAdmissao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo data de admissao!");
            return false;
//        }else if (tela.cbFuncao.getSelectedIndex() == 0) {
//            JOptionPane.showMessageDialog(tela, "Selecione a funcao do funcionario!");
//            return false;
//        }else if (tela.cbSetor.getSelectedIndex() == 0) {
//            JOptionPane.showMessageDialog(tela, "Selecione o setor do funcionario!");
//            return false;
//        }else 
        }else if (tela.jtfSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o campo senha!");
            return false;
        }else
        return true;
    }

    /**
     * Deixa os campos em branco e habilita/desabilita os botões
     *
     * @param tela
     */
    public static void limparCampos(FuncionarioView tela) {
        //alterar:: limpando os campos
        tela.jtfNumeroRegistro.setText("");
        tela.jtfNome.setText("");
        tela.jtfRG.setText("");
        tela.jtfCPF.setText("");
        tela.jtfDataNascimento.setText("");
        tela.jtfCTPS.setText("");
        tela.jtfCNH.setText("");
        tela.jtfDataAdmissao.setText("");
        tela.cbFuncao.setSelectedIndex(0);
        tela.cbSetor.setSelectedIndex(0);
        tela.jtfSenha.setText("");

        //habilitando/desabilitando os botões
        tela.jbtAdicionar.setEnabled(true);
        tela.jbtAlterar.setEnabled(false);
        tela.jbtExcluir.setEnabled(false);
    }
}
