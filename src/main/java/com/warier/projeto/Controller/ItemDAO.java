package com.warier.projeto.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.warier.projeto.Model.Item;

public class ItemDAO {

    private static final String sqlConsulta = "SELECT preco, descricao FROM Itens WHERE nome = ?";
    private static final String sqlCadastra = "INSERT INTO Itens (nome, preco, descricao) VALUES (?, ?, ?)";
    private static final String sqlExcluir = "DELETE FROM Itens WHERE nome = ?";
    private static final String sqlAlterar = "UPDATE Itens SET preco = ?, descricao = ? WHERE nome = ?";
    private static final String sqlTabela = "SELECT nome, preco, descricao FROM Itens";

    public void cadastrar(Item item) {

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlCadastra);
        ) {

            stmt.setString(1, item.getNome());
            stmt.setBigDecimal(2, item.getPrecoUnitario());
            stmt.setString(3, item.getDescricao());

            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void apagar(Item item) {
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlExcluir);
        ) {

            stmt.setString(1, item.getNome());

            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Item item) {
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlAlterar);
        ) {

            stmt.setBigDecimal(1, item.getPrecoUnitario());
            stmt.setString(2, item.getDescricao());
            stmt.setString(3, item.getNome());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Item consultar(Item item) {
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlConsulta);
        ) {
            stmt.setString(1, item.getNome());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Item ite = new Item(item.getNome(), rs.getBigDecimal("preco"), rs.getString("descricao"));
                return ite;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Item> listar() {
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlTabela);
        ) {
            ResultSet rs = stmt.executeQuery();
            List<Item> itens = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item(rs.getString("nome"), rs.getBigDecimal("preco"), rs.getString("descricao"));
                itens.add(item);
            }
            return itens;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
