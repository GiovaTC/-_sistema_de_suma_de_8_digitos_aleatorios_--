package ui;

import dao.SumaDAO;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SumaUI extends JFrame {


    private JTextField[] campos = new JTextField[8];
    private JTextField txtResultado = new JTextField();
    private int[] numeros = new int[8];

    public SumaUI() {
        setTitle("Suma de 8 Dígitos Aleatorios");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(11, 2, 5, 5));

        for (int i = 0; i < 8; i++) {
            panel.add(new JLabel("Número " + (i + 1) + ":"));
            campos[i] = new JTextField();
            campos[i].setEditable(false);
            panel.add(campos[i]);
        }

        panel.add(new JLabel("Resultado:"));
        txtResultado.setEditable(false);
        panel.add(txtResultado);

        JButton btnGenerar = new JButton("Generar y Sumar");
        JButton btnGuardar = new JButton("Guardar en Oracle");

        panel.add(btnGenerar);
        panel.add(btnGuardar);

        add(panel);

      //  btnGenerar.addActionListener(e -> generarNumeros());
      //  btnGuardar.addActionListener(e -> guardarDatos());
    }
}
