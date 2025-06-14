package com.xubank.view;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.xubank.controller.BancoController;
import com.xubank.model.Cliente;
import com.xubank.model.Conta;
import com.xubank.model.ContaCorrente;
import com.xubank.model.ContaInvestimento;
import com.xubank.model.ContaPoupanca;
import com.xubank.model.ContaRendaFixa;
import com.xubank.model.DirecaoBank;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BancoController controller = new BancoController();
    private static Random random = new Random();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("=== XuBank - Menu Principal ===");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Adicionar conta");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Ver extrato");
            System.out.println("6. Relatório da Direção");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> adicionarConta();
                case 3 -> operacaoConta(true);
                case 4 -> operacaoConta(false);
                case 5 -> verExtrato();
                case 6 -> relatorioDirecao();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }

    private static void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        controller.cadastrarCliente(nome, cpf, senha);
        System.out.println("Cliente cadastrado.");
    }

    private static void adicionarConta() {
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.println("Tipo de conta: 1-CC, 2-CP, 3-RF, 4-INV");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Conta novaConta = switch (tipo) {
            case 1 -> {
                System.out.print("Limite: ");
                double limite = scanner.nextDouble();
                scanner.nextLine();
                yield new ContaCorrente(limite);
            }
            case 2 -> new ContaPoupanca();
            case 3 -> {
                double taxa = 0.005 + (0.0085 - 0.005) * random.nextDouble();
                System.out.printf("Taxa Renda Fixa gerada: %.4f%n", taxa);
                yield new ContaRendaFixa(taxa);
            }
            case 4 -> {
                double taxa = -0.006 + (0.015 - (-0.006)) * random.nextDouble();
                System.out.printf("Taxa Investimento gerada: %.4f%n", taxa);
                yield new ContaInvestimento(taxa);
            }
            default -> null;
        };

        if (novaConta != null) {
            controller.adicionarConta(cpf, novaConta);
            System.out.println("Conta adicionada com sucesso.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void operacaoConta(boolean deposito) {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        Cliente c = controller.buscarClientePorCpf(cpf);
        if (c == null || c.getContas().isEmpty()) {
            System.out.println("Cliente ou contas inexistentes.");
            return;
        }

        List<Conta> contas = c.getContas();
        for (int i = 0; i < contas.size(); i++) {
            System.out.println(i + " - " + contas.get(i).getTipo());
        }
        System.out.print("Escolha conta: ");
        int idx = scanner.nextInt();
        scanner.nextLine();
        if (idx < 0 || idx >= contas.size()) {
            System.out.println("Índice inválido");
            return;
        }

        Conta conta = contas.get(idx);
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (deposito) conta.depositar(valor);
        else conta.sacar(valor);

        System.out.println("Operação concluída.");
    }

    private static void verExtrato() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        Cliente c = controller.buscarClientePorCpf(cpf);
        if (c != null) System.out.println(c.getExtratoMensal());
        else System.out.println("Cliente não encontrado.");
    }

    private static void relatorioDirecao() {
        DirecaoBank direcao = new DirecaoBank(controller.getClientes());

        System.out.println("=== Relatório da Direção ===");

        System.out.println("Custódia por tipo de conta:");
        direcao.getTotalPorTipo().forEach((tipo, valor) ->
                System.out.printf("- %s: R$ %.2f%n", tipo, valor));

        System.out.printf("Saldo médio das contas: R$ %.2f%n", direcao.getSaldoMedio());

        Cliente maior = direcao.getClienteMaiorSaldo();
        Cliente menor = direcao.getClienteMenorSaldo();
        if (maior != null)
            System.out.printf("Cliente com maior saldo: %s (R$ %.2f)%n", maior.getNome(), maior.getSaldoTotal());
        if (menor != null)
            System.out.printf("Cliente com menor saldo: %s (R$ %.2f)%n", menor.getNome(), menor.getSaldoTotal());
    }
}