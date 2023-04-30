/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.warier.projeto.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author wary
 */
public class SalvarLerDadosBin {
    
    private ObjectInputStream ler = null;
    private ObjectOutputStream escrever = null;
    private File dados = null;
    
    public SalvarLerDadosBin(){
        this.dados = new File("dados.bin"); 
    }
    
    public Controller carregar(){
        try{
        ler = new ObjectInputStream(new FileInputStream(dados));
        Controller controller = (Controller) ler.readObject();
        ler.close();
        return controller;
        }catch (ClassNotFoundException erro) {
            erro.printStackTrace();
        } catch (IOException erro) {
            erro.printStackTrace();
        }
        return new Controller();
    }
    
    public void salvar(Controller controle){

        try {
            escrever = new ObjectOutputStream(new FileOutputStream(dados, false));
            escrever.writeObject(controle);
            escrever.close();
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }
    
}
