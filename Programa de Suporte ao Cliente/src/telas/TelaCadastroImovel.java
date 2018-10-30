package telas;

import componentes.MeuCampoTextoJ;
import componentes.MeuDBComboBoxJ;
import dao.DaoImovel;
import dao.DaoStatusAluguel;
import pojo.Imovel;
import pojo.StatusAluguel;

public class TelaCadastroImovel extends TelaCadastro{
    private Imovel imovel = new Imovel();
    private StatusAluguel sa = new StatusAluguel();
    private DaoImovel daoImovel = new DaoImovel(imovel);
    private MeuCampoTextoJ campoCodigo = new MeuCampoTextoJ(2, true, "Código");
    private MeuCampoTextoJ campoNome = new MeuCampoTextoJ(50, true, "Nome do Imóvel");
    private MeuCampoTextoJ campoValor = new MeuCampoTextoJ(13, true, "Valor Líquido");
    private MeuDBComboBoxJ campoStatus = new MeuDBComboBoxJ(DaoStatusAluguel.SQL_COMBOBOX, true, "Status");
    

    public TelaCadastroImovel(){
        super("Cadastro de Imóvel");
       
        adicionaComponente(1, 1, 1, 1, campoCodigo);
        adicionaComponente(2, 1, 1, 1, campoNome);
        adicionaComponente(3, 1, 1, 1, campoValor);
        adicionaComponente(4, 1, 1, 1, campoStatus);
        habilitaCampos(false);
        pack();
  
    }
    
    @Override
    public void incluirBD(){
        imovel.setId(Integer.parseInt(campoCodigo.getText()));
        imovel.setNome(campoNome.getText());
        imovel.setValor(Double.parseDouble(campoValor.getText()));
        imovel.getSa().setId(campoStatus.getValor());
        daoImovel.inserir();
    }
    
    public void alterarBD(){
        imovel.setId(Integer.parseInt(campoCodigo.getText()));
        imovel.setNome(campoNome.getText());
        imovel.setValor(Double.parseDouble(campoValor.getText()));
        imovel.getSa().setId(campoStatus.getValor());
        daoImovel.alterar(imovel);
    }
    
    public void excluirBD(){
        imovel.setId(Integer.parseInt(campoCodigo.getText()));
        daoImovel.excluir();
    }
    
    @Override
    public void consultar(){
        super.consultar();
        new TelaConsultaJ("Consulta de Imoveis", new String[]{
            "Codigo", "Nome do Imóvel", "Valor Liquido", "Status"
        }, DaoImovel.SQL_CONSULTAR, this);
    }
    
    public void preencherDados(int id){
        imovel = daoImovel.consultar(id);
        campoCodigo.setText("" + imovel.getId());
        campoNome.setText(imovel.getNome());
        campoValor.setText("" + imovel.getValor());
        campoStatus.setValor(imovel.getSa().getId());
        
        
    }
 }
