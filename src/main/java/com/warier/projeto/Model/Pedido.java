package com.warier.projeto.Model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Pedido {
    private int numero;
    private String dataHora;
    private List<Item> itens;
    private BigDecimal valorTotal;
    private Cliente cliente;

    private boolean pedidoAberto;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this. gerarNumeroDePedido();
        Date dataHora = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.dataHora = formatador.format(dataHora);
        pedidoAberto = true;
    }

    public int getNumero() {
        return numero;
    }

    public String getDataHora() {
        return dataHora;
    }

    public List<Item> getItens() {
        return itens;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int gerarNumeroDePedido() {
        Random rand = new Random();
        int numeroDePedido = rand.nextInt(900000000) + 100000000;
        return numeroDePedido;
    }

    public BigDecimal fecharPedido(){
        for(Item x: this.itens){
            this.valorTotal.add(x.getPrecoUnitario());
        }
        pedidoAberto = false;
        return this.valorTotal;
    }
}
