/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author manoelpimentel
 */
public class Quarto {
    
    private int id;
    private int numero;
    private String tipo;       // Simples, Duplo, Suíte
    private int numCamas;
    private String status;     // Disponível, Ocupado, Bloqueado
    private String observacao;
    private LocalDate criadoEm;

    // Lista de Preços vinculados
    private List<PrecoQuarto> precos;

    public Quarto() {
    }

    public Quarto(int id, int numero, String tipo, int numCamas, 
            String status, String observacao, LocalDate criadoEm, List<PrecoQuarto> precos) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.numCamas = numCamas;
        this.status = status;
        this.observacao = observacao;
        this.criadoEm = criadoEm;
        this.precos = precos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumCamas() {
        return numCamas;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDate criadoEm) {
        this.criadoEm = criadoEm;
    }

    public List<PrecoQuarto> getPrecos() {
        return precos;
    }

    public void setPrecos(List<PrecoQuarto> precos) {
        this.precos = precos;
    }
  
}
