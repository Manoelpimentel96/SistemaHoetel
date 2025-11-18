/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author manoelpimentel
 */
public class PagamentoFuncionario {

    private int id;
    private Funcionario funcionario;       // Associação direta ao funcionário
    private double aumento;
    private double desconto;
    private double valorPago;
    private LocalDate dataLiberacao;
    private LocalDateTime dataEfetuado;
    private String statusPagamento;
    private int mesPagamento;
    private int anoPagamento;

    private Funcionario responsavel;       // Funcionário que liberou o pagamento

    public PagamentoFuncionario() {
    }

    public PagamentoFuncionario(int id, Funcionario funcionario, double aumento,
            double desconto, double valorPago, LocalDate dataLiberacao, 
            LocalDateTime dataEfetuado, String statusPagamento, int mesPagamento, 
            int anoPagamento, Funcionario responsavel) {
        this.id = id;
        this.funcionario = funcionario;
        this.aumento = aumento;
        this.desconto = desconto;
        this.valorPago = valorPago;
        this.dataLiberacao = dataLiberacao;
        this.dataEfetuado = dataEfetuado;
        this.statusPagamento = statusPagamento;
        this.mesPagamento = mesPagamento;
        this.anoPagamento = anoPagamento;
        this.responsavel = responsavel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public double getAumento() {
        return aumento;
    }

    public void setAumento(double aumento) {
        this.aumento = aumento;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataLiberacao() {
        return dataLiberacao;
    }

    public void setDataLiberacao(LocalDate dataLiberacao) {
        this.dataLiberacao = dataLiberacao;
    }

    public LocalDateTime getDataEfetuado() {
        return dataEfetuado;
    }

    public void setDataEfetuado(LocalDateTime dataEfetuado) {
        this.dataEfetuado = dataEfetuado;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public int getMesPagamento() {
        return mesPagamento;
    }

    public void setMesPagamento(int mesPagamento) {
        this.mesPagamento = mesPagamento;
    }

    public int getAnoPagamento() {
        return anoPagamento;
    }

    public void setAnoPagamento(int anoPagamento) {
        this.anoPagamento = anoPagamento;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }
   
}