package dao;

import banco.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pojo.Cidade;

public class DaoCidade {
   
    public static final String SQL_CONSULTAR = "SELECT * FROM CIDADE";
    public static final String SQL_CONSULTAR_ID = "SELECT * FROM CIDADE WHERE ID_CIDADE=?";
    public static final String SQL_ALTERAR = "UPDATE CIDADE SET NOME=?,CEP=?,ID_ESTADO=? WHERE ID_CIDADE=?";
    public static final String SQL_EXCLUIR = "DELETE FROM CIDADE WHERE ID_CIDADE=?";
    public static final String SQL_COMBOBOX = "SELECT ID_CIDADE,NOME FROM CIDADE ORDER BY NOME";
    
    
    private Cidade cidade;
   
    
    public DaoCidade(Cidade cidade){
       this.cidade = cidade;
   } 
    
    
   public boolean inserir(){
       try{
       Connection conexao = Banco.getConexao();
       String sql = "INSERT INTO CIDADE(ID_CIDADE,NOME,CEP,ID_ESTADO)VALUES(?,?,?,?)";
       PreparedStatement ps = conexao.prepareStatement(sql);
       ps.setInt(1, cidade.getId());
       ps.setString(2, cidade.getNome());
       ps.setString(3, cidade.getCEP());
       ps.setInt(4, cidade.getEstado().getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,se.getMessage());
           se.printStackTrace();
           return false;
       }
   } 
   
    public boolean alterar(Cidade cidade) {
        try {
           Connection conexao = Banco.getConexao();
           PreparedStatement ps = conexao.prepareStatement(SQL_ALTERAR);
           ps.setString(1, cidade.getNome());
           ps.setString(2, cidade.getCEP());
           ps.setInt(3, cidade.getEstado().getId());
           ps.setInt(4, cidade.getId());
           ps.execute();
           return true;
   
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
     
   public boolean excluir(){
       try{
       Connection conexao = Banco.getConexao();   
       PreparedStatement ps = conexao.prepareStatement(SQL_EXCLUIR);
       ps.setInt(1, cidade.getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,se.getMessage());
           se.printStackTrace();
           return false;
       }
   }
   
   public Cidade consultar(Integer id){
       try{
       Connection conexao = Banco.getConexao();   
       PreparedStatement ps = conexao.prepareStatement(SQL_CONSULTAR_ID);
       ps.setInt(1, id);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           cidade.setId(rs.getInt("ID_CIDADE"));
           cidade.setNome(rs.getString("NOME"));
           cidade.setCEP(rs.getString("CEP"));
           cidade.getEstado().setId(rs.getInt("ID_ESTADO"));
       }
       
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,se.getMessage());
           se.printStackTrace();
       }
        return cidade;
   } 
   
}
