package telas;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TelaSistemaSuporte extends JFrame implements ActionListener{
    public static JDesktopPane jdp = new JDesktopPane();
    private JMenu jmCadastros = new JMenu("Cadastros");
    private JMenu jmRelatórios = new JMenu("Relatórios");
    private JMenuItem jmiCidade = new JMenuItem("Cidade");
    private JMenuItem jmiCliente = new JMenuItem("Cliente");
    private JMenuItem jmiContratoAluguel = new JMenuItem("Contrato do Aluguel");
    private JMenuItem jmiEstado = new JMenuItem("Estado");
    private JMenuItem jmiImovel = new JMenuItem("Imóvel");
    private JMenuItem jmiProprietario = new JMenuItem("Proprietário");
    private JMenuItem jmiStatusAluguel = new JMenuItem("Status do Aluguel");
    private JMenuBar jmb = new JMenuBar();
    
    public TelaSistemaSuporte() {
        setTitle("SiSC - Sistema de Suporte ao Cliente");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(jdp);
        jdp.setBackground(Color.gray);
        setJMenuBar(jmb);
        jmb.add(jmCadastros);
        jmb.add(jmRelatórios);
        adicionaItemMenu(jmCadastros, jmiCidade);
        adicionaItemMenu(jmCadastros, jmiCliente);
        adicionaItemMenu(jmCadastros, jmiContratoAluguel);
        adicionaItemMenu(jmCadastros, jmiEstado);
        adicionaItemMenu(jmCadastros, jmiImovel);
        adicionaItemMenu(jmCadastros, jmiProprietario);
        adicionaItemMenu(jmRelatórios, jmiStatusAluguel);
        setVisible(true);
    }
    
    public void adicionaItemMenu(JMenu menu, JMenuItem itemMenu){
        menu.add(itemMenu);
        itemMenu.addActionListener(this);
    }
    
    @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jmiCliente) {
            TelaCadastroCliente telaCliente = new TelaCadastroCliente();
        } else if (e.getSource() == jmiCidade) {
            TelaCadastroCidade telaCidade = new TelaCadastroCidade();
        } else if (e.getSource() == jmiContratoAluguel) {
            TelaCadastroContratoAluguel telaContratoAluguel = new TelaCadastroContratoAluguel();
        }else if (e.getSource() == jmiEstado) {
            TelaCadastroEstado telaEstado = new TelaCadastroEstado();
        } else if (e.getSource() == jmiImovel){
            TelaCadastroImovel telaImovel = new TelaCadastroImovel();
        }else if (e.getSource() == jmiProprietario){
            TelaCadastroProprietario telaProprietario = new TelaCadastroProprietario();
        }else if (e.getSource() == jmiStatusAluguel){
            TelaCadastroStatusAluguel telaALuguel = new TelaCadastroStatusAluguel();
        }
    }
    
    
}
