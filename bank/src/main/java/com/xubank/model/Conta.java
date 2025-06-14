package com.xubank.model;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Conta {
    protected double saldo;
    protected List<Operacao> operacoes = new ArrayList<>();

    public void depositar(double valor) {
        saldo += valor;
        operacoes.add(new Operacao(TipoOperacao.DEPOSITO, LocalDateTime.now(), valor));
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            operacoes.add(new Operacao(TipoOperacao.SAQUE, LocalDateTime.now(), valor));
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract String getExtratoMensal();
    public abstract void renderMensal();
    public abstract TipoConta getTipo();
}