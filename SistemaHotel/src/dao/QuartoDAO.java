/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Quarto;
import java.sql.*;
import java.util.*;
import util.ConnectionFactory;

/**
 *
 * @author manoelpimentel
 */
public class QuartoDAO {

    public int cadastrarRetornandoId(Quarto q) {
        String sql = "INSERT INTO quartos (numero, tipo, numCamas, status, "
                + "observacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, String.valueOf(q.getNumero()));
            stmt.setString(2, q.getTipo().toUpperCase());
            stmt.setInt(3, q.getNumCamas());
            stmt.setString(4, q.getStatus().toUpperCase());
            stmt.setString(5, q.getObservacao());
            int affected = stmt.executeUpdate();
            if (affected == 0) {
                return -1;
            }
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            return -1;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao cadastrar quarto: " + ex.getMessage(), ex);
        }
    }

    public boolean cadastrar(Quarto q) {
        return cadastrarRetornandoId(q) > 0;
    }

    public List<Quarto> listarTodos() {
        List<Quarto> lista = new ArrayList<>();
        String sql = "SELECT id, numero, tipo, numCamas, status, observacao,"
                + " criado_em FROM quartos ORDER BY id DESC";
        try (Connection conn = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Quarto q = new Quarto();
                q.setId(rs.getInt("id"));
                q.setNumero(Integer.parseInt(rs.getString("numero")));
                q.setTipo(rs.getString("tipo"));
                q.setNumCamas(rs.getInt("numCamas"));
                q.setStatus(rs.getString("status"));
                q.setObservacao(rs.getString("observacao"));
                Timestamp t = rs.getTimestamp("criado_em");
                if (t != null) {
                    q.setCriadoEm(t.toLocalDateTime().toLocalDate());
                }
                lista.add(q);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar quartos: " + ex.getMessage(), ex);
        }
        return lista;
    }

    public Quarto buscarPorNumero(int numero) {
        String sql = "SELECT * FROM quartos WHERE numero = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(numero));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToQuarto(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro buscar por número: " + ex.getMessage(), ex);
        }
        return null;
    }

    public Quarto buscarPorId(int id) {
        String sql = "SELECT * FROM quartos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToQuarto(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro buscar por id: " + ex.getMessage(), ex);
        }
        return null;
    }

    public boolean atualizar(Quarto q) {
        String sql = "UPDATE quartos SET numero = ?, tipo = ?, numCamas = ?, status = ?, observacao = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(q.getNumero()));
            stmt.setString(2, q.getTipo().toUpperCase());
            stmt.setInt(3, q.getNumCamas());
            stmt.setString(4, q.getStatus().toUpperCase());
            stmt.setString(5, q.getObservacao());
            stmt.setInt(6, q.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar quarto: " + ex.getMessage(), ex);
        }
    }

    /**
     * Exclui o quarto apenas se estiver DISPONIVEL. Remove preços vinculados
     * antes de remover o quarto.
     */
    public boolean excluirQuartoIfDisponivel(int id) {
        String checkSql = "SELECT status FROM quartos WHERE id = ?";
        String deletePrecos = "DELETE FROM precos_quartos WHERE quarto_id = ?";
        String deleteQuarto = "DELETE FROM quartos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            conn.setAutoCommit(false);
            checkStmt.setInt(1, id);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (!rs.next()) {
                    conn.rollback();
                    return false;
                }
                String status = rs.getString("status");
                if (!"DISPONIVEL".equalsIgnoreCase(status)) {
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement delPre = conn.prepareStatement(deletePrecos); 
                    PreparedStatement delQ = conn.prepareStatement(deleteQuarto)) {
                delPre.setInt(1, id);
                delPre.executeUpdate();

                delQ.setInt(1, id);
                int affected = delQ.executeUpdate();
                if (affected > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir quarto: " + ex.getMessage(), ex);
        }
    }

    private Quarto mapRowToQuarto(ResultSet rs) throws SQLException {
        Quarto q = new Quarto();
        q.setId(rs.getInt("id"));
        q.setNumero(Integer.parseInt(rs.getString("numero")));
        q.setTipo(rs.getString("tipo"));
        q.setNumCamas(rs.getInt("numCamas"));
        q.setStatus(rs.getString("status"));
        q.setObservacao(rs.getString("observacao"));
        Timestamp t = rs.getTimestamp("criado_em");
        if (t != null) {
            q.setCriadoEm(t.toLocalDateTime().toLocalDate());
        }
        return q;
    }

}
