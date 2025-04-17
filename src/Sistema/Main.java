package Sistema;

public class Main {
    public static void main(String[] args) {
        Estabelecimento estabelecimento = new Estabelecimento();

        // Adicionando 5 clientes distintos
        try {
            System.out.println("=== Adicionando Clientes ===");
            estabelecimento.adicionarCliente("Ana Silva", "12345678900", "Rua A, 123", "42991001001");
            estabelecimento.adicionarCliente("Bruno Costa", "23456789011", "Av. B, 456", "42991001002");
            estabelecimento.adicionarCliente("Clara Mendes", "34567890122", "Rua C, 789", "42991001003");
            estabelecimento.adicionarCliente("Daniel Oliveira", "45678901233", "Av. D, 101", "42991001004");
            estabelecimento.adicionarCliente("Elisa Pereira", "56789012344", "Rua E, 202", "42991001005");
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Listando todos os clientes
        try {
            System.out.println("\n=== Listando Todos os Clientes ===");
            estabelecimento.listarClientes();
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Consultando o cliente com ID 3
        try {
            System.out.println("\n=== Consultando Cliente ID 3 ===");
            estabelecimento.consultaCliente(3);
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Adicionando compras fiado
        try {
            System.out.println("\n=== Adicionando Compras Fiado ===");
            estabelecimento.adicionarCompraFiado(1, 150.75); // Ana
            estabelecimento.adicionarCompraFiado(1, 89.20);  // Ana
            estabelecimento.adicionarCompraFiado(2, 200.50); // Bruno
            estabelecimento.adicionarCompraFiado(3, 75.30);  // Clara
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Pagando fiado para o cliente ID 1
        try {
            System.out.println("\n=== Pagando Fiado ID 1 ===");
            estabelecimento.pagarFiado(1, 100.00);
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Mostrando histórico do cliente ID 1
        try {
            System.out.println("\n=== Histórico do Cliente ID 1 ===");
            estabelecimento.mostrarHistorico(1);
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Atualizando CPF do cliente ID 2
        try {
            System.out.println("\n=== Atualizando CPF ID 2 ===");
            estabelecimento.atualizarCPF(2, "98765432100");
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Atualizando telefone do cliente ID 3
        try {
            System.out.println("\n=== Atualizando Telefone ID 3 ===");
            estabelecimento.atualizarTelefone(3, "42991001006");
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Atualizando endereço do cliente ID 4
        try {
            System.out.println("\n=== Atualizando Endereço ID 4 ===");
            estabelecimento.atualizarEndereço(4, "Rua Nova, 303");
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Testando erro: cliente inexistente
        try {
            System.out.println("\n=== Testando Cliente Inexistente (ID 999) ===");
            estabelecimento.consultaCliente(999);
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Testando erro: CPF duplicado
        try {
            System.out.println("\n=== Testando CPF Duplicado ===");
            estabelecimento.atualizarCPF(5, "12345678900");
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }
    }
}