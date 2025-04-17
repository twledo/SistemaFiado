package Sistema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estabelecimento {
    private HashMap<Integer, Cliente> dbClientes = new HashMap<>();
    private Map<Integer, List<HistoricoTransação>> historicoCliente = new HashMap<>();

    public class HistoricoTransação {
        private String tipo;
        private double valor;
        private LocalDateTime dataHora;

        public HistoricoTransação(String tipo, double valor) {
            this.tipo = tipo;
            this.valor = valor;
            this.dataHora = LocalDateTime.now();
        }

        @Override
        public String toString() {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return tipo + " de R$" + String.format("%.2f", valor) + " em " + dataHora.format(formatador);
        }
    }

    public void adicionarCliente(String nome, String cpf, String endereco, String numeroCelular) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Excecao("ERRO - Nome não pode ser vazio!");
        }

        if (endereco == null) endereco = "";
        if (numeroCelular == null) numeroCelular = "";

        String cpfFormatado = formataCPF(cpf);
        if (cpfFormatado != null) {
            for (Cliente cliente : dbClientes.values()) {
                if (cpfFormatado.equals(cliente.getCpf())) {
                    throw new Excecao("ERRO - Já existe um cliente com este CPF!");
                }
            }
        }

        Cliente cliente = new Cliente(nome.trim(), cpfFormatado, endereco, numeroCelular);
        dbClientes.put(cliente.getId(), cliente);
        System.out.println("--- Cliente " + cliente.getNome() + " adicionado com sucesso (ID = " + cliente.getId() + ") ---");
    }


    public void adicionarCompraFiado(int id, double valor) {
        Cliente cliente = dbClientes.get(id);
        if (cliente == null) {
            throw new Excecao("ERRO - Cliente não encontrado!");
        }
        if (valor < 0) {
            throw new Excecao("ERRO - Impossível adicionar valor negativo");
        }
        cliente.adicionarCompraFiado(valor);
        registrarHistorico(id, "Compra fiado", valor);
    }


    public void pagarFiado(int id, double valor) {
        Cliente cliente = dbClientes.get(id);
        if (cliente == null) {
            throw new Excecao("ERRO - Usuário não encontrado");
        }
        cliente.pagarFiado(valor);
        registrarHistorico(id, "Pagamento", valor);
    }

    public void listarClientes() {
        if (dbClientes.isEmpty()) {
            throw new Excecao("ERRO - Usuários não cadastrados");
        } else {
            dbClientes.forEach((id, cliente) -> System.out.println(
                    "-=- ID: " + id +
                            " | Nome: " + cliente.getNome() +
                            " | CPF: " + cliente.getCpf() +
                            " | Endereço: " + cliente.getEndereço() +
                            " | Telefone: " + cliente.getNumeroCelular() +
                            " | Saldo devedor: " + String.format("%.2f", cliente.getSaldoDevedor()) + " -=-"
            ));
        }
    }

    public void consultaCliente(int id) {
        Cliente cliente = dbClientes.get(id);
        if (cliente == null) {
            throw new Excecao("ERRO - Usuário não encontrado para ID: " + id);
        }
        System.out.println(
                "-=- ID: " + id +
                        " | Nome: " + cliente.getNome() +
                        " | CPF: " + cliente.getCpf() +
                        " | Endereço: " + cliente.getEndereço() +
                        " | Telefone: " + cliente.getNumeroCelular() +
                        " | Saldo devedor: " + String.format("%.2f", cliente.getSaldoDevedor()) + " -=-"
        );
    }

    private void registrarHistorico(int id, String tipo, double valor) {
        historicoCliente.computeIfAbsent(id, k -> new ArrayList<>()).add(new HistoricoTransação(tipo, valor));
    }

    public void mostrarHistorico(int id) {
        Cliente cliente = dbClientes.get(id);
        if (cliente == null) {
            throw new Excecao("ERRO - Cliente não encontrado para ID: " + id);
        }

        List<HistoricoTransação> historico = historicoCliente.get(id);
        if (historico == null || historico.isEmpty()) {
            throw new Excecao("Nenhum transação registrada para " + cliente.getNome());
        }

        System.out.println("Historico de transação para " + cliente.getNome() + ":");
        for (HistoricoTransação transação : historico) {
            System.out.println(" - " + transação);
        }
    }

    public void atualizarCPF(int id, String novoCPF) {
        Cliente cliente = dbClientes.get(id);
        if (cliente == null) {
            throw new Excecao("ERRO - Cliente não encontrado para ID: " + dbClientes.get(id));
        }

        String cpfFormatado_Atualizado = formataCPF(novoCPF);
        for (Cliente c : dbClientes.values()) {
            if (cpfFormatado_Atualizado.equals(c.getCpf()) && c.getId() != id) {
                throw new Excecao("ERRO - Já existe um cliente com este CPF!");            }
        }

        cliente.setCpf(cpfFormatado_Atualizado);
        System.out.println("--- CPF do cliente " + cliente.getNome() + " atualizado para " + cpfFormatado_Atualizado + " ---");
    }

    public void atualizarTelefone (int id, String novoNumero) {
        Cliente cliente = dbClientes.get(id);
        if (cliente == null) {
            throw new Excecao("ERRO - Cliente não encontrado para ID: " + id);
        }

        cliente.setNumeroCelular(novoNumero);
        System.out.println("--- Número de celular do cliente " + cliente.getNome() + " atualizado para " + novoNumero + " ---");
    }

    public void atualizarEndereço(int id, String novoEndereço) {
        Cliente cliente = dbClientes.get(id);
        if (cliente == null) {
            throw new Excecao("ERRO - Cliente não encontrado para ID: " + id);
        }

        cliente.setEndereço(novoEndereço);
        System.out.println("--- Endereço do cliente " + cliente.getNome() + " atualizado para " + novoEndereço + " ---");
    }


    private static String formataCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return "";
        } else {
            String cpfLimpo = cpf.replaceAll("[^0-9]", ""); // Remove tudo que não é dígito
            if (!cpfLimpo.matches("\\d{11}")) {
                throw new Excecao("ERRO - CPF deve conter exatamente 11 dígitos!");
            }

            String cpfFormatado = String.format("%s.%s.%s-%s",
                    cpfLimpo.substring(0, 3),
                    cpfLimpo.substring(3, 6),
                    cpfLimpo.substring(6, 9),
                    cpfLimpo.substring(9, 11));
            return cpfFormatado;
        }
    }
}