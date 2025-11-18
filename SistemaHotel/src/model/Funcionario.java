/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author manoelpimentel
 */
public class Funcionario {

    private int id;
    private String nome;
    private String cpf; // Vai ser usado para realizar o login
    private String cargo; // Selecione, Administração, Gerente, Recepcionista, Porteilro etc..
    private String email;
    private String telefone;
    private LocalDate dataAdmissao; // a data em que o funcionario foi contratado
    private double salario; // vai amarzena o valor do salario base
    private int roleId; // ID da função/cargo no sistema
    private String roleNome; // 
    private String login; // Nome de usuario
    private String senha;// Salvar com criptografia
    private String status; // Ativo / Inativo e Pausado
    private String status_pag_salario; // PENDENTE, LIBERADO, EFETUADO
    private LocalDate data_liberacao_salario; // Quando o salário foi liberado

    public Funcionario() {
    }

    public Funcionario(int id, String nome, String cpf, String cargo, String email,
            String telefone, LocalDate dataAdmissao, double salario, int roleId,
            String roleNome, String login, String senha, String status,
            String status_pag_salario, LocalDate data_liberacao_salario) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.email = email;
        this.telefone = telefone;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
        this.roleId = roleId;
        this.roleNome = roleNome;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.status_pag_salario = status_pag_salario;
        this.data_liberacao_salario = data_liberacao_salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleNome() {
        return roleNome;
    }

    public void setRoleNome(String roleNome) {
        this.roleNome = roleNome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_pag_salario() {
        return status_pag_salario;
    }

    public void setStatus_pag_salario(String status_pag_salario) {
        this.status_pag_salario = status_pag_salario;
    }

    public LocalDate getData_liberacao_salario() {
        return data_liberacao_salario;
    }

    public void setData_liberacao_salario(LocalDate data_liberacao_salario) {
        this.data_liberacao_salario = data_liberacao_salario;
    }

    public static int obterRoleIdPorCargo(String cargo) {
        switch (cargo) {
            case "Administração":
                return 1;
            case "Gerente":
                return 2;
            case "Recepcionista":
                return 3;
            case "Porteilro":
                return 4;
            default:
                return 3;
        }
    }

}
