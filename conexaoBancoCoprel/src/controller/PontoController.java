
package controller;

import dao.PontoDAO;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Funcionario;
import model.Ponto;
import view.AdministradorView;


/**
 *
 * @author edimar
 */
public class PontoController {


    public static void abrirponto(Funcionario objeto) {
            
            /*É PRECISO DECIDIR COMO VAI SER A ABERTURA/CADASTRO DE PONTO:
                CADA PONTO IRÁ TER UM CÓDIGO: CASO ELE SEJA ABERTO PELA MANHÃ/TARDE(INSERT dh_inicio), NO FIM DA MANHÃ/TARDE O PONTO DE MESMO CODIGO 
                                                                                             TERÁ QUE SER ALTERADO E SERÁ ADICIONADO dh_final(UPDATE)
             testa isso no bd, não consegui explicar direito
                --ponto inicio
                INSERT INTO ponto(dh_inicio, numregistro)VALUES(CURRENT_TIMESTAMP, 10001);
        
                --ponto fim(caso não houver a verificação no inicio do turno/ gera um novo codigo);
                INSERT INTO ponto(dh_fim, numregistro)VALUES(CURRENT_TIMESTAMP, 10001);
                
                --inserir ponto fim se dh_inicio ja tiver sido inserido
                UPDATE ponto SET dh_fim = CURRENT_TIMESTAMP WHERE dh_fim = NULL AND DATE(dh_inicio) = DATE(NOW()) AND numregistro = 10001;
            
                --> precisamos fazer uma verificação no banco ou aqui, não sei como pode ser
        
            */
    } 

}
