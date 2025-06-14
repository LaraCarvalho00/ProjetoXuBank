package com.xubank.model;

import java.time.LocalDateTime;

public class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(double limite) {
        this.limite = limite;
    }

    @Override
public void sacar(double valor) {
    if (valor > saldo + limite) {
        throw new SaldoInsuficienteException("Saldo insuficiente. Limite máximo disponível: R$ " + (saldo + limite));
    }
    saldo -= valor;
    operacoes.add(new Operacao(TipoOperacao.SAQUE, LocalDateTime.now(), valor));
}


    @Override
    public void depositar(double valor) {
        if (saldo < 0) {
            double taxa = Math.abs(saldo) * 0.03 + 10;
            saldo += valor - taxa;
        } else {
            saldo += valor;
        }
        operacoes.add(new Operacao(TipoOperacao.DEPOSITO, LocalDateTime.now(), valor));
    }

    @Override
    public String getExtratoMensal() {
        return "Conta Corrente - Saldo: R$" + saldo;
    }

    @Override
    public void renderMensal() { }

    @Override
    public TipoConta getTipo() { return TipoConta.CORRENTE; }
}