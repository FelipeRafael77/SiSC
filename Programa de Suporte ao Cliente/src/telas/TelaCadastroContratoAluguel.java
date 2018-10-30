  package telas;

import componentes.MeuCampoTextoJ;
import componentes.MeuDBComboBoxJ;
import dao.DaoCliente;
import dao.DaoContratoAluguel;
import dao.DaoImovel;
import dao.DaoProprietario;
import dao.DaoStatusAluguel;
import pojo.Cliente;
import pojo.ContratoAluguel;
import pojo.Imovel;
import pojo.Proprietario;
import pojo.StatusAluguel;

public class TelaCadastroContratoAluguel extends TelaCadastro {
    private ContratoAluguel cl = new ContratoAluguel();
    private Cliente cliente = new Cliente();
    private Proprietario proprietario = new Proprietario();
    private Imovel imovel = new Imovel();
    private StatusAluguel sa = new StatusAluguel();
    private DaoContratoAluguel daoContratoAluguel = new DaoContratoAluguel(cl);
    private MeuCampoTextoJ campoId = new MeuCampoTextoJ(2, true, "Código");
    private MeuCampoTextoJ campoNumero = new MeuCampoTextoJ(2, true, "Nº do Contrato");
    private MeuCampoTextoJ campoValor = new MeuCampoTextoJ(13, true, "Valor do Aluguel");
    private MeuCampoTextoJ campoDi = new MeuCampoTextoJ(10, false, "Data de Inicio");
    private MeuCampoTextoJ campoDt = new MeuCampoTextoJ(10, false, "Data de Termino");
    private MeuDBComboBoxJ campoProprietario = new MeuDBComboBoxJ(DaoProprietario.SQL_COMBOBOX, true, "Proprietario");
    private MeuDBComboBoxJ campoCliente = new MeuDBComboBoxJ(DaoCliente.SQL_COMBOBOX, true, "Cliente");
    private MeuDBComboBoxJ campoImovel = new MeuDBComboBoxJ(DaoImovel.SQL_COMBOBOX, true, "Imovel");
    private MeuDBComboBoxJ campoStatus = new MeuDBComboBoxJ(DaoStatusAluguel.SQL_COMBOBOX, true, "Status");

    public TelaCadastroContratoAluguel(){
        super("Contrato de Aluguel");
       
        adicionaComponente(1, 1, 1, 1, campoId);
        adicionaComponente(2, 1, 1, 1, campoNumero);
        adicionaComponente(3, 1, 1, 1, campoValor);
        adicionaComponente(4, 1, 1, 1, campoDi);
        adicionaComponente(5, 1, 1, 1, campoDt);
        adicionaComponente(6, 1, 1, 1, campoProprietario);
        adicionaComponente(7, 1, 1, 1, campoCliente);
        adicionaComponente(8, 1, 1, 1, campoImovel);
        adicionaComponente(9, 1, 1, 1, campoStatus);
        habilitaCampos(false);
        pack();
  
    }
    
    @Override
    public void incluirBD(){
        
        cl.setId(Integer.parseInt(campoId.getText()));
        cl.setNumero(campoNumero.getText());
        cl.setValor(Double.parseDouble(campoValor.getText()));
        cl.setDi(util.Util.stringToData(campoDi.getText()));
        cl.setDt(util.Util.stringToData(campoDt.getText()));
        cl.getProprietario().setId(campoProprietario.getValor());
        cl.getCliente().setId(campoCliente.getValor());
        cl.getImovel().setId(campoImovel.getValor());
        cl.getSa().setId(campoStatus.getValor());
        daoContratoAluguel.inserir();
    }
    
    public void alterarBD(){
        cl.setId(Integer.parseInt(campoId.getText()));
        cl.setNumero(campoNumero.getText());
        cl.setValor(Double.parseDouble(campoValor.getText()));
        cl.setDi(util.Util.stringToData(campoDi.getText()));
        cl.setDt(util.Util.stringToData(campoDt.getText()));
        cl.getProprietario().setId(campoProprietario.getValor());
        cl.getCliente().setId(campoCliente.getValor());
        cl.getImovel().setId(campoImovel.getValor());
        cl.getSa().setId(campoStatus.getValor());
        daoContratoAluguel.alterar(cl);
    }
    
    public void excluirBD(){
        cl.setId(Integer.parseInt(campoId.getText()));
        daoContratoAluguel.excluir();
    }
    
    @Override
    public void consultar(){
        super.consultar();
        new TelaConsultaJ("Consulta de Contrato de Aluguel", new String[]{
            "Codigo", "Nº do Contrato", "Valor do Aluguel", "Data de Inicio", "Data de Termino", "Proprietario", "Cliente", "Imovel", "Status"
        }, DaoContratoAluguel.SQL_CONSULTAR, this);
    }
    
    @Override
    public void preencherDados(int id){
        cl = daoContratoAluguel.consultar(id);
        campoId.setText("" + cl.getId());
        campoNumero.setText(cl.getNumero());
        campoValor.setText("" + cl.getValor());
        campoDi.setText("" + cl.getDi());
        campoDt.setText("" + cl.getDt());
        campoProprietario.setValor(cl.getProprietario().getId());
        campoCliente.setValor(cl.getCliente().getId());
        campoImovel.setValor(cl.getImovel().getId());
        campoStatus.setValor(cl.getSa().getId());
        
        
    }

}