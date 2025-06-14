package com.xubank.controller;

import com.xubank.model.Cliente;
import com.xubank.model.Conta;

import java.util.ArrayList;
import java.util.List;

public class BancoController {
    private List<Cliente> clientes = new ArrayList<>();

    public void cadastrarCliente(String nome, String cpf, String senha) {
        if (buscarClientePorCpf(cpf) != null) {
            throw new IllegalArgumentException("Já existe um cliente com o CPF informado.");
        }
        clientes.add(new Cliente(nome, cpf, senha));
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public void adicionarConta(String cpf, Conta conta) {
        Cliente c = buscarClientePorCpf(cpf);
        if (c != null) {
            c.adicionarConta(conta);
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}