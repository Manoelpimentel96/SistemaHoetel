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
public class PrecoQuarto {
    
    private int id;
    private int quartoId;
    private int ocupacao;     // 1 hóspede, 2 hóspedes, 3 hóspedes, etc.
    private double preco;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public PrecoQuarto() {
    }

    public PrecoQuarto(int id, int quartoId, int ocupacao, double preco, 
            LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.quartoId = quartoId;
        this.ocupacao = ocupacao;
        this.preco = preco;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(int quartoId) {
        this.quartoId = quartoId;
    }

    public int getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

   
}
