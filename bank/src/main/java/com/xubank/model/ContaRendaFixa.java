package com.xubank.model;

public class ContaRendaFixa extends Conta {
    private double taxaMensal;

    public ContaRendaFixa(double taxaMensal) {
        this.taxaMensal = taxaMensal;
    }

    @Override
    public void renderMensal() {
        saldo = saldo * (1 + taxaMensal) - 20;
    }

    @Override
    public void sacar(double valor) {
        double rendimento = saldo * taxaMensal;
        double imposto = rendimento * 0.15;
        saldo -= imposto;
        super.sacar(valor);
    }

    @Override
    public String getExtratoMensal() {
        return "Conta Renda Fixa - Saldo: R$" + saldo;
    }

    @Override
    public TipoConta getTipo() { return TipoConta.RENDA_FIXA; }
}