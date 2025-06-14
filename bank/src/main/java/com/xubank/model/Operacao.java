package com.xubank.model;

import java.time.LocalDateTime;

public class Operacao {
    private TipoOperacao tipo;
    private LocalDateTime data;
    private double valor;

    public Operacao(TipoOperacao tipo, LocalDateTime data, double valor) {
        this.tipo = tipo;
        this.data = data;
        this.valor = valor;
    }
}