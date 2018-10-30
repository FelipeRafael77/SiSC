package telas;

import componentes.MeuCampoTextoJ;
import dao.DaoStatusAluguel;
import pojo.StatusAluguel;

public class TelaCadastroStatusAluguel extends TelaCadastro {
    private StatusAluguel sa = new StatusAluguel();
    private DaoStatusAluguel daoStatusAluguel = new DaoStatusAluguel(sa);
    private MeuCampoTextoJ campoCodigo = new MeuCampoTextoJ(2, true, "Código");
    private MeuCampoTextoJ campoStatus = new MeuCampoTextoJ(30, true, "Status");

    public TelaCadastroStatusAluguel(){
        super("Status do Aluguel");
       
        adicionaComponente(1, 1, 1, 1, campoCodigo);
        adicionaComponente(2, 1, 1, 1, campoStatus);
        habilitaCampos(false);
        pack();
  
    }
    
    @Override
    public void incluirBD(){
        sa.setId(Integer.parseInt(campoCodigo.getText()));
        sa.setStatus(campoStatus.getText());
        daoStatusAluguel.inserir();
    }
    
    public void alterarBD(){
        sa.setId(Integer.parseInt(campoCodigo.getText()));
        sa.setStatus(campoStatus.getText());
        daoStatusAluguel.alterar(sa);
    }
    
    public void excluirBD(){
        sa.setId(Integer.parseInt(campoCodigo.getText()));
        daoStatusAluguel.excluir();
    }
    
    @Override
    public void consultar(){
        super.consultar();
        new TelaConsultaJ("Status do Aluguel", new String[]{
            "Codigo", "Status"
        }, DaoStatusAluguel.SQL_CONSULTAR, this);
    }
    
    public void preencherDados(int id){
        sa = daoStatusAluguel.consultar(id);
        campoCodigo.setText("" + sa.getId());
        campoStatus.setText(sa.getStatus());
        
        
    }
}
