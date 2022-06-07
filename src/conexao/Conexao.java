package conexao;
import java.sql.*;

public class Conexao {
    private static final String user = "gvdjrteste";//usuário do banco de dados
    private static final String pass = "gvdjrteste";//password do banco(se não tiver informar vazio)
    private static final String url = "jdbc:mysql://localhost:3306/agenda";

    public static Connection createConnectionToMySQL() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection connection = DriverManager.getConnection(url, user, pass );
        return connection;
    }

    public static void main(String[] args) throws Exception {
        Connection conn = createConnectionToMySQL();

        if(conn!=null){
            System.out.println("CONEXAO ESTABELECIDA");
            conn.close();
        }
    }
}
