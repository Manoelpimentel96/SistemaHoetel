/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.QuartoDAO;
import java.util.List;
import model.Quarto;

/**
 *
 * @author manoelpimentel
 */
public class QuartoController {

    private final QuartoDAO dao = new QuartoDAO();

    public boolean cadastrarQuarto(Quarto q) {
        return dao.cadastrar(q);
    }

    public int cadastrarRetornandoId(Quarto q) {
        return dao.cadastrarRetornandoId(q);
    }

    public List<Quarto> listarTodos() {
        return dao.listarTodos();
    }

    public Quarto buscarPorNumero(int numero) {
        return dao.buscarPorNumero(numero);
    }

    public Quarto buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public boolean atualizarQuarto(Quarto q) {
        return dao.atualizar(q);
    }

    public boolean excluirQuartoIfDisponivel(int id) {
        return dao.excluirQuartoIfDisponivel(id);
    }

}
