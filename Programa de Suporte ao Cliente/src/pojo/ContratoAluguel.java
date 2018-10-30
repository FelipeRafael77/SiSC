
package pojo;

import java.util.Date;

public class ContratoAluguel {
    private int id;
    private String numero;
    private double valor;
    private Date di;
    private Date dt;
    private Proprietario proprietario = new Proprietario();
    private Cliente cliente = new Cliente();
    private Imovel imovel = new Imovel();
    private StatusAluguel sa = new StatusAluguel();

    public Date getDi() {
        return di;
    }

    public void setDi(Date di) {
        this.di = di;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public StatusAluguel getSa() {
        return sa;
    }

    public void setSa(StatusAluguel sa) {
        this.sa = sa;
    }
    
}
