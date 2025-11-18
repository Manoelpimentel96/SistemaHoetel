/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import model.Funcionario;

/**
 *
 * @author manoelpimentel
 */
import model.Funcionario;

public class UsuarioLogado {

    private static Funcionario usuario;
    private static final List<Consumer<Funcionario>> ouvintes = new ArrayList<>();

    /**
     * Define o usuário logado no sistema e notifica os ouvintes.
     */
    public static void setUsuario(Funcionario f) {
        usuario = f;
        notificarMudanca();
    }

    /**
     * Retorna o usuário logado no momento.
     */
    public static Funcionario getUsuario() {
        return usuario;
    }

    /**
     * Limpa o usuário logado (logout) e notifica os ouvintes.
     */
    public static void limpar() {
        usuario = null;
        notificarMudanca();
    }

    /**
     * Adiciona um ouvinte que será notificado sempre que o usuário for
     * alterado.
     *
     * @param listener função que recebe o usuário atualizado
     */
    public static void addOuvinte(Consumer<Funcionario> listener) {
        ouvintes.add(listener);
    }

    /**
     * Notifica todos os ouvintes sobre alterações no usuário logado.
     */
    private static void notificarMudanca() {
        for (Consumer<Funcionario> l : ouvintes) {
            l.accept(usuario);
        }
    }
 
}
