/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author manoelpimentel
 */
import javax.swing.*;
import java.util.regex.Pattern;

public class ValidaEmail {
    // Aplica no customizar nas telas
    // CampoMoeda.aplicar(campoSalario);
    private static final Pattern PADRAO_EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    public static boolean validarCampo(JTextField campo) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo de e-mail não pode estar vazio!");
            campo.requestFocus();
            return false;
        }
        if (!PADRAO_EMAIL.matcher(texto).matches()) {
            JOptionPane.showMessageDialog(null, "E-mail inválido. Use um formato como exemplo@dominio.com");
            campo.requestFocus();
            return false;
        }
        return true;
    }
}
