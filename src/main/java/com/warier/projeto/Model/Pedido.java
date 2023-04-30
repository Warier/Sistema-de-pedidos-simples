package com.warier.projeto.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Pedido implements Serializable{
    private static long serialVersionUID = 1L;
    private int numero;
    private List<Item> itens = new ArrayList<>();
    private BigDecimal valorTotal;
    private Cliente cliente;

    private boolean pedidoAberto;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.gerarNumeroDePedido();
        pedidoAberto = true;
    }
    
    public Pedido(int numero, Cliente cliente, double preco){
        this.numero = numero;
        this.cliente = cliente;
        this.valorTotal = BigDecimal.valueOf(preco);
    }

    public int getNumero() {
        return numero;
    }


    public List<Item> getItens() {
        return itens;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setItens(Item e) {
        this.itens.add(e);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public boolean isPedidoAberto() {
        return pedidoAberto;
    }
    
    

    public int gerarNumeroDePedido() {
        Random rand = new Random();
        this.numero = rand.nextInt(900000000) + 100000000;
        return numero;
    }

    public void fecharPedido(){
        this.valorTotal = BigDecimal.valueOf(0);
        for(Item x: this.itens){
            this.valorTotal = this.valorTotal.add(x.getPrecoUnitario());
        }
        pedidoAberto = false;
        
    }
}
