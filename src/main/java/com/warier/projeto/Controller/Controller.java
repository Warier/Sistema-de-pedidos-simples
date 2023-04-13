package com.warier.projeto.Controller;

import com.warier.projeto.Model.Cliente;
import com.warier.projeto.Model.Item;
import com.warier.projeto.Model.Pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<Item> itens = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

    public void criarItem(String nome, double preco, String descricao){
        itens.add(new Item(nome, BigDecimal.valueOf(preco), descricao));
    }

    public void cadastrarCliente(String nome, String endereco, String telefone){
        clientes.add(new Cliente(nome, endereco, telefone));
    }

    public void criarPedido(Cliente cliente){
        pedidos.add(new Pedido(cliente));
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

    public boolean existe(Object x){
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


}
