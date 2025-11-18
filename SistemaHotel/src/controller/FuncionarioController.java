/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FuncionarioDAO;
import model.Funcionario;
import java.util.List;

/**
 *
 * @author manoelpimentel
 */
public class FuncionarioController {

    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    //tela login Funcionario
    public Funcionario autenticar(String cpf, String senha) {
        return funcionarioDAO.autenticar(cpf, senha);
    }

    //tela Cadastro Funcionario
    public void inserir(Funcionario funcionario) {
        funcionarioDAO.inserir(funcionario);
    }

    // Atualizar o Funcionario
    public void atualizar(Funcionario funcionario) {
        funcionarioDAO.atualizar(funcionario);
    }

    //tela Cadastro Funcionario
    public Funcionario buscarPorId(int id) {
        return funcionarioDAO.buscarPorId(id);
    }

    // Usado pra verificar se já existe cpf cadastrado no sistema ante de realizar 
    // um novo cadastro
    public boolean existePorCpf(String cpf) {
        return funcionarioDAO.buscarPorCpf(cpf) != null;
    }

    //tela Cadastro Funcionario
    public Funcionario buscarPorCpf(String cpf) {
        return funcionarioDAO.buscarPorCpf(cpf);
    }

    //tela Cadastro Funcionario
    public List<Funcionario> listarTodos() {
        return funcionarioDAO.listarTodos();
    }

     public void excluir(int id) {
       funcionarioDAO.tratarExclusao(id);
    }
    //tela Cadastro Funcionario
    /*public void excluir(int id) {

        if (funcionarioDAO.possuiPagamentos(id)) {
            funcionarioDAO.pausar(id);  // agora pausa
        } else {
            funcionarioDAO.excluir(id); // exclui definitivamente
        }
    }*/

}
