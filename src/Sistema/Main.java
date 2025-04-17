package Sistema;

public class Main {
    public static void main(String[] args) {
        Estabelecimento mercado = new Estabelecimento();

        // Adiciona 2 clientes
        mercado.adicionarCliente("Thiago", "12345678901");
        mercado.adicionarCliente("Camila", "98765432100");

        // Cliente 1 faz duas compras fiado e paga uma
        mercado.adicionarCompraFiado(1, 200.00);
        mercado.adicionarCompraFiado(1, 150.00);
        mercado.pagarFiado(1, 100.00);

        // Cliente 2 faz uma compra e paga tudo
        mercado.adicionarCompraFiado(2, 300.00);
        mercado.pagarFiado(2, 300.00);

        // Lista todos os clientes
        System.out.println("\n--- Lista de Clientes ---");
        mercado.listarClientes();

        // Consulta saldos
        System.out.println("\n--- Saldos Individuais ---");
        mercado.consultaSaldo(1);
        mercado.consultaSaldo(2);

        // Mostra histórico das transações
        System.out.println("\n--- Histórico Cliente 1 ---");
        mercado.mostrarHistorico(1);

        System.out.println("\n--- Histórico Cliente 2 ---");
        mercado.mostrarHistorico(2);
    }
}
