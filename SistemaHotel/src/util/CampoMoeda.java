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
import javax.swing.text.*;
import java.text.NumberFormat;
import java.util.Locale;

public class CampoMoeda extends PlainDocument {
    // Aplica no customizar nas telas
    // if (!ValidaEmail.validarCampo(campoEmail)) return;
    private final NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) return;

        String textoAtual = getText(0, getLength()).replaceAll("[^0-9]", "");
        String novoTexto = textoAtual + str.replaceAll("[^0-9]", "");
        double valor = Double.parseDouble(novoTexto) / 100;
        super.remove(0, getLength());
        super.insertString(0, formato.format(valor), a);
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        String textoAtual = getText(0, getLength()).replaceAll("[^0-9]", "");
        if (textoAtual.length() > 0) {
            textoAtual = textoAtual.substring(0, Math.max(0, textoAtual.length() - len));
            double valor = textoAtual.isEmpty() ? 0 : Double.parseDouble(textoAtual) / 100;
            super.remove(0, getLength());
            super.insertString(0, formato.format(valor), null);
        } else {
            super.remove(0, getLength());
        }
    }

    public static void aplicar(JTextField campo) {
        campo.setDocument(new CampoMoeda());
    }

    public static double getValor(JTextField campo) {
        try {
            String texto = campo.getText().replaceAll("[^0-9,]", "").replace(",", ".");
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
