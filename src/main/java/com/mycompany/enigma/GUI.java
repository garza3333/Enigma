package com.mycompany.enigma;


import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class GUI extends JFrame {
    private final JComboBox<String> operationSelector;
    private final JComboBox<String> algorithmSelector;
    private final JTextArea inputText;
    private final JTextArea outputText;
    private final JTextField numberField;
    private final JTextField letterField;
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
        
        // Crear un JTextField para introducir el correo electrónico
        JTextField emailField = new JTextField();
        emailField.setBounds(250, 520, 170, 25);
        add(emailField);
        
        // Crear un JLabel para etiquetar el campo de texto
        JLabel numberLabel = new JLabel("Clave Vigenere: ");
        numberLabel.setBounds(260, 20, 120, 25);
        add(numberLabel);
        
        
        // Crear un JTextField para introducir solo números
        numberField = new JTextField();
        numberField.setBounds(360, 20, 120, 25);
        add(numberField);
        
        // Agregar un DocumentFilter al PlainDocument del JTextField
        ((AbstractDocument) numberField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        
        // Crear un JLabel para etiquetar el campo de texto
        JLabel keywordLabel = new JLabel("Clave Llave: ");
        keywordLabel.setBounds(260, 60, 120, 25);
        add(keywordLabel);
        
        // Crear un JTextField para introducir solo letras
        letterField = new JTextField();
        letterField.setBounds(360, 60, 120, 25);
        add(letterField);
        
        // Agregar un DocumentFilter al PlainDocument del JTextField
        ((AbstractDocument) letterField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[a-zA-Z]*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[a-zA-Z]*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        
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
                                if(!"".equals(letterField.getText())){
                                    output = encryptorManager.l.encrypt(input,letterField.getText());
                                }else{
                                    output = encryptorManager.l.encrypt(input,"tango");
                                }
                                
                            } else {
                                if(!"".equals(letterField.getText())){
                                    output = encryptorManager.l.decrypt(input,letterField.getText());
                                }else{
                                    output = encryptorManager.l.decrypt(input,"tango");
                                }
                            }
                            break;
                        case "Vigenere":
                            if (operationSelector.getSelectedIndex() == 0) {
                                if(!"".equals(letterField.getText())){
                                    output = encryptorManager.v.encrypt(input,Integer.parseInt(numberField.getText()));
                                }else{
                                    output = encryptorManager.v.encrypt(input,23);
                                }
                                
                            } else {
                                if(!"".equals(letterField.getText())){
                                    output = encryptorManager.v.decrypt(input,Integer.parseInt(numberField.getText()));
                                }else{
                                    output = encryptorManager.v.decrypt(input,23);
                                }
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
        
        emailButton.addActionListener(new ActionListener() {
                
            @Override    public void actionPerformed(ActionEvent e) {
                
                String output = outputText.getText();        
                String selectedAlgorithm = (String) algorithmSelector.getSelectedItem();
                String email = emailField.getText();
                
                Properties prop = new Properties();
                prop.put("mail.smtp.auth", true);
                prop.put("mail.smtp.starttls.enable", "true");
                prop.put("mail.smtp.host", "smtp.gmail.com");
                prop.put("mail.smtp.port", "587");
                prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

                
                Session session = Session.getInstance(prop, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("proyecto02poo@gmail.com", "ydna gmhv kkci blxo");
                    }
                });
                
                
                Message message = new MimeMessage(session);
                try {
                    message.setFrom(new InternetAddress(email));
                } catch (AddressException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    message.setRecipients(
                            Message.RecipientType.TO, InternetAddress.parse(email));
                } catch (MessagingException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    message.setSubject("Enigma Encription/Decription");
                } catch (MessagingException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String msg = "default";
                if(operationSelector.getSelectedIndex() == 0){
                    msg = "Mensaje Encriptado ["+selectedAlgorithm+"] : " + output;
                }else{
                    msg = "Mensaje Desencriptado ["+selectedAlgorithm+"] : " + output;
                }
                

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                try {
                    mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
                } catch (MessagingException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                Multipart multipart = new MimeMultipart();
                try {
                    multipart.addBodyPart(mimeBodyPart);
                } catch (MessagingException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    message.setContent(multipart);
                } catch (MessagingException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    Transport.send(message);
                } catch (MessagingException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                

                System.out.println("Email sent");
                
            

        }});
        
        
 
    }


}

