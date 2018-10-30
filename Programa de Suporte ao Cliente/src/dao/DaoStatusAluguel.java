package dao;

import banco.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pojo.StatusAluguel;

public class DaoStatusAluguel {
    public static final String SQL_CONSULTAR = "SELECT * FROM STATUS_ALUGUEL";
    public static final String SQL_CONSULTAR_ID = "SELECT * FROM STATUS_ALUGUEL WHERE IDSTATUS_ALUGUEL=?";
    public static final String SQL_ALTERAR = "UPDATE STATUS_ALUGUEL SET STATUS=? WHERE IDSTATUS_ALUGUEL=?";
    public static final String SQL_EXCLUIR = "DELETE FROM STATUS_ALUGUEL WHERE IDSTATUS_ALUGUEL=?";
    public static final String SQL_COMBOBOX = "SELECT IDSTATUS_ALUGUEL, STATUS FROM STATUS_ALUGUEL";
    
    private StatusAluguel sa;
   
    
    public DaoStatusAluguel(StatusAluguel sa){
       this.sa = sa;
   } 
    
    
   public boolean inserir(){
       try{
       Connection conexao = Banco.getConexao();
       String sql = "INSERT INTO STATUS_ALUGUEL(IDSTATUS_ALUGUEL,STATUS) VALUES(?,?)";
       PreparedStatement ps = conexao.prepareStatement(sql);
       ps.setInt(1, sa.getId());
       ps.setString(2, sa.getStatus());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,"Não foi possível inserir os dados!");
           se.printStackTrace();
           return false;
       }
   } 
   
   public boolean alterar(StatusAluguel sa){
       try{
       Connection conexao = Banco.getConexao();
       PreparedStatement ps = conexao.prepareStatement(SQL_ALTERAR);
       ps.setString(1, sa.getStatus());
       ps.setInt(2, sa.getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,"Não foi possível alterar os dados!");
           se.printStackTrace();
           return false;
       }
   }
   
   public boolean excluir(){
       try{
       Connection conexao = Banco.getConexao();   
       PreparedStatement ps = conexao.prepareStatement(SQL_EXCLUIR);
       ps.setInt(1, sa.getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,"Não foi possível excluir os dados!");
           se.printStackTrace();
           return false;
       }
   }
   
   public StatusAluguel consultar(Integer id){
       try{
       Connection conexao = Banco.getConexao();   
       PreparedStatement ps = conexao.prepareStatement(SQL_CONSULTAR_ID);
       ps.setInt(1, id);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           sa.setId(rs.getInt("IDSTATUS_ALUGUEL"));
           sa.setStatus(rs.getString("STATUS"));
       }
       
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,"Não foi possível consultar!");
           se.printStackTrace();
       }
        return sa;
   
   }
   }
