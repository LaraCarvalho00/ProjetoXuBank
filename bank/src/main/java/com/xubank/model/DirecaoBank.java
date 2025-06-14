package com.xubank.model;

import java.util.*;

public class DirecaoBank {

    private List<Cliente> clientes;

    public DirecaoBank(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Map<TipoConta, Double> getTotalPorTipo() {
        Map<TipoConta, Double> totalPorTipo = new EnumMap<>(TipoConta.class);
        for (Cliente cliente : clientes) {
            for (Conta conta : cliente.getContas()) {
                TipoConta tipo = conta.getTipo();
                totalPorTipo.put(tipo, totalPorTipo.getOrDefault(tipo, 0.0) + conta.getSaldo());
            }
        }
        return totalPorTipo;
    }

    public double getSaldoMedio() {
        int totalContas = 0;
        double soma = 0;
        for (Cliente cliente : clientes) {
            for (Conta conta : cliente.getContas()) {
                soma += conta.getSaldo();
                totalContas++;
            }
        }
        return totalContas == 0 ? 0 : soma / totalContas;
    }

    public Cliente getClienteMaiorSaldo() {
        return clientes.stream()
                .max(Comparator.comparingDouble(Cliente::getSaldoTotal))
                .orElse(null);
    }

    public Cliente getClienteMenorSaldo() {
        return clientes.stream()
                .min(Comparator.comparingDouble(Cliente::getSaldoTotal))
                .orElse(null);
    }
}
