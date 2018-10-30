package dao;

import banco.Banco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pojo.ContratoAluguel;

public class DaoContratoAluguel {
    public static final String SQL_CONSULTAR = "SELECT * FROM CONTRATO_ALUGUEL";
    public static final String SQL_CONSULTAR_ID = "SELECT * FROM CONTRATO_ALUGUEL WHERE IDCONTRATO_ALUGUEL=?";
    public static final String SQL_ALTERAR = "UPDATE CONTRATO_ALUGUEL SET N_CONTRATO=?,VALOR_ALUGUEL=?,DATA_INICIO=?,DATA_TERMINO=?,ID_PROPRIETARIO=?,ID_CLIENTE=?,IDIMOVEL=?,IDSTATUS_ALUGUEL=? WHERE IDCONTRATO_ALUGUEL=?";
    public static final String SQL_EXCLUIR = "DELETE FROM CONTRATO_ALUGUEL WHERE IDCONTRATO_ALUGUEL=?";
    
    private ContratoAluguel cl;
   
    
    public DaoContratoAluguel(ContratoAluguel cl){
       this.cl = cl;
   } 
    
    
   public boolean inserir(){
       try{
       Connection conexao = Banco.getConexao();
       String sql = "INSERT INTO CONTRATO_ALUGUEL(IDCONTRATO_ALUGUEL,N_CONTRATO,VALOR_ALUGUEL,DATA_INICIO,DATA_TERMINO,ID_PROPRIETARIO,ID_CLIENTE,IDIMOVEL,IDSTATUS_ALUGUEL) VALUES (?,?,?,?,?,?,?,?,?)";
       PreparedStatement ps = conexao.prepareStatement(sql);
       ps.setInt(1, cl.getId());
       ps.setString(2, cl.getNumero());
       ps.setDouble(3, cl.getValor());
       ps.setDate(4, (Date) cl.getDi());
       ps.setDate(5, (Date) cl.getDt());
       ps.setInt(6, cl.getProprietario().getId());
       ps.setInt(7, cl.getCliente().getId());
       ps.setInt(8, cl.getImovel().getId());
       ps.setInt(9, cl.getSa().getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,se.getMessage());
           se.printStackTrace();
           return false;
       }
   } 
   
   public boolean alterar(ContratoAluguel cl){
       try{
           Connection conexao = Banco.getConexao();
           PreparedStatement ps = conexao.prepareStatement(SQL_ALTERAR);
           ps.setString(1, cl.getNumero());
           ps.setDouble(2, cl.getValor());
           ps.setDate(3, (Date) cl.getDi());
           ps.setDate(4, (Date) cl.getDt());
           ps.setInt(5, cl.getProprietario().getId());
           ps.setInt(6, cl.getCliente().getId());
           ps.setInt(7, cl.getImovel().getId());
           ps.setInt(8, cl.getSa().getId());
           ps.setInt(9, cl.getId());
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
       ps.setInt(1, cl.getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,se.getMessage());
           se.printStackTrace();
           return false;
       }
   }
   
   public ContratoAluguel consultar(Integer id){
       try{
       Connection conexao = Banco.getConexao();   
       PreparedStatement ps = conexao.prepareStatement(SQL_CONSULTAR_ID);
       ps.setInt(1, id);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           cl.setId(rs.getInt("IDCONTRATO_ALUGUEL"));
           cl.setNumero(rs.getString("N_CONTRATO"));
           cl.setValor(rs.getDouble("VALOR_ALUGUEL"));
           cl.setDi(rs.getDate("DATA_INICIO"));
           cl.setDt(rs.getDate("DATA_TERMINO"));
           cl.getProprietario().setId(rs.getInt("ID_PROPRIETARIO"));
           cl.getCliente().setId(rs.getInt("ID_CLIENTE"));
           cl.getImovel().setId(rs.getInt("IDIMOVEL"));
           cl.getSa().setId(rs.getInt("IDSTATUS_ALUGUEL"));
       }
       
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,se.getMessage());
           se.printStackTrace();
       }
        return cl;
   
   }
   }

