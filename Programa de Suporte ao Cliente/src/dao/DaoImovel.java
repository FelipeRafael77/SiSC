package dao;

import banco.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pojo.Imovel;

public class DaoImovel {
    public static final String SQL_CONSULTAR = "SELECT * FROM IMOVEL ORDER BY IDIMOVEL";
    public static final String SQL_CONSULTAR_ID = "SELECT * FROM IMOVEL WHERE IDIMOVEL=?";
    public static final String SQL_ALTERAR = "UPDATE IMOVEL SET NOME=?,VALOR_LIQUIDO=?,IDSTATUS_ALUGUEL=? WHERE IDIMOVEL=?";
    public static final String SQL_EXCLUIR = "DELETE FROM IMOVEL WHERE IDIMOVEL=?";
    public static final String SQL_COMBOBOX = "SELECT IDIMOVEL, NOME FROM IMOVEL ORDER BY NOME";
    
    
    private Imovel imovel;
   
    
    public DaoImovel(Imovel imovel){
       this.imovel = imovel;
   } 
    
    
   public boolean inserir(){
       try{
       Connection conexao = Banco.getConexao();
       String sql = "INSERT INTO IMOVEL(IDIMOVEL,NOME,VALOR_LIQUIDO,IDSTATUS_ALUGUEL) VALUES (?,?,?,?)";
       PreparedStatement ps = conexao.prepareStatement(sql);
       ps.setInt(1, imovel.getId());
       ps.setString(2, imovel.getNome());
       ps.setDouble(3, imovel.getValor());
       ps.setInt(4, imovel.getSa().getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,se.getMessage());
           se.printStackTrace();
           return false;
       }
   } 
   
   public boolean alterar(Imovel imovel){
       try{
           Connection conexao = Banco.getConexao();
           PreparedStatement ps = conexao.prepareStatement(SQL_ALTERAR);
           ps.setString(1, imovel.getNome());
           ps.setDouble(2,  imovel.getValor());
           ps.setInt(3, imovel.getSa().getId());
           ps.setInt(4, imovel.getId());
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
       ps.setInt(1, imovel.getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,se.getMessage());
           se.printStackTrace();
           return false;
       }
   }
   
   public Imovel consultar(Integer id){
       try{
       Connection conexao = Banco.getConexao();   
       PreparedStatement ps = conexao.prepareStatement(SQL_CONSULTAR_ID);
       ps.setInt(1, id);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           imovel.setId(rs.getInt("IDIMOVEL"));
           imovel.setNome(rs.getString("NOME"));
           imovel.setValor(rs.getDouble("VALOR_LIQUIDO"));
           imovel.getSa().setId(rs.getInt("IDSTATUS_ALUGUEL"));
       }
       
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null, se.getMessage());
           se.printStackTrace();
       }
        return imovel;
   
   }
   }
