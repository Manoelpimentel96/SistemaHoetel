/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.PagamentoFuncionario;
import util.ConnectionFactory;

/**
 *
 * @author manoelpimentel
 */
public class PagamentoFuncionarioDAO {

    public static void inserir(PagamentoFuncionario pagamento) {
        String sql = "INSERT INTO pagamentos_funcionarios "
                + "(funcionario_id, aumento, desconto, valor_pago, data_liberacao, "
                + "data_efetuado, status_pagamento, responsavel_id, mes_pagamento, ano_pagamento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pagamento.getFuncionario().getId());
            stmt.setDouble(2, pagamento.getAumento());
            stmt.setDouble(3, pagamento.getDesconto());
            stmt.setDouble(4, pagamento.getValorPago());
            stmt.setDate(5, java.sql.Date.valueOf(pagamento.getDataLiberacao()));

            // Se não tiver data_efetuado, use null
            if (pagamento.getDataEfetuado() != null) {
                stmt.setTimestamp(6, Timestamp.valueOf(pagamento.getDataEfetuado()));
            } else {
                stmt.setNull(6, java.sql.Types.TIMESTAMP);
            }

            stmt.setString(7, pagamento.getStatusPagamento());
            stmt.setInt(8, pagamento.getResponsavel().getId());

            // EXTRA: evitar duplicidade
            stmt.setInt(9, pagamento.getDataLiberacao().getMonthValue());
            stmt.setInt(10, pagamento.getDataLiberacao().getYear());

            stmt.executeUpdate();

        } catch (SQLException e) {

            if (e.getMessage().contains("unq_pagamento_periodo")) {
                throw new RuntimeException(
                        "Já existe um pagamento registrado para este funcionário neste período."
                );
            }

            throw new RuntimeException("Erro ao inserir pagamento: " + e.getMessage(), e);
        }
    }

    // Verificar se já existe pagamento para o perio
    public static boolean existePagamentoNoPeriodo(int funcionarioId, int mes, int ano) {

        String sql = "SELECT COUNT(*) FROM pagamentos_funcionarios "
                + "WHERE funcionario_id = ? "
                + "AND MONTH(data_liberacao) = ? "
                + "AND YEAR(data_liberacao) = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, funcionarioId);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            return false;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar pagamento no período: " + e.getMessage(), e);
        }
    }

    // Implementando o historico de pagamento 
    /*public boolean temHistorico(int funcionarioId) {
        String sql = "SELECT COUNT(*) FROM pagamentos_funcionarios WHERE id_funcionario = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, funcionarioId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar histórico: " + e.getMessage());
        }

        return false;
    }

    public List<PagamentoFuncionario> buscarHistoricoPorPeriodo(
            int funcionarioId, int mesInicio, int mesFim, int ano) {

        List<PagamentoFuncionario> lista = new ArrayList<>();

        String sql = """
        SELECT * FROM pagamentos_funcionarios
        WHERE id_funcionario = ?
        AND MONTH(data_liberacao) BETWEEN ? AND ?
        AND YEAR(data_liberacao) = ?
        ORDER BY data_liberacao ASC
    """;

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, funcionarioId);
            stmt.setInt(2, mesInicio);
            stmt.setInt(3, mesFim);
            stmt.setInt(4, ano);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PagamentoFuncionario p = new PagamentoFuncionario();
                p.setId(rs.getInt("id"));
                p.setAumento(rs.getDouble("aumento"));
                p.setDesconto(rs.getDouble("desconto"));
                p.setValorPago(rs.getDouble("valor_pago"));
                p.setDataLiberacao(rs.getDate("data_liberacao").toLocalDate());
                p.setDataEfetuado(rs.getTimestamp("data_efetuado").toLocalDateTime());
                p.setStatusPagamento(rs.getString("status_pagamento"));

                lista.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar histórico: " + e.getMessage());
        }

        return lista;
    }*/

}
