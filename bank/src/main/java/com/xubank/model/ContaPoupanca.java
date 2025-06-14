package com.xubank.model;

public class ContaPoupanca extends Conta {
    @Override
    public void renderMensal() {
        saldo *= 1.006;
    }

    @Override
    public String getExtratoMensal() {
        return "Conta Poupança - Saldo: R$" + saldo;
    }

    @Override
    public TipoConta getTipo() { return TipoConta.POUPANCA; }
}