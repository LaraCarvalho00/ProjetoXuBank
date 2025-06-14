package com.xubank.model;

public class ContaInvestimento extends Conta {
    private double taxaMensal;

    public ContaInvestimento(double taxaMensal) {
        this.taxaMensal = taxaMensal;
    }

    @Override
    public void renderMensal() {
        double rendimento = saldo * taxaMensal;
        if (rendimento > 0) rendimento -= rendimento * 0.01;
        saldo += rendimento;
    }

    @Override
    public void sacar(double valor) {
        double rendimento = saldo * taxaMensal;
        if (rendimento > 0) {
            double imposto = rendimento * 0.225;
            saldo -= imposto;
        }
        super.sacar(valor);
    }

    @Override
    public String getExtratoMensal() {
        return "Conta Investimento - Saldo: R$" + saldo;
    }

    @Override
    public TipoConta getTipo() { return TipoConta.INVESTIMENTO; }
}