package Sistema;

public class SistemaFiado {
    public static void main(String[] args) {
        Estabelecimento mercado = new Estabelecimento();
        mercado.adicionarCliente("Thiago", "", "", "");
        mercado.listarClientes();
    }
}