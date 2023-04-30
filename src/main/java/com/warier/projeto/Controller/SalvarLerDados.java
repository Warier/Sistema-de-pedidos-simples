package com.warier.projeto.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalvarLerDados {
    File clientes = new File("Cliente.txt");
    File itens = new File("Item.txt");
    File pedidos = new File("Pedido.txt");
    Controller controlador = null;
    
    public SalvarLerDados(Controller control){
        this.controlador = control;
        carregar();
    }
    
    public void salvar(){
        salvarClientes();
        salvarItens();
        salvarPedidos();
    }
    
    private void salvarClientes(){
        StringBuilder dados = new StringBuilder();
        FileWriter arquivo = null;
        try {
            arquivo = new FileWriter(clientes);
            dados.append(controlador.getClientes().size() + "\n");
            for(int x = 0;x < controlador.getClientes().size(); x++){
                dados.append(controlador.getClientes().get(x).getNome() + "," +
                    controlador.getClientes().get(x).getEndereco() + "," +
                    controlador.getClientes().get(x).getTelefone() + "\n");
            }
            arquivo.write(dados.toString());
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void salvarItens(){
        StringBuilder dados = new StringBuilder();
        FileWriter arquivo = null;
        try {
            arquivo = new FileWriter(itens);
            dados.append(controlador.getItens().size() + "\n");
            for(int x = 0;x < controlador.getItens().size(); x++){
                dados.append(controlador.getItens().get(x).getNome() + "," +
                    controlador.getItens().get(x).getPrecoUnitario().toString() + "," +
                    controlador.getItens().get(x).getDescricao() + "\n");
            }
            arquivo.write(dados.toString());
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void salvarPedidos(){
        StringBuilder dados = new StringBuilder();
        FileWriter arquivo = null;
        try {
            arquivo = new FileWriter(pedidos);
            dados.append(controlador.getPedidos().size() + "\n");
            for(int x = 0;x < controlador.getPedidos().size(); x++){
                dados.append(controlador.getPedidos().get(x).getCliente().getEndereco() + "," +
                    controlador.getPedidos().get(x).getNumero() + "," +
                    controlador.getPedidos().get(x).getValorTotal().toString() + "\n");
            }
            arquivo.write(dados.toString());
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean arquivoVazio(File arquivo){
        return arquivo.length() == 0;
    }

    private List<String> lerInfo(File arquivo){
        BufferedReader br = null;
        String[] dados = null;
        List<String> info = new ArrayList<String>();
        String linha;
        try {
            br = new BufferedReader(new FileReader(arquivo));
            linha = br.readLine();
            int quant = Integer.parseInt(linha);
            for(int i = 0; i < quant; i++){
                linha = br.readLine();
                dados = linha.split(",");
                for(String x: dados){
                    info.add(x);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return info;
    }
    
    //Carrega todos os dados lidos de dentro dos arquivos para a classe controller
    private void carregar(){
        List<String> clientes = lerInfo(this.clientes);
        List<String> itens = lerInfo(this.itens);
        List<String> pedidos = lerInfo(this.pedidos);
        for(int i = 0; i < itens.size() / 3; i++){
            controlador.criarItem(itens.get(i * 3), Double.parseDouble(itens.get(i * 3 + 1)), itens.get(i * 3 + 2));
            
        }
        for(int i = 0; i < clientes.size() / 3; i++){
            controlador.cadastrarCliente(clientes.get(i * 3), clientes.get(i * 3 + 1), clientes.get(i * 3 + 2));
        }
        for(int i = 0; i < pedidos.size() / 3; i++){
            controlador.carregarPedidos(pedidos.get(i * 3), Integer.parseInt(pedidos.get(i * 3 + 1)), Double.parseDouble(pedidos.get(i * 3 + 2)));
        }
    }

}
