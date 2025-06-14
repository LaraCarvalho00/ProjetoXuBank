package com.xubank.model;

import java.util.*;

public class Cliente {
    private String nome, cpf, senha;
    private List<Conta> contas = new ArrayList<>();

    public Cliente(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public void adicionarConta(Conta c) { contas.add(c); }

    public double getSaldoTotal() {
        return contas.stream().mapToDouble(Conta::getSaldo).sum();
    }

    public String getExtratoMensal() {
        StringBuilder sb = new StringBuilder("Extrato de " + nome + "\\n");
        contas.forEach(c -> sb.append(c.getExtratoMensal()).append("\\n"));
        return sb.toString();
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public List<Conta> getContas() { return contas; }
}