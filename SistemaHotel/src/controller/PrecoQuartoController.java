/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PrecoQuartoDAO;
import java.util.List;
import model.PrecoQuarto;

/**
 *
 * @author manoelpimentel
 */
public class PrecoQuartoController {

    private final PrecoQuartoDAO dao = new PrecoQuartoDAO();

    public boolean cadastrar(PrecoQuarto p) {
        return dao.cadastrar(p);
    }

    public List<PrecoQuarto> listarPorQuarto(int quartoId) {
        return dao.listarPorQuarto(quartoId);
    }

    public boolean excluirPorId(int id) {
        return dao.excluirPorId(id);
    }

     public List<PrecoQuarto> listarPrecos(int quartoId) {
        return new PrecoQuartoDAO().listarPorQuartoId(quartoId);
    }

    public List<PrecoQuarto> listarPorQuartoId(int quartoId) {
        return dao.listarPorQuartoId(quartoId);
    }
  
}

