package com.warier.projeto.Controller;

import com.warier.projeto.Model.*;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Controller implements Serializable{

    private static long serialVersionUID = 1L;

    private Pedido pedido = null;
    private ClienteDAO cliDAO = new ClienteDAO();
    private ItemDAO itDAO = new ItemDAO();
    private PedidoDAO pedDAO = new PedidoDAO();

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

    public void cadastraPedido(){
        try {
            pedDAO.cadastrar(this.pedido);
        }catch (RuntimeException e){
            System.out.println(e);
        }
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
        try {
            itDAO.cadastrar(new Item(nome, BigDecimal.valueOf(preco), descricao));
        }catch (RuntimeException e){

        }
    }

    public void cadastrarCliente(String nome, String endereco, String telefone){
        try {
            cliDAO.cadastrar(new Cliente(nome, endereco, telefone));
        }catch (RuntimeException e){

        }
    }


    public boolean apagarItem(Item item){
        try {
            if (itDAO.consultar(item) != null) {
                itDAO.apagar(item);
                return true;
            }
            return false;
        } catch (RuntimeException e){
            return false;
        }
    }

    public boolean apagarCliente(Cliente cliente){
        try {
            if (cliDAO.consultar(cliente) != null) {
                cliDAO.apagar(cliente);
                return true;
            }
            return false;
        } catch (RuntimeException e){
            return false;
        }
    }



    public List<Item> getItens() {
        try {
            return itDAO.listar();
        } catch (RuntimeException e){
            return null;
        }
    }

    public List<Cliente> getClientes() {
        try {
            return cliDAO.listar();
        }catch (RuntimeException e){
            return null;
        }
    }

    public List<Pedido> getPedidos() {
        try {
            return pedDAO.listar();
        }catch (RuntimeException e){
            return null;
        }
    }
    
    public Cliente buscarCliente(String cpf){
        Cliente x = new Cliente(cpf);
        try{
            x = cliDAO.consultar(x);
        } catch (RuntimeException e){
            return null;
        }
        if(x != null){
            return x;
        }

        return null;
    }

    
    public Item buscaItem(String nome){
        Item x = new Item(nome);
        try{
            x = itDAO.consultar(x);
        } catch (RuntimeException e){
            return null;
        }
        if(x != null){
            return x;
        }

        return null;
    }


    public void editarCliente(Cliente cliente){
        try {
            cliDAO.alterar(cliente);
        }catch (RuntimeException e){

        }
    }

    public void editarItem(Item item){
        try {
            itDAO.alterar(item);
        }catch (RuntimeException e){

        }
    }

}
