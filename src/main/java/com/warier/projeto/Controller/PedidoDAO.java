package com.warier.projeto.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.warier.projeto.Model.Cliente;
import com.warier.projeto.Model.Pedido;

public class PedidoDAO {

    private static final String sqlConsulta = "SELECT P.idPedidos, P.ValorTotal, C.RG, C.nome FROM Pedidos P INNER JOIN Clientes C ON P.Clientes_RG = C.RG WHERE P.idPedidos = ?";
    private static final String sqlCadastra = "INSERT INTO Pedidos (idPedidos, Clientes_RG, ValorTotal) VALUES (?, ?, ?)";
    private static final String sqlTabela = "SELECT P.idPedidos, P.ValorTotal, C.RG, C.nome FROM Pedidos P INNER JOIN Clientes C ON P.Clientes_RG = C.RG";

    public void cadastrar(Pedido pedido) {

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlCadastra);
        ) {

            stmt.setInt(1, pedido.getNumero());
            stmt.setString(2, pedido.getCliente().getEndereco());
            stmt.setBigDecimal(3, pedido.getValorTotal());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pedido consultar(Pedido pedido) {
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlConsulta);
        ) {
            stmt.setInt(1, pedido.getNumero());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("RG"));
                Pedido ped = new Pedido(rs.getInt("idPedidos"), cliente, rs.getDouble("ValorTotal"));
                return ped;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pedido> listar() {
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlTabela);
        ) {
            ResultSet rs = stmt.executeQuery();
            List<Pedido> pedidos = new ArrayList<>();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("RG"));
                Pedido pedido = new Pedido(rs.getInt("idPedidos"), cliente, rs.getDouble("ValorTotal"));
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
