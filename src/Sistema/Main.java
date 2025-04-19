package Sistema;

public class Main {
    public static void main(String[] args) {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.adicionarCliente("Thiago", "", "", "");
        estabelecimento.adicionarCompraFiado(1, 250, "sla");
        estabelecimento.pagarFiado(1, 150, "pagando sla");
        estabelecimento.mostrarHistorico(1);
        estabelecimento.listarClientes();
    }
}