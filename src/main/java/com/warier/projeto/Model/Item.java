package com.warier.projeto.Model;

import java.math.BigDecimal;

public class Item {
    private String nome;
    private int quantidade;
    private BigDecimal precoUnitario;
    private String descricao;

    public Item(String nome, BigDecimal precoUnitario, String descricao) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
