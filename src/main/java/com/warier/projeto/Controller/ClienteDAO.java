package com.warier.projeto.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.warier.projeto.Model.Cliente;

public class ClienteDAO {

    private static final String sqlConsulta = "SELECT nome, telefone FROM Clientes WHERE RG = ?";
    private static final String sqlCadastra = "INSERT INTO Clientes (RG, nome, telefone) VALUES (?, ?, ?)";
    private static final String sqlaexcluir = "DELETE FROM Clientes WHERE RG = ?";
    private static final String sqlalterar = "UPDATE Clientes SET nome = ?, telefone = ? WHERE RG = ?";
    private static final String sqlTabela = "SELECT RG, nome, telefone FROM Clientes";



    public void cadastrar(Cliente cliente) {

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlCadastra);
        ) {

            stmt.setString(1, cliente.getEndereco());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());

            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void apagar(Cliente cliente) {
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlaexcluir);
        ) {

            stmt.setString(1, cliente.getEndereco());

            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Cliente cliente) {
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlalterar);
        ) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEndereco());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente consultar(Cliente cliente) {
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlConsulta);
        ) {
            stmt.setString(1, cliente.getEndereco());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cli = new Cliente(rs.getString("nome"), cliente.getEndereco(), rs.getString("telefone"));
                return cli;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> listar() {
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlTabela);
        ) {
            ResultSet rs = stmt.executeQuery();
            List<Cliente> clientes = new ArrayList<>();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("RG"), rs.getString("telefone"));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

