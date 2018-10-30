package dao;

import banco.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pojo.Proprietario;

public class DaoProprietario {
    public static final String SQL_CONSULTAR = "SELECT * FROM PROPRIETARIO";
    public static final String SQL_CONSULTAR_ID = "SELECT * FROM PROPRIETARIO WHERE ID_PROPRIETARIO=?";
    public static final String SQL_ALTERAR = "UPDATE PROPRIETARIO SET NOME=?, ENDERECO=?, COMPLEMENTO=?, RG=?, CPF=?, TELEFONE=?, ID_CIDADE=? WHERE ID_PROPRIETARIO=?";
    public static final String SQL_EXCLUIR = "DELETE FROM PROPRIETARIO WHERE ID_PROPRIETARIO=?";
    public static final String SQL_COMBOBOX = "SELECT ID_PROPRIETARIO, NOME FROM PROPRIETARIO ORDER BY NOME";
    
    
    private Proprietario proprietario;
   
    
    public DaoProprietario(Proprietario proprietario){
       this.proprietario = proprietario;
   } 
    
    
   public boolean inserir(){
       try{
       Connection conexao = Banco.getConexao();
       String sql = "INSERT INTO PROPRIETARIO (ID_PROPRIETARIO,NOME,ENDERECO,COMPLEMENTO,RG,CPF,TELEFONE,ID_CIDADE) VALUES (?,?,?,?,?,?,?,?)";
       PreparedStatement ps = conexao.prepareStatement(sql);
       ps.setInt(1, proprietario.getId());
       ps.setString(2, proprietario.getNome());
       ps.setString(3, proprietario.getEndereco());
       ps.setString(4, proprietario.getComplemento());
       ps.setString(5, proprietario.getRg());
       ps.setString(6, proprietario.getCpf());
       ps.setString(7, proprietario.getTelefone());
       ps.setInt(8, proprietario.getCidade().getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,"Não foi possível inserir os dados!");
           se.printStackTrace();
           return false;
       }
   } 
   
   public boolean alterar(Proprietario proprietario){
       try{
           Connection conexao = Banco.getConexao();
           PreparedStatement ps = conexao.prepareStatement(SQL_ALTERAR);
           ps.setString(1, proprietario.getNome());
           ps.setString(2, proprietario.getEndereco());
           ps.setString(3, proprietario.getComplemento());
           ps.setString(4, proprietario.getRg());
           ps.setString(5, proprietario.getCpf());
           ps.setString(6, proprietario.getTelefone());
           ps.setInt(7, proprietario.getCidade().getId());
           ps.setInt(8, proprietario.getId());
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
       ps.setInt(1, proprietario.getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,"Não foi possível excluir os dados!");
           se.printStackTrace();
           return false;
       }
   }
   
   public Proprietario consultar(Integer id){
       try{
       Connection conexao = Banco.getConexao();   
       PreparedStatement ps = conexao.prepareStatement(SQL_CONSULTAR_ID);
       ps.setInt(1, id);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           proprietario.setId(rs.getInt("ID_PROPRIETARIO"));
           proprietario.setNome(rs.getString("NOME"));
           proprietario.setEndereco(rs.getString("ENDERECO"));
           proprietario.setComplemento(rs.getString("COMPLEMENTO"));
           proprietario.setRg(rs.getString("RG"));
           proprietario.setCpf(rs.getString("CPF"));
           proprietario.setTelefone(rs.getString("TELEFONE"));
           proprietario.getCidade().setId(rs.getInt("ID_CIDADE"));
       }
       
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,"Não foi possível consultar!");
           se.printStackTrace();
       }
        return proprietario;
   
   }
}
