/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import model.PrecoQuarto;
import util.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manoelpimentel
 */
public class PrecoQuartoDAO {

    public boolean cadastrar(PrecoQuarto p) {
        String sql = "INSERT INTO precos_quartos (quarto_id, ocupacao, preco_diaria, data_inicio, data_fim) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, p.getQuartoId());
            stmt.setInt(2, p.getOcupacao());
            stmt.setDouble(3, p.getPreco());
            if (p.getDataInicio() != null) {
                stmt.setDate(4, Date.valueOf(p.getDataInicio()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            if (p.getDataFim() != null) {
                stmt.setDate(5, Date.valueOf(p.getDataFim()));
            } else {
                stmt.setNull(5, Types.DATE);
            }

            int affected = stmt.executeUpdate();
            if (affected == 0) {
                return false;
            }
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    p.setId(keys.getInt(1));
                }
            }
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar preço: " + ex.getMessage(), ex);
        }
    }

    public List<PrecoQuarto> listarPorQuarto(int quartoId) {
        List<PrecoQuarto> lista = new ArrayList<>();
        String sql = "SELECT id, quarto_id, ocupacao, preco_diaria, data_inicio, data_fim FROM precos_quartos WHERE quarto_id = ? ORDER BY ocupacao";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quartoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PrecoQuarto p = new PrecoQuarto();
                    p.setId(rs.getInt("id"));
                    p.setQuartoId(rs.getInt("quarto_id"));
                    p.setOcupacao(rs.getInt("ocupacao"));
                    p.setPreco(rs.getDouble("preco_diaria"));
                    Date d1 = rs.getDate("data_inicio");
                    Date d2 = rs.getDate("data_fim");
                    if (d1 != null) {
                        p.setDataInicio(d1.toLocalDate());
                    }
                    if (d2 != null) {
                        p.setDataFim(d2.toLocalDate());
                    }
                    lista.add(p);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar preços: " + ex.getMessage(), ex);
        }
        return lista;
    }

    public List<PrecoQuarto> listarPorQuartoId(int quartoId) {
        List<PrecoQuarto> lista = new ArrayList<>();

        String sql = "SELECT id, quarto_id, ocupacao, preco_diaria, data_inicio, data_fim "
                + "FROM precos_quartos WHERE quarto_id = ? ORDER BY ocupacao ASC";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quartoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PrecoQuarto p = new PrecoQuarto();
                    p.setId(rs.getInt("id"));
                    p.setQuartoId(rs.getInt("quarto_id"));
                    p.setOcupacao(rs.getInt("ocupacao"));
                    p.setPreco(rs.getDouble("preco_diaria")); // coluna certa

                    Date di = rs.getDate("data_inicio");
                    Date df = rs.getDate("data_fim");

                    if (di != null) {
                        p.setDataInicio(di.toLocalDate());
                    }
                    if (df != null) {
                        p.setDataFim(df.toLocalDate());
                    }

                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar preços do quarto: " + e.getMessage(), e);
        }

        return lista;
    }

    public boolean excluirPorId(int id) {
        String sql = "DELETE FROM precos_quartos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir preço: " + ex.getMessage(), ex);
        }
    }
}
