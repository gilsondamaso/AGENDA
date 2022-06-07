package classesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import classes.Contato;
import conexao.Conexao;

public class ContatoDAO {

    public void Salvar(Contato contato){

        String sql = "INSERT INTO contato (nome, idade, dataCadastro) VALUES (?, ?, ?)";

        Connection conn = null;

        PreparedStatement pstm = null;

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

    public List<Contato> listarContatos(){
        String sql = "SELECT * FROM contato";
        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
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
    public void removerContato(int idRem){
        String sql = "DELETE FROM contato WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setInt(1, idRem);

            pstm.execute();

            System.out.println("Contato deletado com Sucesso!!S");
                        
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
