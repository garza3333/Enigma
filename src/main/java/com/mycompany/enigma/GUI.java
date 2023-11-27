package com.mycompany.enigma;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private final JComboBox<String> operationSelector;
    private final JComboBox<String> algorithmSelector;
    private final JTextArea inputText;
    private final JTextArea outputText;

    public GUI() {
        setTitle("Enigma");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700); // Ajuste el tamaño según sea necesario
        setLayout(null);

        JLabel operationLabel = new JLabel("Operación:");
        operationLabel.setBounds(20, 20, 80, 25);
        add(operationLabel);

        String[] operations = {"Cifrado", "Descifrado"};
        operationSelector = new JComboBox<>(operations);
        operationSelector.setBounds(110, 20, 120, 25);
        add(operationSelector);

        JLabel algorithmLabel = new JLabel("Algoritmo:");
        algorithmLabel.setBounds(20, 60, 80, 25);
        add(algorithmLabel);

        String[] algorithms = {"Algoritmo 1", "Algoritmo 2", "Algoritmo 3"}; // Reemplaza con tus algoritmos
        algorithmSelector = new JComboBox<>(algorithms);
        algorithmSelector.setBounds(110, 60, 120, 25);
        add(algorithmSelector);

        JButton applyButton = new JButton("Aplicar Algoritmo");
        applyButton.setBounds(20, 100, 150, 25);
        add(applyButton);

        JLabel inputTextLabel = new JLabel("Ingresar Texto:");
        inputTextLabel.setBounds(20, 140, 100, 25);
        add(inputTextLabel);

        inputText = new JTextArea();
        JScrollPane inputScrollPane = new JScrollPane(inputText);
        inputScrollPane.setBounds(20, 170, 300, 300); // Cambié el tamaño para que sea más grande
        add(inputScrollPane);

        JButton loadButton = new JButton("Cargar desde Archivo");
        loadButton.setBounds(20, 480, 180, 25);
        add(loadButton);

        JLabel outputTextLabel = new JLabel("Texto Cifrado/Descifrado:");
        outputTextLabel.setBounds(350, 140, 200, 25);
        add(outputTextLabel);

        outputText = new JTextArea();
        JScrollPane outputScrollPane = new JScrollPane(outputText);
        outputScrollPane.setBounds(350, 170, 400, 300); // Cambié el tamaño para que sea más grande
        outputText.setEditable(false);
        add(outputScrollPane);

        JButton emailButton = new JButton("Enviar por Correo Electrónico");
        emailButton.setBounds(20, 520, 220, 25);
        add(emailButton);

        // Acción al presionar el botón "Aplicar Algoritmo"
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí se puede implementar la lógica para aplicar el algoritmo seleccionado
                // Utiliza operationSelector.getSelectedIndex() para obtener el índice de la operación seleccionada
                // y algorithmSelector.getSelectedIndex() para obtener el índice del algoritmo seleccionado
                // Luego, realiza el cifrado o descifrado y actualiza el campo outputText con el resultado
                // Puedes utilizar bibliotecas de cifrado como javax.crypto para implementar los algoritmos
                // No se incluye la implementación real aquí por simplicidad del ejemplo.
            }
        });

        // Acción al presionar el botón "Cargar desde Archivo"
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí se puede implementar la lógica para cargar texto desde un archivo
                // Por ejemplo, abrir un JFileChooser para seleccionar un archivo y cargar su contenido en inputText
                // No se incluye la implementación real aquí por simplicidad del ejemplo.
            }
        });

        // Acción al presionar el botón "Enviar por Correo Electrónico"
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí se puede implementar la lógica para abrir una ventana para enviar un correo electrónico
                // Por ejemplo, abrir una nueva ventana (JFrame) para escribir el correo electrónico
                JFrame emailFrame = new JFrame("Enviar Correo Electrónico");
                emailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                emailFrame.setSize(400, 300);
                // Añadir componentes para escribir el correo electrónico aquí
                emailFrame.setVisible(true);
                // No se incluye la implementación real aquí por simplicidad del ejemplo.
            }
        });
    }


}

