package com.warier.projeto.Model;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    

    public Cliente(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.cpf = endereco;
        this.telefone = telefone;
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
