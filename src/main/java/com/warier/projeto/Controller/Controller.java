package com.warier.projeto.Controller;

import com.warier.projeto.Model.*;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Controller implements Serializable{

    private static long serialVersionUID = 1L;
    private final List<Item> itens = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();
    private Pedido pedido = null;

    public Pedido getPedido() {
        return pedido;
    }
    
    public boolean pedidoVazio(){
        if(this.pedido == null){
            return true;
        }
        return false;
    }
    
    public void setPedido(Cliente cliente){
        this.pedido = new Pedido(cliente);
    }
    
    public double getPreco(){
        double x = 0;
        for(Item i:pedido.getItens()){
            x += Double.parseDouble(i.getPrecoUnitario().toString());
        }
        return x;
    }
    
    public void esvaziaPedido(){
        this.pedido = null;
    }

    public void criarItem(String nome, double preco, String descricao){
        itens.add(new Item(nome, BigDecimal.valueOf(preco), descricao));
    }

    public void cadastrarCliente(String nome, String endereco, String telefone){
        clientes.add(new Cliente(nome, endereco, telefone));
    }

    public void criarPedido(Cliente cliente){
        pedidos.add(new Pedido(cliente));
    }
    
    public void carregarPedidos(String cpf, int numero, double preco){
        Cliente clien = buscarCliente(cpf);
        pedidos.add(new Pedido(numero, clien, preco));
    }

    public boolean apagarItem(Item item){
        if(itens.contains(item)) {
            itens.remove(item);
            return true;
        }
        return false;
    }

    public boolean apagarCliente(Cliente cliente){
        if(clientes.contains(cliente)){
            clientes.remove(cliente);
            return true;
        }
        return false;
    }

    public boolean apagarPedido(Pedido pedido){
        if (pedidos.contains(pedido)){
            pedidos.remove(pedido);
            return true;
        }
        return false;
    }

    public boolean existe(@org.jetbrains.annotations.NotNull Object x){
        if(x.getClass() == Item.class){
            if(itens.contains(x)){
                return true;
            }
            return false;
        } else if (x.getClass() == Cliente.class) {
            if(clientes.contains(x)){
                return true;
            }
            return false;
        } else if (x.getClass() == Pedido.class) {
            if(pedidos.contains(x)){
                return true;
            }
            return false;
        }
        return false;
    }

    public List<Item> getItens() {
        return itens;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public Cliente buscarCliente(String cpf){
        for(Cliente x:clientes){
            if(x.getEndereco().equals(cpf)){
                return x;
            }
            
        }
        return null;
    }
    
    public Item buscaItem(String nome){
        for(Item x:itens){
            if(x.getNome().equals(nome)){
                return x;
            }
        }
        return null;
    }
    
    public Pedido buscarPedido(int num){
        for(Pedido x:pedidos){
            if(x.getNumero() == num){
                return x;
            }
        }
        return null;
    }
    


}
