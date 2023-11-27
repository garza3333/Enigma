package com.mycompany.enigma;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class GUI extends JFrame {
    private final JComboBox<String> operationSelector;
    private final JComboBox<String> algorithmSelector;
    private final JTextArea inputText;
    private final JTextArea outputText;
    private final EncryptManager encryptorManager = new EncryptManager();

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

        String[] algorithms = {"Cesar", "Llave", "Vigenere","PalabraInversa","MensajeInverso","CodigoTelefonico","Binario","RSA","DES","AES"};
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

        
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlgorithm = (String) algorithmSelector.getSelectedItem();
                String input = inputText.getText();
                String output = "";

                try {
                    switch (selectedAlgorithm) {
                        case "Cesar":
                            if (operationSelector.getSelectedIndex() == 0) {
                                output = encryptorManager.c.encrypt(input);
                            } else {
                                output = encryptorManager.c.decrypt(input);
                            }
                            break;
                        case "Llave":
                            if (operationSelector.getSelectedIndex() == 0) {
                                output = encryptorManager.l.encrypt(input,"tango");
                            } else {
                                output = encryptorManager.l.decrypt(input,"tango");
                            }
                            break;
                        case "Vigenere":
                            if (operationSelector.getSelectedIndex() == 0) {
                                output = encryptorManager.v.encrypt(input,32);
                            } else {
                                output = encryptorManager.v.decrypt(input,32);
                            }
                            break;
                        case "PalabraInversa":
                            if (operationSelector.getSelectedIndex() == 0) {
                                output = encryptorManager.pi.encrypt(input);
                            } else {
                                output = encryptorManager.pi.decrypt(input);
                            }
                            break;
                        case "MensajeInverso":
                            if (operationSelector.getSelectedIndex() == 0) {
                                output = encryptorManager.mi.encrypt(input);
                            } else {
                                output = encryptorManager.mi.decrypt(input);
                            }
                            break;
                            
                        case "CodigoTelefonico":
                            if (operationSelector.getSelectedIndex() == 0) {
                                output = encryptorManager.ct.encrypt(input);
                            } else {
                                output = encryptorManager.ct.decrypt(input);
                            }
                            break;
                        
                        case "Binario":
                        if (operationSelector.getSelectedIndex() == 0) {
                            output = encryptorManager.bin.encrypt(input);
                        } else {
                            output = encryptorManager.bin.decrypt(input);
                        }
                        break;
                        
                        case "RSA":
                        if (operationSelector.getSelectedIndex() == 0) {
                            output = encryptorManager.rsa.encrypt(input);
                        } else {
                            output = encryptorManager.rsa.decrypt(input);
                        }
                        break;
                        
                        case "DES":
                        if (operationSelector.getSelectedIndex() == 0) {
                            output = encryptorManager.des.encrypt(input);
                        } else {
                            output = encryptorManager.des.decrypt(input);
                        }
                        break;
                        
                        case "AES":
                        if (operationSelector.getSelectedIndex() == 0) {
                            output = encryptorManager.aes.encrypt(input);
                        } else {
                            output = encryptorManager.aes.decrypt(input);
                        }
                        break;
                        
                        
                        // Agregar casos para los demás algoritmos
                        default:
                            output = "Algoritmo no válido";
                    }
                } catch (Exception ex) {
                    output = "Error al aplicar el algoritmo";
                }

                outputText.setText(output);
            }
        });

        
       
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();

                    try (java.util.Scanner scanner = new java.util.Scanner(selectedFile)) {
                        StringBuilder fileContent = new StringBuilder();
                        while (scanner.hasNextLine()) {
                            fileContent.append(scanner.nextLine()).append("\n");
                        }
                        inputText.setText(fileContent.toString());
                    } catch (java.io.FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Archivo no encontrado");
                    }
                }
            }
        });
        
        // LOGICA DE CORREO ELECTRONICO
        
 
    }


}

