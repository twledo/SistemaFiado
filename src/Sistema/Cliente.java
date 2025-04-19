package Sistema;

public class Cliente {
    private static int contadorID = 1;
    private int id;
    private String nome;
    private String cpf;
    private String endereço;
    private String numeroCelular;
    private String descricao;
    private double saldoDevedor;

    public Cliente(String nome, String cpf, String endereço, String numeroCelular) {
        this.id = contadorID++;
        this.nome = nome;
        this.cpf = cpf;
        this.endereço = endereço;
        this.numeroCelular = numeroCelular;
        this.saldoDevedor = 0.0;
        this.descricao = "";
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereço() {
        return endereço;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void adicionarCompraFiado(double valor, String descricao) {
        if (valor < 0) {
            throw new Excecao("ERRO - Impossível adicionar valor negativo");
        }
        this.saldoDevedor += valor;
        System.out.println("--- Compra fiada de R$" + valor + " (" + descricao + ") registrada para " + this.nome + " ---");
    }

    public void pagarFiado(double valor, String descricao) {
        if (valor < 0) {
            throw new Excecao("ERRO - Impossível adicionar valor negativo");
        }

        if (this.saldoDevedor < valor) {
            throw new Excecao("ERRO - Impossivel o valor para pagar ser maior que o saldo devedor");
        }

        this.saldoDevedor -= valor;
        System.out.println("--- Pagando R$" + valor + " (" + descricao + ") de fiado no nome de " + this.nome + " ---");
    }
}