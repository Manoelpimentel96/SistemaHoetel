/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author manoelpimentel
 */

public class LimpezaQuarto {

    private int id;
    private int quartoId;
    private Date dataLimpeza;
    private String quartoLimpo;
    private String trocaRoupaCama;
    private String insumosRepostos;
    private String funcionarioNome;
    private Date criadoEm;

    public LimpezaQuarto() {}

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

    public Date getDataLimpeza() {
        return dataLimpeza;
    }

    public void setDataLimpeza(Date dataLimpeza) {
        this.dataLimpeza = dataLimpeza;
    }

    public String getQuartoLimpo() {
        return quartoLimpo;
    }

    public void setQuartoLimpo(String quartoLimpo) {
        this.quartoLimpo = quartoLimpo;
    }

    public String getTrocaRoupaCama() {
        return trocaRoupaCama;
    }

    public void setTrocaRoupaCama(String trocaRoupaCama) {
        this.trocaRoupaCama = trocaRoupaCama;
    }

    public String getInsumosRepostos() {
        return insumosRepostos;
    }

    public void setInsumosRepostos(String insumosRepostos) {
        this.insumosRepostos = insumosRepostos;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }
}

