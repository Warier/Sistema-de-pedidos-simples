package com.warier.projeto.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SalvarLerDados {
    File save = new File("info.txt");
    Controller controlador = new Controller();
    
    
    
    private void salvarDados(){
        StringBuilder dados = new StringBuilder(cliente.getNome() + "," + cliente.getEmail() + "," + cliente.getSenha() + "," + cliente.getIdade());
        FileWriter arquivo = null;
        try {
            arquivo = new FileWriter(save);
            arquivo.write(dados.toString());
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean arquivoVazio(){
        return this.save.length() == 0;
    }

    public String[] lerInfo(){
        BufferedReader arquivo = null;
        String[] dados = null;
        try {
            arquivo = new BufferedReader(new FileReader(save));
            dados = arquivo.readLine().split(",");
            arquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cliente = new Cliente(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
        return dados;
    }

}
