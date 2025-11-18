/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import util.ConnectionFactory;
import util.Criptografia;

/**
 *
 * @author manoelpimentel
 */
public class FuncionarioDAO {

    // Metodo da tela Login pra autenticar o login
    public Funcionario autenticar(String cpf, String senhaDigitada) {
        String sqlBusca = "SELECT * FROM funcionarios WHERE cpf = ?";

        cpf = cpf.replaceAll("\\D", ""); // remove máscara

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlBusca)) {
            //stmt.setString(1, cpf.replaceAll("\\D", ""));
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            // Aqui; Verifica se CPF existe!
            if (!rs.next()) {
                throw new RuntimeException("CPF não encontrado no cadastro do sistema!");
            }

            //  Aqui; Verifica se usuário está ativo!
            if (!"ATIVO".equalsIgnoreCase(rs.getString("status"))) {
                throw new RuntimeException("Usuário inativo. Contate o administrador.");
            }

            // Aqui; verificar se a senha esta correta!
            String senhaHashDigitada = Criptografia.gerarHash(senhaDigitada);
            String senhaHashBanco = rs.getString("password_hash");

            if (!senhaHashDigitada.equals(senhaHashBanco)) {
                throw new RuntimeException("Senha incorreta!");
            }

            // Aqui; Se passou por tudo, cria o objeto Funcionario!
            Funcionario f = new Funcionario();
            f.setId(rs.getInt("id"));
            f.setNome(rs.getString("nome"));
            f.setCpf(rs.getString("cpf"));
            f.setCargo(rs.getString("cargo"));
            f.setEmail(rs.getString("email"));
            f.setTelefone(rs.getString("telefone"));
            f.setSalario(rs.getDouble("salario"));
            f.setRoleId(rs.getInt("role_id"));
            f.setLogin(rs.getString("login"));
            f.setStatus(rs.getString("status"));
            f.setSenha(rs.getString("password_hash"));
            return f;

        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco de dados: " + e.getMessage());
        }
    }

    // Tela de Cadastro Funcionario
    // Cadastra um funcionario no sistema!
    public void inserir(Funcionario f) {
        if (cpfExiste(f.getCpf())) {
            throw new RuntimeException("CPF já cadastrado.");
        }
        if (loginExiste(f.getLogin())) {
            throw new RuntimeException("Login já está em uso.");
        }

        String sql = "INSERT INTO funcionarios (nome, cpf, cargo, email, telefone, "
                + "data_admissao, salario, role_id, login, password_hash, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCpf().replaceAll("\\D", ""));
            stmt.setString(3, f.getCargo());
            stmt.setString(4, f.getEmail());
            stmt.setString(5, f.getTelefone());
            stmt.setDate(6, java.sql.Date.valueOf(f.getDataAdmissao()));
            stmt.setDouble(7, f.getSalario());
            stmt.setInt(8, f.getRoleId());
            stmt.setString(9, f.getLogin());
            stmt.setString(10, f.getSenha());
            stmt.setString(11, f.getStatus());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    // Tela cadastro funcionario
    // Atualizar os dados do funcionario!
    public void atualizar(Funcionario f) {
        String sql = "UPDATE funcionarios SET nome = ?, cpf = ?, cargo = ?, email = ?, telefone = ?, \n"
                + "   salario = ?, status = ?, login = ?, password_hash = ?, role_id = ?, data_admissao = ?\n"
                + "   WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCpf().replaceAll("\\D", ""));
            stmt.setString(3, f.getCargo());
            stmt.setString(4, f.getEmail());
            stmt.setString(5, f.getTelefone());
            stmt.setDouble(6, f.getSalario());
            stmt.setString(7, f.getStatus());
            stmt.setString(8, f.getLogin());
            stmt.setString(9, f.getSenha());
            stmt.setInt(10, f.getRoleId());
            stmt.setDate(11, java.sql.Date.valueOf(f.getDataAdmissao()));
            stmt.setInt(12, f.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    // Excluir funcionario sem pagamento
    // btnExcluir
    public void excluirDefinitivo(int id) {
        String sql = "DELETE FROM funcionarios WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir funcionário: " + e.getMessage());
        }
    }
//Ajustando aqui
    // Verifica se existe pagamento relacionado
    // Verifica se existe pagamento vinculado ao funcionário

    public boolean possuiPagamentos(int idFuncionario) {
        String sql = "SELECT COUNT(*) FROM pagamentos_funcionarios WHERE funcionario_id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar pagamentos: " + e.getMessage());
        }
    }

    public void pausar(int id) {
        String sql = "UPDATE funcionarios SET status = 'PAUSADO', data_pausa = CURDATE() WHERE id=?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao pausar funcionário: " + e.getMessage());
        }
    }

    public void atualizarFuncionariosPausados() {
        String sql
                = "UPDATE funcionarios "
                + "SET status = 'INATIVO' "
                + "WHERE status = 'PAUSADO' "
                + "AND data_pausa <= DATE_SUB(CURDATE(), INTERVAL 60 DAY)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionários pausados: " + e.getMessage());
        }
    }

    //
    // Tela cadastro e login
    public Funcionario buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM funcionarios WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf.replaceAll("\\D", ""));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setCargo(rs.getString("cargo"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setLogin(rs.getString("login"));
                f.setSalario(rs.getDouble("salario"));
                f.setStatus(rs.getString("status"));
                f.setDataAdmissao(rs.getDate("data_admissao").toLocalDate());
                // correção
                f.setStatus_pag_salario(rs.getString("status_pag_salario"));
                Date dt = rs.getDate("data_liberacao_salario");
                f.setData_liberacao_salario(dt != null ? dt.toLocalDate() : null);
                return f;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionário: " + e.getMessage());
        }
        return null;
    }

    // tela cadastro funcionario
    // radio lista todos
    public List<Funcionario> listarTodos() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setCargo(rs.getString("cargo"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setLogin(rs.getString("login"));
                f.setSalario(rs.getDouble("salario"));
                f.setStatus(rs.getString("status"));
                f.setDataAdmissao(rs.getDate("data_admissao").toLocalDate());

                //CAMPOS QUE ESTÃO FALTANDO
                f.setStatus_pag_salario(rs.getString("status_pag_salario"));
                Date dt = rs.getDate("data_liberacao_salario");
                f.setData_liberacao_salario(dt != null ? dt.toLocalDate() : null);

                lista.add(f);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage());
        }
        return lista;
    }

    // Tela login e cadastro
    private boolean cpfExiste(String cpf) {
        String sql = "SELECT COUNT(*) FROM funcionarios WHERE cpf=?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf.replaceAll("\\D", ""));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar CPF: " + e.getMessage());
        }
        return false;
    }

    // Aqui foi uma implementação opcional minha pra teste
    private boolean loginExiste(String login) {
        String sql = "SELECT COUNT(*) FROM funcionarios WHERE login=?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar login: " + e.getMessage());
        }
        return false;
    }

    // Usado no atualizar | senha 
    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM funcionarios WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setCargo(rs.getString("cargo"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setLogin(rs.getString("login"));
                f.setSenha(rs.getString("password_hash")); // importante!
                f.setStatus(rs.getString("status"));
                f.setDataAdmissao(rs.getDate("data_admissao").toLocalDate());
                f.setSalario(rs.getDouble("salario"));

                return f;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionário por ID: " + e.getMessage());
        }
        return null;
    }

    // Metodos da tela TelaLiberarPagamentoSalario
    // Método usado pela TelaLiberarPagamentoSalario
    public static void atualizarStatusPagamento(Funcionario funcionario) {

        String sql = "UPDATE funcionarios SET status_pag_salario = ?, data_liberacao_salario = ? "
                + "WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getStatus_pag_salario());
            stmt.setDate(2, java.sql.Date.valueOf(funcionario.getData_liberacao_salario()));
            stmt.setInt(3, funcionario.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status do pagamento: " + e.getMessage(), e);
        }
    }

    // Metodo pra atualizar pagamento
    public static void atualizarStatusAutomatico() {

        try (Connection conn = ConnectionFactory.getConnection()) {

            // LIBERADO → EFETUADO quando chega a data
            String sqlEfetuado
                    = "UPDATE funcionarios "
                    + "SET status_pag_salario = 'EFETUADO' "
                    + "WHERE status_pag_salario = 'LIBERADO' "
                    + "AND data_liberacao_salario = CURDATE()";

            conn.prepareStatement(sqlEfetuado).executeUpdate();

            // Novo mês → VOLTA PARA PENDENTE
            String sqlPendente
                    = "UPDATE funcionarios "
                    + "SET status_pag_salario = 'PENDENTE', "
                    + "data_liberacao_salario = NULL "
                    + "WHERE MONTH(data_liberacao_salario) < MONTH(CURDATE()) "
                    + "OR YEAR(data_liberacao_salario) < YEAR(CURDATE())";

            conn.prepareStatement(sqlPendente).executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro na atualização automática: " + e.getMessage(), e);
        }
    }

    // CHAMADO NO EXCLUIR
    public void tratarExclusao(int id) {
        if (possuiPagamentos(id)) {
            pausar(id);
        } else {
            excluirDefinitivo(id);
        }
    }
}
