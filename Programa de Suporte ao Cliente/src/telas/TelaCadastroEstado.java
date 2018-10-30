package telas;

import componentes.MeuCampoTextoJ;
import dao.DaoEstado;
import pojo.Estado;

public class TelaCadastroEstado extends TelaCadastro{
    private Estado estado = new Estado();
    private DaoEstado daoEstado = new DaoEstado(estado);
    private MeuCampoTextoJ campoId = new MeuCampoTextoJ(2, true, "Código");
    private MeuCampoTextoJ campoNomeEstado = new MeuCampoTextoJ(50, true, "Nome");

    public TelaCadastroEstado(){
        super("Cadastro de Estado");
       
        adicionaComponente(1, 1, 1, 1, campoId);
        adicionaComponente(2, 1, 1, 1, campoNomeEstado);
        habilitaCampos(false);
        pack();
  
    }
    
     @Override
    public void incluirBD(){
        estado.setId(Integer.parseInt(campoId.getText()));
        estado.setNome(campoNomeEstado.getText());
        daoEstado.inserir();
    }
    
    public void alterarBD(){
        estado.setId(Integer.parseInt(campoId.getText()));
        estado.setNome(campoNomeEstado.getText());
        daoEstado.alterar(estado);
    }
    
    public void excluirBD(){
        estado.setId(Integer.parseInt(campoId.getText()));
        daoEstado.excluir();
    }
    
    @Override
    public void consultar(){
        super.consultar();
        new TelaConsultaJ("Consulta de Estados", new String[]{
            "Codigo", "Nome"
        }, DaoEstado.SQL_CONSULTAR, this);
    }
    
    public void preencherDados(int id){
        estado = daoEstado.consultar(id);
        campoId.setText("" + estado.getId());
        campoNomeEstado.setText(estado.getNome());
        
        
    }
}
