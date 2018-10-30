package telas;

import banco.Banco;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaConsultaJ extends JFrame {
    private TelaCadastro telaChamadora;
    private DefaultTableModel dtm = new DefaultTableModel();
    private String [][] dados = {};
    private JTable tabela = new JTable(dtm){
        @Override
        public boolean isCellEditable(int linha, int coluna){
            return false;
        }
    };
    private JScrollPane jsp = new JScrollPane(tabela);
    
    public TelaConsultaJ(String nomeTela, String[] colunas, String sql, TelaCadastro telaChamadora){
        this.telaChamadora = telaChamadora;
        setTitle(nomeTela);
        dtm.setDataVector(dados, colunas);
        getContentPane().add(jsp);
        setSize(600, 480);
        setVisible(true);
        List<String[]> dados = Banco.consultaBanco(sql);
        for(int i = 0; i < dados.size(); i++){
            dtm.addRow(dados.get(i));
        }
        tabela.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                if(me.getClickCount() == 2){
                    telaChamadora.preencherDados(Integer.parseInt((String)tabela.getValueAt(tabela.getSelectedRow(), 0)));
                    dispose();
                }
            }
        });
    }
  public void preencherDados(int id){
        JOptionPane.showMessageDialog(null, id);
    } 
}
