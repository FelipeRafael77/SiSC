package telas;

import componentes.MeuCampoTextoJ;
import componentes.MeuDBComboBoxJ;
import dao.DaoCidade;
import dao.DaoProprietario;
import pojo.Cidade;
import pojo.Proprietario;

public class TelaCadastroProprietario extends TelaCadastro{
    private Proprietario proprietario = new Proprietario();
    private Cidade cidade = new Cidade();
    private DaoProprietario daoProprietario = new DaoProprietario(proprietario); 
    private MeuCampoTextoJ campoCodigo = new MeuCampoTextoJ(1, true, "Código");
    private MeuCampoTextoJ campoNome = new MeuCampoTextoJ(50, true, "Nome");
    private MeuCampoTextoJ campoEndereco = new MeuCampoTextoJ(50, true, "Endereço");
    private MeuCampoTextoJ campoComplemento = new MeuCampoTextoJ(45, true, "Complemento");
    private MeuCampoTextoJ campoRG = new MeuCampoTextoJ(20, true, "RG");
    private MeuCampoTextoJ campoCPF = new MeuCampoTextoJ(11, true, "CPF");
    private MeuCampoTextoJ campoTelefone = new MeuCampoTextoJ(11, true, "Telefone");
    private MeuDBComboBoxJ campoCidade = new MeuDBComboBoxJ(DaoCidade.SQL_COMBOBOX, true, "Cidade");

    public TelaCadastroProprietario(){
        super("Cadastro de Proprietário");
       
        adicionaComponente(1, 1, 1, 1, campoCodigo);
        adicionaComponente(2, 1, 1, 1, campoNome);
        adicionaComponente(3, 1, 1, 1, campoEndereco);
        adicionaComponente(4, 1, 1, 1, campoComplemento);
        adicionaComponente(5, 1, 1, 1, campoRG);
        adicionaComponente(6, 1, 1, 1, campoCPF);
        adicionaComponente(7, 1, 1, 1, campoTelefone);
        adicionaComponente(8, 1, 1, 1, campoCidade);
        habilitaCampos(false);
        pack();
  
    }
    
    @Override
    public void incluirBD(){
        proprietario.setId(Integer.parseInt(campoCodigo.getText()));
        proprietario.setNome(campoNome.getText());
        proprietario.setEndereco(campoEndereco.getText());
        proprietario.setComplemento(campoComplemento.getText());
        proprietario.setRg(campoRG.getText().replace(".", "").replace(".", "").replace("-", ""));
        proprietario.setCpf(campoCPF.getText().replace(".", "").replace(".", "").replace("-", ""));
        proprietario.setTelefone(campoTelefone.getText().replace("(", "").replace(")", "").replace("-", ""));
        proprietario.getCidade().setId(campoCidade.getValor());
        daoProprietario.inserir();
    }
    
    public void alterarBD(){
        proprietario.setId(Integer.parseInt(campoCodigo.getText()));
        proprietario.setNome(campoNome.getText());
        proprietario.setEndereco(campoEndereco.getText());
        proprietario.setComplemento(campoComplemento.getText());
        proprietario.setRg(campoRG.getText().replace(".", "").replace(".", "").replace("-", ""));
        proprietario.setCpf(campoCPF.getText().replace(".", "").replace(".", "").replace("-", ""));
        proprietario.setTelefone(campoTelefone.getText().replace("(", "").replace(")", "").replace("-", ""));
        proprietario.getCidade().setId(campoCidade.getValor());
        daoProprietario.alterar(proprietario);
    }
    
    public void excluirBD(){
        proprietario.setId(Integer.parseInt(campoCodigo.getText()));
        daoProprietario.excluir();
    }
    
    @Override
    public void consultar(){
        super.consultar();
        new TelaConsultaJ("Consulta de Proprietario", new String[]{
            "Codigo", "Nome", "Endereco", "Complemento", "RG", "CPF", "Telefone", "Cidade"
        }, DaoProprietario.SQL_CONSULTAR, this);
    }
    
    public void preencherDados(int id){
        proprietario = daoProprietario.consultar(id);
        campoCodigo.setText("" + proprietario.getId());
        campoNome.setText(proprietario.getNome());
        campoEndereco.setText(proprietario.getEndereco());
        campoComplemento.setText(proprietario.getComplemento());
        campoRG.setText("" + proprietario.getRg());
        campoCPF.setText("" + proprietario.getCpf());
        campoTelefone.setText("" + proprietario.getTelefone());
        campoCidade.setValor(proprietario.getCidade().getId());
        
        
    }
}
