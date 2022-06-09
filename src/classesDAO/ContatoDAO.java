package classesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import classes.Contato;
import conexao.Conexao;

//Classe de processamento de dados(cadas Classe criada deve ter uma classe DAO. EX: Contato - ContatoDAO)
public class ContatoDAO {
    //Método salvar
    public void Salvar(Contato contato){
        //String do SQL que será usado neste método. 
        String sql = "INSERT INTO contato (nome, idade, dataCadastro) VALUES (?, ?, ?)";
        
        //Declaração das classes connection e PerparedStatement com null. Será utilizado no decorrer do código. 
        Connection conn = null;
        PreparedStatement pstm = null;

        //Try - Catch será realizada uma validação em todos os campos, caso algum passo dê errado cao no catch com o retorno do erro. 
        try{
            conn = Conexao.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            pstm.execute();
            System.out.println("Contato Salvo com Sucesso!!");
        } catch (Exception e) {
            e.printStackTrace();
        //Finally independente de sucesso ou erro o bloco finally é executado para que a conexão seja fechada. 
        } finally {
            //fechar Conexões tanto a PreparedStatement quanto ao Connection
            try {
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }

            } catch (Exception e) {                
                e.printStackTrace();
            }
        }
    }
    //Método Listar contatos
    public List<Contato> listarContatos(){
        String sql = "SELECT * FROM contato";
        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        PreparedStatement pstm = null;
        //retorno da consulta SQL.
        ResultSet rst = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //ExecuteQuery em casos de Select. Em casos de inserção, alteração e exclusão é utilizado .execute();
            rst = pstm.executeQuery();           
            while (rst.next()) {
                Contato contato = new Contato();
                contato.setId(rst.getInt("id"));
                contato.setNome(rst.getString("nome"));
                contato.setIdade(rst.getInt("idade"));
                contato.setDataCadastro(rst.getDate("dataCadastro"));
                contatos.add(contato);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }

            } catch (Exception e) {                
                e.printStackTrace();
            }
        }
        return contatos;
    }
    //Método Alterar Contato
    public void alterarContato(Contato contato){

        String sql = "UPDATE contato  SET nome = ?, idade = ?, dataCadastro = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            pstm.setInt(4, contato.getId());

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fechar Conexões
            try {
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (Exception e) {                
                e.printStackTrace();
            }
        }
    }
    //Método Remover Contato
    public void removerContato(int idRem){
        String sql = "DELETE FROM contato WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setInt(1, idRem);

            pstm.execute();

            System.out.println("Contato deletado com Sucesso!!");
                        
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fechar Conexões
            try {
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (Exception e) {                
                e.printStackTrace();
            }
        }
    }
}
