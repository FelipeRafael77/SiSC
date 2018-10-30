package telas;

import componentes.MeuComponenteJ;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaCadastro extends JInternalFrame implements ActionListener {
    private final int PADRAO = 0;
    private final int INCLUINDO = 1;
    private final int ALTERANDO = 2;
    private final int EXCLUINDO = 3;
    private final int CONSULTANDO = 4;
    private int estadoTela = PADRAO;
    private boolean temDadosNaTela = false;
    
    protected JPanel jpComponentes = new JPanel();
    private JPanel jpBotoes = new JPanel();
    private JButton jbIncluir = new JButton("Incluir");
    private JButton jbAlterar = new JButton("Alterar");
    private JButton jbExcluir = new JButton("Excluir");
    private JButton jbConsultar = new JButton("Consultar");
    private JButton jbConfirmar = new JButton("Confirmar");
    private JButton jbCancelar = new JButton("Cancelar");
    private List<MeuComponenteJ> campos = new ArrayList();
    
    
  public TelaCadastro(String titulo){
      super(titulo, true , true, false, false);
      getContentPane().add("North", jpBotoes);
      getContentPane().add("Center", jpComponentes);
      jpComponentes.setLayout(new GridBagLayout());
      jpBotoes.setLayout(new GridLayout(1, 8));
      adicionaBotoes();
      pack();
      setVisible(true);
      TelaSistemaSuporte.jdp.add(this);
      habilitaBotoes();
  }
  
  public void adicionaComponente(int linha, int coluna, int linhas, int colunas, MeuComponenteJ componente){
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = coluna;
      gbc.gridy = linha;
      String texto = componente.getDica();
      if(componente.eObrigatorio()){
          texto = "<html><body>" + texto + "<font color=\"red\">*</font>: ";
      } else {
          texto = texto + ": ";
      }
      JLabel rotulo = new JLabel(texto);
      gbc.anchor = GridBagConstraints.WEST;
      jpComponentes.add(rotulo, gbc);
      gbc.gridx++;
      gbc.gridwidth = colunas;
      gbc.gridheight = linhas;
      jpComponentes.add((JComponent) componente, gbc);
      campos.add(componente);
      
  }
  
  public void adicionaBotoes(){
      adicionaBotao(jbIncluir);
      adicionaBotao(jbAlterar);
      adicionaBotao(jbExcluir);
      adicionaBotao(jbConsultar);
      adicionaBotao(jbConfirmar);
      adicionaBotao(jbCancelar);
  }
  
  public void adicionaBotao(JButton botao){
      jpBotoes.add(botao);
      botao.addActionListener(this);
  }

   public void habilitaBotoes(){
       jbIncluir.setEnabled(estadoTela == PADRAO);
       jbAlterar.setEnabled(estadoTela == PADRAO && temDadosNaTela || estadoTela == CONSULTANDO);
       jbExcluir.setEnabled(estadoTela == PADRAO && temDadosNaTela || estadoTela == CONSULTANDO);
       jbConsultar.setEnabled(estadoTela == PADRAO);
       jbConfirmar.setEnabled(estadoTela != PADRAO);
       jbCancelar.setEnabled(estadoTela != PADRAO && estadoTela != CONSULTANDO);
   } 
   
   public void habilitaCampos(boolean status){
       for (int i = 0; i < campos.size(); i++){
           campos.get(i).habilita(status);
       }
   }
   
   public void limpaCampos(){
       for (int i = 0; i < campos.size(); i++){
           campos.get(i).limpa();
       }
   }
   
   public boolean verificaCampos(){
       String errosObrigatorios = "";
       String errosValidacao = "";
       for (int i = 0; i < campos.size(); i++){
           campos.get(i);
           if(campos.get(i).eObrigatorio() && campos.get(i).eVazio()){
              errosObrigatorios = errosObrigatorios + campos.get(i).getDica() + "\n";
           }
           
           if(!campos.get(i).eValido()){
               errosValidacao = errosValidacao + campos.get(i).getDica() + "\n";
           }
       }
       
       if(!errosObrigatorios.isEmpty()){
           JOptionPane.showMessageDialog(null, "Campos obrigatórios não preenchidos:\n\n" + errosObrigatorios);
       }
       
       if(!errosValidacao.isEmpty()){
           JOptionPane.showMessageDialog(null, "Campos inválidos:\n\n" + errosValidacao);
       }
       return errosObrigatorios.isEmpty() && errosValidacao.isEmpty();
   }
   
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbIncluir){
            incluir();
        } else if (e.getSource() == jbAlterar){
            alterar();
        } else if (e.getSource() == jbExcluir){
            excluir();
        } else if (e.getSource() == jbConsultar){
            consultar();
        } else if (e.getSource() == jbConfirmar){
            confirmar();
        } else if (e.getSource() == jbCancelar){
            cancelar();
        } else {
            System.out.println("Fonte do ActionListener não reconhecida.");
        }
        
    }
    
    public void incluir(){
        estadoTela = INCLUINDO;
        limpaCampos();
        habilitaCampos(true);
        habilitaBotoes();
    }
    public void alterar(){
        estadoTela = ALTERANDO;
        habilitaCampos(true);
        habilitaBotoes();
    }
    public void excluir(){
        estadoTela = EXCLUINDO;
        habilitaBotoes();
    }
    public void consultar(){
        estadoTela = CONSULTANDO;
        habilitaBotoes();
    }
    public void confirmar(){
        if(!verificaCampos()){
          return;  
        }
        if(estadoTela == INCLUINDO){
            incluirBD();
            temDadosNaTela = true;
        }
        if(estadoTela == ALTERANDO){
            alterarBD();
            temDadosNaTela = true;
        }
        if(estadoTela == EXCLUINDO){
           int opcao = JOptionPane.showConfirmDialog(this, "Excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
           
           if(opcao == JOptionPane.YES_OPTION){
               excluirBD();
               limpaCampos();
               temDadosNaTela = false;
           }
           temDadosNaTela = true;
        }
        
        estadoTela = PADRAO;
        habilitaCampos(false);
        habilitaBotoes();
    }
    public void cancelar(){
        estadoTela = PADRAO;
        limpaCampos();
        habilitaCampos(false);
        habilitaBotoes();
    }
    
    public void incluirBD(){
       
    }
    
    public void alterarBD(){
        
    }
    
    public void excluirBD(){
        
    }
    
        
    public void preencherDados(int id){
        
    }
    
}
