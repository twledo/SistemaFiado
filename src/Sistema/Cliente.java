package Sistema;

public class Cliente {
    private static int contadorID = 1;
    private int id;
    private String nome;
    private String cpf;
    private double saldoDevedor;

    public Cliente(String nome, String cpf) {
        this.id = contadorID++;
        this.nome = nome;
        this.cpf = cpf;
        this.saldoDevedor = 0.0;
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

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void adicionarCompraFiado(double valor) {
        if (valor < 0) {
            throw new Excecao("ERRO - Impossível adicionar valor negativo");
        }
        this.saldoDevedor += valor;
        System.out.println("--- Compra fiada de R$" + valor + " registrada para " + this.nome + " ---");
    }

    public void pagarFiado(double valor) {
        if (valor < 0) {
            throw new Excecao("ERRO - Impossível adicionar valor negativo");
        }

        if (this.saldoDevedor < valor) {
            throw new Excecao("ERRO - Impossivel o valor para pagar ser maior que o saldo devedor");
        }

        this.saldoDevedor -= valor;
        System.out.println("--- Pago R$" + valor + " de fiado no nome de " + this.nome + " ---");
    }
}