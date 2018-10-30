package telas;

import componentes.MeuCampoTextoJ;
import componentes.MeuDBComboBoxJ;
import dao.DaoCidade;
import dao.DaoEstado;
import pojo.Cidade;
import pojo.Estado;


public class TelaCadastroCidade extends TelaCadastro{
    private Cidade cidade = new Cidade();
    private Estado estado = new Estado();
    private DaoCidade daoCidade = new DaoCidade(cidade);
    private MeuCampoTextoJ campoId = new MeuCampoTextoJ(1, true, "Código");
    private MeuCampoTextoJ campoNome = new MeuCampoTextoJ(50, true, "Nome");
    private MeuCampoTextoJ campoCEP = new MeuCampoTextoJ(8, true, "CEP");
    private MeuDBComboBoxJ campoEstado = new MeuDBComboBoxJ(DaoEstado.SQL_COMBOBOX, true, "Estado");
    
    public TelaCadastroCidade(){
        super("Cadastro de Cidade");
       
        adicionaComponente(1, 1, 1, 1, campoId);
        adicionaComponente(2, 1, 1, 1, campoNome);
        adicionaComponente(3, 1, 1, 1, campoCEP);
        adicionaComponente(4, 1, 1, 1, campoEstado);
        habilitaCampos(false);
        pack();
  
    
    }
    
    public void incluirBD(){
        cidade.setId(Integer.parseInt(campoId.getText()));
        cidade.setNome(campoNome.getText());
        cidade.setCEP(campoCEP.getText().replace(".", "").replace("-", ""));
        cidade.getEstado().setId(campoEstado.getValor());
        daoCidade.inserir();
    }
    
    public void alterarBD(){
        cidade.setId(Integer.parseInt(campoId.getText()));
        cidade.setNome(campoNome.getText());
        cidade.setCEP(campoCEP.getText().replace(".", "").replace("-", ""));
        cidade.getEstado().setId(campoEstado.getValor());
        daoCidade.alterar(cidade);
    }
    
    public void excluirBD(){
        cidade.setId(Integer.parseInt(campoId.getText()));
        daoCidade.excluir();
        
    }
    
    @Override
    public void consultar(){
        super.consultar();
        new TelaConsultaJ("Consulta de Cidade", new String[]{
            "Codigo", "Nome", "CEP", "Estado"
        }, DaoCidade.SQL_CONSULTAR, this);
    }
    
      public void preencherDados(int id){
        cidade = daoCidade.consultar(id);
        campoId.setText(cidade.getId()+"");
        campoNome.setText(cidade.getNome());
        campoCEP.setText(cidade.getCEP());
        campoEstado.setValor(cidade.getEstado().getId());
        
      }
}
