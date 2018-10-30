package pojo;

public class Imovel {
    private int id;
    private String nome;
    private double valor;
    private Proprietario proprietario = new Proprietario();
    private StatusAluguel sa = new StatusAluguel();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
    
    public StatusAluguel getSa() {
        return sa;
    }

    public void setSa(StatusAluguel sa) {
        this.sa = sa;
    }

 
   
}
