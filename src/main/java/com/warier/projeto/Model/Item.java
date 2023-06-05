package com.warier.projeto.Model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable{
    private static long serialVersionUID = 1L;
    private String nome;
    private BigDecimal precoUnitario;
    private String descricao;

    public Item(String nome, BigDecimal precoUnitario, String descricao) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.descricao = descricao;
    }

    public Item(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
