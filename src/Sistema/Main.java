package Sistema;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Estabelecimento mercado = getEstabelecimento();

        // Lista todos os clientes
        System.out.println("\n--- Lista de Todos os Clientes ---");
        try {
            mercado.listarClientes();
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }

        // Consulta saldos de alguns clientes
        System.out.println("\n--- Saldos Individuais (Clientes 1, 5, 10, 15) ---");
        for (int id : new int[]{1, 5, 10, 15}) {
            try {
                mercado.consultaCliente(id);
            } catch (Excecao e) {
                System.out.println(e.getMessage());
            }
        }

        // Mostra histórico de alguns clientes
        System.out.println("\n--- Histórico de Transações (Clientes 1, 5, 10, 15) ---");
        for (int id : new int[]{1, 5, 10, 15}) {
            try {
                System.out.println("\nHistórico Cliente ID " + id + ":");
                mercado.mostrarHistorico(id);
            } catch (Excecao e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Estabelecimento getEstabelecimento() {
        Estabelecimento mercado = new Estabelecimento();
        Random random = new Random();

        // Lista de nomes para os clientes
        String[] nomes = {
                "Ana", "Bruno", "Carla", "Diego", "Elisa",
                "Felipe", "Gabriela", "Hugo", "Isabela", "João",
                "Karla", "Lucas", "Mariana", "Nelson", "Olivia"
        };

        // Adiciona 15 clientes
        for (int i = 0; i < 15; i++) {
            String nome = nomes[i];
            String cpf = String.format("%011d", 12345678900L + i); // CPFs únicos
            String endereco = "Rua " + (i + 1) + ", Cidade";
            String telefone = "4299" + String.format("%07d", 1000000 + i);

            try {
                mercado.adicionarCliente(nome, cpf, endereco, telefone);
            } catch (Excecao e) {
                System.out.println(e.getMessage());
            }
        }

        // Realiza transações variadas
        for (int id = 1; id <= 15; id++) {
            try {
                // Cada cliente faz entre 1 e 3 compras fiado
                int numCompras = random.nextInt(3) + 1;
                for (int j = 0; j < numCompras; j++) {
                    double valor = 50.0 + random.nextDouble() * 150.0; // Valores entre 50 e 200
                    mercado.adicionarCompraFiado(id, Math.round(valor * 100.0) / 100.0);
                }

                // Alguns clientes fazem pagamentos (70% de chance)
                if (random.nextDouble() < 0.7) {
                    double valor = 30.0 + random.nextDouble() * 100.0; // Pagamentos entre 30 e 130
                    mercado.pagarFiado(id, Math.round(valor * 100.0) / 100.0);
                }
            } catch (Excecao e) {
                System.out.println(e.getMessage());
            }
        }

        return mercado;
    }
}