package dao;

import banco.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pojo.Cidade;
import pojo.Cliente;

public class DaoCliente {
    public static final String SQL_CONSULTAR = "SELECT * FROM CLIENTE";
    public static final String SQL_CONSULTAR_ID = "SELECT * FROM CLIENTE WHERE ID_CLIENTE=?";
    public static final String SQL_ALTERAR = "UPDATE CLIENTE SET NOME=?, ENDERECO=?,COMPLEMENTO=?,RG=?,CPF=?,TELEFONE=?, ID_CIDADE=? WHERE ID_CLIENTE=?";
    public static final String SQL_EXCLUIR = "DELETE FROM CLIENTE WHERE ID_CLIENTE=?";
    public static final String SQL_COMBOBOX = "SELECT ID_CLIENTE, NOME FROM CLIENTE ORDER BY NOME";
    
    
    private Cliente cliente;
   
    
    public DaoCliente(Cliente cliente){
       this.cliente = cliente;
   } 
    
    
   public boolean inserir(){
       try{
       Connection conexao = Banco.getConexao();
       String sql = "INSERT INTO CLIENTE(ID_CLIENTE,NOME,ENDERECO,COMPLEMENTO,RG,CPF,TELEFONE,ID_CIDADE)VALUES(?,?,?,?,?,?,?,?)";
       PreparedStatement ps = conexao.prepareStatement(sql);
       ps.setInt(1, cliente.getId());
       ps.setString(2, cliente.getNome());
       ps.setString(3, cliente.getEndereco());
       ps.setString(4, cliente.getComplemento());
       ps.setString(5, cliente.getRg());
       ps.setString(6, cliente.getCpf());
       ps.setString(7, cliente.getTelefone());
       ps.setInt(8, cliente.getCidade().getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null, se.getMessage());
           se.printStackTrace();
           return false;
       }
   } 
   
   public boolean alterar(Cliente cliente){
          try {
           Connection conexao = Banco.getConexao();
           PreparedStatement ps = conexao.prepareStatement(SQL_ALTERAR);
           ps.setString(1, cliente.getNome());
           ps.setString(2, cliente.getEndereco());
           ps.setString(3, cliente.getComplemento());
           ps.setString(4, cliente.getRg());
           ps.setString(5, cliente.getCpf());
           ps.setString(6, cliente.getTelefone());
           ps.setInt(7, cliente.getCidade().getId());
           ps.setInt(8, cliente.getId());
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
       ps.setInt(1, cliente.getId());
       ps.execute();
       return true;
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,se.getMessage());
           se.printStackTrace();
           return false;
       }

   }
   
   public Cliente consultar(Integer id){
       try{
       Connection conexao = Banco.getConexao();   
       PreparedStatement ps = conexao.prepareStatement(SQL_CONSULTAR_ID);
       ps.setInt(1, id);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           cliente.setId(rs.getInt("ID_CLIENTE"));
           cliente.setNome(rs.getString("NOME"));
           cliente.setEndereco(rs.getString("ENDERECO"));
           cliente.setComplemento(rs.getString("COMPLEMENTO"));
           cliente.setRg(rs.getString("RG"));
           cliente.setCpf(rs.getString("CPF"));
           cliente.setTelefone(rs.getString("TELEFONE"));
           cliente.getCidade().setId(rs.getInt("ID_CIDADE"));
           
       }
       
       } catch (SQLException se ){
           JOptionPane.showMessageDialog(null,se.getMessage());
           se.printStackTrace();
       }
        return cliente;
   
   } 
}
