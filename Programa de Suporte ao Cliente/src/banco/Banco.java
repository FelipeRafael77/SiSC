package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Banco {

    public static Connection getConexao() {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection conexao = DriverManager.getConnection(
                    "jdbc:firebirdsql://127.0.0.1:3050/E:/Area de Trabalho/Trabalho Multidisciplinar/IMOBILIARIAATUALIZADO_1.0.FDB", "SYSDBA", "masterkey");
            return conexao;
        }
        catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, "Há um driver com problema, verifique-o!");
            return null;
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Banco de Dados" + "sem conexão!");
            se.printStackTrace();
            return null;
        }

    }
    
    public static List<String[]> consultaBanco(String sql) {
        List<String[]> retorno = new ArrayList();
        try{
        Statement st = getConexao().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
           String[] linha = new String[rs.getMetaData().getColumnCount()];
           for(int coluna = 1; coluna <= rs.getMetaData().getColumnCount(); coluna++){
              linha[coluna -1] = rs.getString(coluna);
           }
           
           retorno.add(linha);
        }
        return retorno;
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, "Erro na consulta!");
        e.printStackTrace();
        return null;
    }
  }
}
