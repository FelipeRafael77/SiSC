package telas;

import componentes.MeuCampoTextoJ;
import componentes.MeuDBComboBoxJ;
import dao.DaoCidade;
import dao.DaoCliente;
import pojo.Cidade;
import pojo.Cliente;

public class TelaCadastroCliente extends TelaCadastro{
    private Cliente cliente = new Cliente();
    private Cidade cidade = new Cidade();
    private DaoCliente daoCliente = new DaoCliente(cliente);
    private DaoCidade daoCidade = new DaoCidade(cidade);
    private MeuCampoTextoJ campoId = new MeuCampoTextoJ(2, true, "Código");
    private MeuCampoTextoJ campoNome = new MeuCampoTextoJ(50, true, "Nome");
    private MeuCampoTextoJ campoEndereco = new MeuCampoTextoJ(50, true, "Endereço");
    private MeuCampoTextoJ campoComplemento = new MeuCampoTextoJ(45, true, "Complemento");
    private MeuCampoTextoJ campoRG = new MeuCampoTextoJ(20, true, "RG");
    private MeuCampoTextoJ campoCPF = new MeuCampoTextoJ(11, true, "CPF");
    private MeuCampoTextoJ campoTelefone = new MeuCampoTextoJ(11, true, "Telefone");
    private MeuDBComboBoxJ campoCidade = new MeuDBComboBoxJ(DaoCidade.SQL_COMBOBOX, true, "Cidade");

    public TelaCadastroCliente(){
        super("Cadastro de Cliente");
       
        adicionaComponente(1, 1, 1, 1, campoId);
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
        cliente.setId(Integer.parseInt(campoId.getText()));
        cliente.setNome(campoNome.getText());
        cliente.setEndereco(campoEndereco.getText());
        cliente.setComplemento(campoComplemento.getText());
        cliente.setRg(campoRG.getText());
        cliente.setCpf(campoCPF.getText());
        cliente.setTelefone(campoTelefone.getText());
        cliente.getCidade().setId(campoCidade.getValor());
        daoCliente.inserir();
    }
    
    public void alterarBD(){
        cliente.setId(Integer.parseInt(campoId.getText()));
        cliente.setNome(campoNome.getText());
        cliente.setEndereco(campoEndereco.getText());
        cliente.setComplemento(campoComplemento.getText());
        cliente.setRg(campoRG.getText().replace(".", "").replace(".", "").replace("-", ""));
        cliente.setCpf(campoCPF.getText().replace(".", "").replace(".", "").replace("-", ""));
        cliente.setTelefone(campoTelefone.getText().replace("(", "").replace(")", "").replace("-", ""));
        cliente.getCidade().setId(campoCidade.getValor());
        daoCliente.alterar(cliente);
    }
    
    public void excluirBD(){
        cliente.setId(Integer.parseInt(campoId.getText()));
        daoCliente.excluir();
    }
    
    @Override
    public void consultar(){
        super.consultar();
        new TelaConsultaJ("Consulta de Clientes", new String[]{
            "Codigo", "Nome", "Endereço", "Complemento", "RG", "CPF", "Telefone", "Cidade"
        }, DaoCliente.SQL_CONSULTAR, this);
    }
    
    public void preencherDados(int id){
        cliente = daoCliente.consultar(id);
        campoId.setText("" + cliente.getId());
        campoNome.setText(cliente.getNome());
        campoEndereco.setText(cliente.getEndereco());
        campoComplemento.setText(cliente.getComplemento());
        campoRG.setText(cliente.getRg());
        campoCPF.setText(cliente.getCpf());
        campoTelefone.setText(cliente.getTelefone());
        campoCidade.setValor(cliente.getCidade().getId());
        
        
    }
}
