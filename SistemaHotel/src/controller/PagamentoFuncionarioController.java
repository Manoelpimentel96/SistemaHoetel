/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FuncionarioDAO;
import dao.PagamentoFuncionarioDAO;
import java.util.List;
import model.Funcionario;
import model.PagamentoFuncionario;

/**
 *
 * @author manoelpimentel
 */
public class PagamentoFuncionarioController {

    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final PagamentoFuncionarioDAO pagamentoDAO = new PagamentoFuncionarioDAO();

    // Buscar funcionário por CPF
    public Funcionario buscarPorCpf(String cpf) {
        return funcionarioDAO.buscarPorCpf(cpf);
    }

    // uscar por ID (usado ao clicar no botão Finalizar Pagamento)
    public Funcionario buscarPorId(int id) {
        return funcionarioDAO.buscarPorId(id);
    }

    // Listar todos (útil para tabelas)
    public List<Funcionario> listarTodos() {
        return funcionarioDAO.listarTodos();
    }

    // Registrar pagamento
    public void registrarPagamento(PagamentoFuncionario pagamento) {
        pagamentoDAO.inserir(pagamento);
    }

    // Atualizar status do funcionário após pagamento
    public void atualizarStatusPagamento(Funcionario funcionario) {
        funcionarioDAO.atualizarStatusPagamento(funcionario);
    }

    // Verificação opcional: pagamento já feito?
    public boolean existePagamentoNoPeriodo(int funcionarioId, int mes, int ano) {
        return pagamentoDAO.existePagamentoNoPeriodo(funcionarioId, mes, ano);
    }

}
