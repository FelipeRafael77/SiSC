package dao;

import banco.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pojo.Estado;

public class DaoEstado {
   
    public static final String SQL_CONSULTAR = "SELECT * FROM ESTADO";
    public static final String SQL_CONSULTAR_ID = "SELECT * FROM ESTADO WHERE ID_ESTADO=?";
    public static final String SQL_ALTERAR = "UPDATE ESTADO SET NOME=? WHERE ID_ESTADO=?";
    public static final String SQL_EXCLUIR = "DELETE FROM ESTADO WHERE ID_ESTADO=?";
    public static final String SQL_COMBOBOX = "SELECT ID_ESTADO,NOME FROM ESTADO ORDER BY NOME";
    
    private Estado estado;
   
    
    public DaoEstado(Estado estado){
       this.estado = estado;
   } 
    
    
   public boolean inserir(){
       try{
       Connection conexao = Banco.getConexao();
       String sql = "INSERT INTO ESTADO (ID_ESTADO,NOME) VALUES (?,?)";
       PreparedStatement ps = conexao.prepareStatement(sql);
       ps.setInt(1, estado.getId());
       ps.setString(2, estado.getNome());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,"Não foi possível inserir os dados!");
           se.printStackTrace();
           return false;
       }
   } 
   
   public boolean alterar(Estado estado){
       try {
           Connection conexao = Banco.getConexao();
           PreparedStatement ps = conexao.prepareStatement(SQL_ALTERAR);
           ps.setString(1, estado.getNome());
           ps.setInt(2, estado.getId());
           ps.execute();
           return true;
   
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível alterar os dados!");
            ex.printStackTrace();
            return false;
        }
   }
   
   public boolean excluir(){
       try{
       Connection conexao = Banco.getConexao();   
       PreparedStatement ps = conexao.prepareStatement(SQL_EXCLUIR);
       ps.setInt(1, estado.getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,"Não foi possível excluir os dados!");
           se.printStackTrace();
           return false;
       }

   }
   
   public Estado consultar(Integer id){
       try{
       Connection conexao = Banco.getConexao();   
       PreparedStatement ps = conexao.prepareStatement(SQL_CONSULTAR_ID);
       ps.setInt(1, id);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           estado.setId(rs.getInt(("ID_ESTADO")));
           estado.setNome(rs.getString("NOME"));
       }
       
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,"Não foi possível consultar!");
           se.printStackTrace();
       }

       return estado;
   }
}
