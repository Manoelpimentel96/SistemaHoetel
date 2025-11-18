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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SomenteLetras extends DocumentFilter {

    /**
     * Usar dentro das tela SomenteLetras.aplicarComCapitalizacao(campoNome);
     * SomenteLetras.aplicarComCapitalizacao(campoLogin);
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string != null && string.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs)
            throws BadLocationException {
        if (string == null || string.isEmpty()) {
            // Permite apagar o texto (por exemplo, setText("") ou Backspace)
            super.replace(fb, offset, length, string, attrs);
        } else if (string.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            super.replace(fb, offset, length, string, attrs);
        }
    }

    // Método auxiliar: ativa a primeira letra maiúscula ao apertar Enter
    public static void aplicarComCapitalizacao(JTextField campo) {
        ((AbstractDocument) campo.getDocument()).setDocumentFilter(new SomenteLetras());

        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String texto = campo.getText().trim();
                    if (!texto.isEmpty()) {
                        campo.setText(Character.toUpperCase(texto.charAt(0)) + texto.substring(1));
                    }
                }
            }
        });
    }
}
