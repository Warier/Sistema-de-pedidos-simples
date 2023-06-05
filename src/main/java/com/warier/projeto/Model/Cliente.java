package com.warier.projeto.Model;

import java.io.Serializable;

public class Cliente implements Serializable{
    private static long serialVersionUID = 1L;
    private String nome;
    private String cpf;
    private String telefone;
    

    public Cliente(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.cpf = endereco;
        this.telefone = telefone;
    }

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.cpf = endereco;
    }

    public Cliente(String cpf){
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return cpf;
    }

    public void setEndereco(String endereco) {
        this.cpf = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
