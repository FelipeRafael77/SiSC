package componentes;

import javax.swing.JComboBox;

public class MeuComboBoxJ extends JComboBox implements MeuComponenteJ{
    private boolean obrigatorio;
    private String dica;
    
    public MeuComboBoxJ(String[] itens, boolean obrigatorio, String dica){
        super(itens);
        this.obrigatorio = obrigatorio;
        this.dica = dica;
    }

    @Override
    public void limpa() {
        setSelectedIndex(0);
    }

    @Override
    public void habilita(boolean status) {
        setEnabled(status);
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public boolean eVazio() {
        return getSelectedIndex() <= 0;
    }

    @Override
    public String getDica() {
        return dica;
    }
}
