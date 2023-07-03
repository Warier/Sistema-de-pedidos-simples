package com.warier.projeto.Controller;

import com.warier.projeto.Model.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements Serializable{

    private static long serialVersionUID = 1L;

    private Pedido pedido = null;
    private ClienteDAO cliDAO = new ClienteDAO();
    private ItemDAO itDAO = new ItemDAO();
    private PedidoDAO pedDAO = new PedidoDAO();
    private File report = new File(System.getProperty("user.dir") + "/report");

    private Map parametroCliente(String cpf) {
        Map params = new HashMap();
        params.put("cpf", cpf);
        return params;
    }

    public void relatorioCliente(String cpf){
        JasperPrint impressao;
        try{
            FileInputStream rel = new FileInputStream(new File(report, "Cliente.jasper"));
            impressao = JasperFillManager.fillReport(rel, parametroCliente(cpf), Conexao.getConnection());
            JasperViewer.viewReport(impressao, false);
        } catch (FileNotFoundException | JRException | RuntimeException e) {
            System.err.println("Não foi possível exportar o relatório.\n\n" + e);
        }
    }

    public void relatorioItens(){

        JasperPrint impressao;
            try{
                FileInputStream rel = new FileInputStream(new File(report, "itens.jasper"));
                impressao = JasperFillManager.fillReport(rel, null, Conexao.getConnection());
                JasperViewer.viewReport(impressao, false);
            } catch (FileNotFoundException | JRException | RuntimeException e) {
                System.err.println("Não foi possível exportar o relatório.\n\n" + e);
            }
    }

    public void relatorioPedido(){

        JasperPrint impressao;
        try{
            FileInputStream rel = new FileInputStream(new File(report, "Pedidos.jasper"));
            impressao = JasperFillManager.fillReport(rel, null, Conexao.getConnection());
            JasperViewer.viewReport(impressao, false);
        } catch (FileNotFoundException | JRException | RuntimeException e) {
            System.err.println("Não foi possível exportar o relatório.\n\n" + e);
        }
    }

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
            return new ArrayList<Item>();
        }
    }

    public List<Cliente> getClientes() {
        try {
            return cliDAO.listar();
        }catch (RuntimeException e){
            return new ArrayList<Cliente>();
        }
    }

    public List<Pedido> getPedidos() {
        try {
            return pedDAO.listar();
        }catch (RuntimeException e){
            return new ArrayList<Pedido>();
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
